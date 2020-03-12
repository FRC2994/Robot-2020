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

public class DriveToPosition extends CommandBase {
  private Drivetrain drive;
  private double target,error, integral, derivative, timechange, currentTime, lastTime, lastError, currentPosition;
  private double currentAngle, desiredAngle, angleError, previousAngleError, angleSum, angleRate;

  //PID VALUES
  private double kP = 0.08;
  private double kI = 0;
  private double iLimit = 0;
  private double kD = 0;

  //PID VALUES FOR ANGLES (Makes sure that it is going straight, doesn't go off)
  private double a_kP = 0.0196;
  private double a_kI = 0;
  private double a_kD = 0;

  public DriveToPosition(Drivetrain _drive, double _desiredPos) {
    drive = _drive;
    target = _desiredPos;
    addRequirements(drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drive.resetEncoders();
    //Resets variables
    error = 0;
    timechange = 0;
    integral = 0;
    derivative = 0;
    lastTime = 0;
    lastError = 0;

    desiredAngle = -(int)drive.getHeading();
    angleError = 0;
    previousAngleError = 0;
    angleSum = 0;
    angleRate = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    currentPosition = drive.getLeftEncoderValue();
    currentAngle = -(int)drive.getHeading();

    //Time stuff
    currentTime = Timer.getFPGATimestamp();
    timechange = currentTime - lastTime;

    //P Calculations
    error = target - currentPosition;
    angleError = desiredAngle - currentAngle;

    //I Calculations
    if(Math.abs(error) < iLimit){
      integral += error*timechange;
    }
    angleSum += angleError*timechange;

    //D Calculations
    derivative = (error - lastError) / timechange;
    angleRate = (angleError - previousAngleError) /timechange;

    double forwardOutput = kP*error + kI*integral + kD*derivative;
    double rotationOutput = a_kP*angleError + a_kI*angleSum + a_kD*angleRate;
    drive.arcadeDrive(-forwardOutput, rotationOutput);

    lastTime = currentTime;
    lastError = error;
    previousAngleError = angleError;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.tankDrive(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (currentAngle == desiredAngle) && (currentPosition == target);
  }
}
