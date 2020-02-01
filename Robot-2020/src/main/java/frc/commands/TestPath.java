/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import java.io.File;
import frc.robot.Robot;
import frc.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.command.Command;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Trajectory.Segment;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;
import jaci.pathfinder.PathfinderFRC;

import java.io.IOException;

public class TestPath extends Command {

  // VARIABLES

  //Drivetrain
  Drivetrain drivetrain = Robot.m_drivetrain;
  private EncoderFollower left;
  private EncoderFollower right;

  //Robot info
  private int ticksPerRev = 3500;
  private double wheelDiameter = 6.15*0.0254; //0.0254 meters = 1 inch
  private double velocity = 1.52;
  private double wheelbaseWidth = 2.25;

  //Path files
  File leftTraj = new File("/home/lvuser/deploy/paths/Unnamed.right.pf1.csv");
  File rightTraj = new File("/home/lvuser/deploy/paths/Unnamed.right.pf1.csv");
  String pathName = "Test";

  //Trajectory
  Trajectory trajL;
  Trajectory trajR;


  //PID Values
  double kP = 0.9;
  double kI = 0;
  double kD = 0;

  public TestPath() {
    requires(drivetrain);

    try{
      // trajL = Pathfinder.readFromCSV(leftTraj);
      // trajR = Pathfinder.readFromCSV(rightTraj);
      trajL = PathfinderFRC.getTrajectory(pathName + ".left");
      trajR = PathfinderFRC.getTrajectory(pathName + ".right");
    } catch (IOException e) {
      e.printStackTrace();	   
    }

  }

  // Called just before this Command runs the first time

  // Resets, initializes and congifures encoders.
  @Override
  protected void initialize() {
    drivetrain.resetEncoders();
    
    left = new EncoderFollower(trajL);
    right = new EncoderFollower(trajR);

    left.configureEncoder((int)drivetrain.getLeftEncoderValue(),ticksPerRev, wheelDiameter);
    left.configurePIDVA(kP, kI, kD, 1 / velocity, 0);
    right.configureEncoder((int)drivetrain.getRightEncoderValue(), ticksPerRev, wheelDiameter);
    right.configurePIDVA(kP, kI, kD, 1 / velocity, 0);

  }

  // Called repeatedly when this Command is scheduled to run

  // Continuously calculates certain numbers based off of the encoder values and runs the drivetrain using those values.
  @Override
  protected void execute() {
    // double gyroError = drivetrain.getHeading() - Math.toDegrees(left.getHeading());
    // double correction = mp_direction * 0.03 * Pathfinder.boundHalfDegrees(gyroError);
    // int leftIn = drivetrain.getLeftEncoderValue();
    // int rightIn = drivetrain.getRightEncoderValue();
    // drivetrain.tankDrive(leftIn + correction, -1 * (rightIn - correction));
    double left_speed = left.calculate((int)drivetrain.getLeftEncoderValue());
    double right_speed = right.calculate((int)drivetrain.getRightEncoderValue());
    double heading = drivetrain.gyro.getAngle();
    double desired_heading = Pathfinder.r2d(left.getHeading());
    double heading_difference = Pathfinder.boundHalfDegrees(desired_heading - heading);
    double turn = 0;
    drivetrain.tankDrive(left_speed + turn, right_speed - turn);
    System.out.println("Time: " + Robot.getTime() + " leftIn: " + left_speed + " rightIn: " + right_speed 
        		// + " leftPos:" + leftSeg.position + " rightPos:" + rightSeg.position 
        		// + " leftVel:" + leftSeg.velocity + " rightVel:" + rightSeg.velocity
        		// + " leftAcc:" + leftSeg.acceleration + " rightAcc:" + rightSeg.acceleration     
            // + " leftOut: " + leftOut + " rightOut: " + rightOut + " expHeading: " + leftSeg.heading 
            + "Turn: " + turn + " leftError: " + (left_speed-drivetrain.getLeftEncoderValue())
        		+ " gyroHeading: " + heading + " correction: " + heading_difference + " lenc: " 
        		+ drivetrain.getLeftEncoderValue() + " renc: " + drivetrain.getRightEncoderValue());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (left.isFinished() || right.isFinished());
  
  }

  // Called once after isFinished returns true

  // Stops the drivetrain, and broadcasts a final message.
  @Override
  protected void end() {
    drivetrain.tankDrive(0,0);
    System.out.println("PATH IS DONE");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
