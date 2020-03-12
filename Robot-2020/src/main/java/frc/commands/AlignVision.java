/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.subsystems.Drivetrain;
import frc.subsystems.VisionArduino;

public class AlignVision extends CommandBase {
  private Drivetrain drive;
  private VisionArduino vision;

  private double error, lastError, lastTime, currentTime, dt, rotationRate, rotationSum, currentPosition;

  private double target = 144;
  private double pixel_offset = 4;
  private double kP = 0.0095;
  private double kI = 0.01;
  private double iLimit = 20;
  private double kD = 0;

  public AlignVision(Drivetrain _drive, VisionArduino _vision) {
    this.drive = _drive;
    this.vision = _vision;
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    error = 0;
    lastError = 0;
    lastTime = 0;
    currentTime = 0;
    rotationRate = 0;
    rotationSum = 0;
    dt = 0;
    vision.ledOn();
    new WaitCommand(1); //Waits for led to turn on
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    currentPosition = vision.getX();
      if(currentPosition != -1){
      currentTime = Timer.getFPGATimestamp();
      dt = currentTime - lastTime;

      //P Calculation
      error = target - currentPosition;

      //I Calculation
      if(Math.abs(error)<iLimit){
        rotationSum += error*dt;
      }

      //D Calculation
      rotationRate = (error - lastError) / dt;

      double output = kP*error + kI*rotationSum + kD*rotationRate;
      System.out.println(currentPosition);
      drive.arcadeDrive(0, -output);

      lastError = error;
      lastTime = currentTime;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.tankDrive(0, 0);
    vision.ledOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // return currentPosition == -1; // -1 means that is sees no target
    return false;
  }
}
