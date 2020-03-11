/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.subsystems.Drivetrain;

public class DriveRotation extends CommandBase {
  private Drivetrain drive;
  private double target,error, integral, derivative, timechange, currentTime, lastTime, lastError, currentPosition;

  //PID VALUES
  private double kP = 0.0196;
  private double kI = 0;      //TODO: find values for these
  private double iLimit = 10;
  private double kD = 0;

  public DriveRotation(Drivetrain _drive, double _target) {
    drive = _drive;
    target = _target;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //Resets variables
    error = 0;
    timechange = 0;
    integral = 0;
    derivative = 0;
    lastTime = 0;
    lastError = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Gets the position
    currentPosition = -(int)drive.getHeading();

    //Does P calculation
    error = target - currentPosition;

    //Does I calculation
    currentTime = Timer.getFPGATimestamp();
    timechange = currentTime - lastTime;
    if(Math.abs(error) < iLimit){
      integral += error*timechange;
    }

    //Does D calculation
    derivative = (error - lastError) / timechange;

    //Outputs the output
    double output = (kP * error) + (kI * integral) + (kD * derivative);
    drive.arcadeDrive(0, output);

    lastError = error;
    lastTime = currentTime;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.tankDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return currentPosition == target;
  }
}
