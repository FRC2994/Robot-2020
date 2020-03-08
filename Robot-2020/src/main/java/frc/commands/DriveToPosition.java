/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.subsystems.Drivetrain;

public class DriveToPosition extends CommandBase {
  private Drivetrain drive;
  private double target;
  private double error;
  private double kP = 0.08;
  public DriveToPosition(Drivetrain _drive, double _desiredPos) {
    drive =_drive;
    target = _desiredPos;
    addRequirements(drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drive.resetEncoders();
    error = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("Called Position");
    error = target - drive.getLeftEncoderValue();
    double output = kP*error;
    drive.arcadeDrive(-output, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.tankDrive(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    System.out.println(drive.getLeftEncoderValue());
    return (drive.getLeftEncoderValue()-2  < target) && (drive.getLeftEncoderValue()+2 > target);
  }
}
