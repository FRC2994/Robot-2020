/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.subsystems.Drivetrain;

public class DefaultDrive extends CommandBase {
  private Drivetrain drive;
  private Joystick js;

  public DefaultDrive(Drivetrain subsystem, Joystick joystick) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.drive = subsystem;
    this.js = joystick;
    addRequirements(this.drive);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.drive.arcadeDrive(js.getY(), js.getX());
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
