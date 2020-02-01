/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.subsystems.Drivetrain;
import frc.subsystems.VisionArduino;

public class AlignPID extends CommandBase {
  private VisionArduino arduino;
  private Drivetrain drive;
  double kP = 0.1;
  double min_command = 0.10;

  public AlignPID(VisionArduino ad, Drivetrain dt) {
    this.arduino = ad;
    this.drive = dt;
    addRequirements(this.arduino);
    addRequirements(this.drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    int currentPos = arduino.getPos();
    int error = 158 - currentPos; 
    double steer = kP*error - min_command;
    System.out.println("Error: " + error + " Pos:" + currentPos);
    // if(error > 10){// The target is left so robot goes left

    // }
    // else if (error < -10){ //The target is right so robot goes right

    // }
    // else{
    //   drive.tankDrive(0,0);
    // }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.tankDrive(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
