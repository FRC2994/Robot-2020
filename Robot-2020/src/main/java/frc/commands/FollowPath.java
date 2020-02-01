/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import frc.subsystems.Drivetrain;

import java.io.File;
import java.io.IOException;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

import edu.wpi.first.wpilibj.Filesystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class FollowPath extends CommandBase {
	//Drivetrain
	private Drivetrain drivetrain;

	private EncoderFollower leftFollower;
	private EncoderFollower rightFollower;

	//Robot info
	private int ticksPerRev = 4500;
	private double wheelDiameter = 6.15*0.0254; //0.0254 meters = 1 inch
	private double velocity = 0.5;
	private double wheelbaseWidth = 0.2921;

	//Trajectory
	private Trajectory trajL;
	private Trajectory trajR;

	//PID Values
	double kP = 0.9;
	double kI = 0;
	double kD = 0;

	/**
	 * Creates a new FollowPath.
	 */
	public FollowPath(Drivetrain drivetrain) {
		System.out.println("[FollowPath] constructor");

		this.drivetrain = drivetrain;
		addRequirements(this.drivetrain);

		try{
			File deployDir = Filesystem.getDeployDirectory();

			// File leftTraj = new File(deployDir.getAbsolutePath() + "/PathWeaver/output/GenericPath.left.pf1.csv");
			// File rightTraj = new File(deployDir.getAbsolutePath() + "/PathWeaver/output/GenericPath.right.pf1.csv");

			// this.trajL = Pathfinder.readFromCSV(leftTraj);
			// this.trajR = Pathfinder.readFromCSV(rightTraj);
			File trajFile = new File(deployDir.getAbsolutePath() + "/PathWeaver/output/GenericPath.pf1.csv");
			Trajectory mainTraj = Pathfinder.readFromCSV(trajFile);

			// Create the Modifier Object
			TankModifier modifier = new TankModifier(mainTraj);

			// Generate the Left and Right trajectories using the original trajectory
			// as the centre
			modifier.modify(wheelbaseWidth);

			this.trajL = modifier.getLeftTrajectory();       // Get the Left Side
			this.trajR = modifier.getRightTrajectory();      // Get the Right Side 

		} catch (IOException e) {
			e.printStackTrace();	   
		}

		this.leftFollower = new EncoderFollower(trajL);
		this.rightFollower = new EncoderFollower(trajR);

		System.out.println("[FollowPath] constructor complete");
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		drivetrain.resetEncoders();
    
		this.leftFollower.configureEncoder((int)drivetrain.getLeftEncoderValue(), ticksPerRev, wheelDiameter);
		this.leftFollower.configurePIDVA(kP, kI, kD, 1 / velocity, 0);
		this.rightFollower.configureEncoder((int)drivetrain.getRightEncoderValue(), ticksPerRev, wheelDiameter);
		this.rightFollower.configurePIDVA(kP, kI, kD, 1 / velocity, 0);
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		System.out.println("[FollowPath] execute");

		double left_speed = this.leftFollower.calculate((int)drivetrain.getLeftEncoderValue());
		double right_speed = this.rightFollower.calculate((int)drivetrain.getRightEncoderValue());
		
		drivetrain.tankDrive(-left_speed, -right_speed);
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		drivetrain.tankDrive(0,0);
    	System.out.println("PATH IS DONE");
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		boolean isFinished = this.leftFollower.isFinished() && this.rightFollower.isFinished();
		System.out.println("[FollowPath] isFinished: " + isFinished);
		
		return isFinished;
	}
}
