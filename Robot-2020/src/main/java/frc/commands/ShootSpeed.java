/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Joystick;
import frc.subsystems.ShooterWheel;

public class ShootSpeed extends CommandBase {
  private Joystick joystick;
  private ShooterWheel shoot;

  public ShootSpeed(ShooterWheel subsystem, Joystick gamepad) {
    this.shoot = subsystem;
    this.joystick = gamepad;
    addRequirements(this.shoot);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(joystick.getPOV() == 0 && shoot.status == true){ //Top D-Pad
      shoot.incrementSpeed();
    }
    if(joystick.getPOV() == 180 && shoot.status == true){ //Bottom D-Pad
      shoot.decrementSpeed();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
