/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.subsystems.Drivetrain;

public class DriveRotation extends CommandBase {
  private Drivetrain drive;
  private double target;
  private double error;
  private double kP = 0.0196;
  public DriveRotation(Drivetrain _drive, double _target) {
    drive = _drive;
    target = _target;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    error = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // System.out.println("Called Rotation");
    error = target - (-(int)drive.getHeading());

    double output = kP * error;
    // System.out.println(output);
    drive.arcadeDrive(0, output);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.tankDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    System.out.println(-(int)drive.getHeading());

    return (-(int)drive.getHeading() +2 > target) && (-(int)drive.getHeading() - 2 < target);
  }
}
