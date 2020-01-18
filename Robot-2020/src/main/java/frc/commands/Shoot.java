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
import frc.robot.Robot;

public class Shoot extends CommandBase {
  private ShooterWheel shoot = Robot.m_shooterwheel;
  private Joystick joystick = Robot.m_oi.getGamepad();

  public Shoot() {
    addRequirements(this.shoot);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //The top dpad
    if(joystick.getPOV() == 0 && shoot.status == true)
    {
      shoot.incrementSpeed();
    }
    //The bottom dpad
    if(joystick.getPOV() == 180 && shoot.status == true)
    {
      shoot.decrementSpeed();
    }

    //X preset Button
    if(joystick.getRawButton(3) == true)
    {
      shoot.shoot(1);
    }

    //Y preset Button
    if(joystick.getRawButton(4) == true)
    {
      shoot.shoot(2);
    }

    //B preset Button
    if(joystick.getRawButton(2) == true)
    {
      shoot.shoot(3);
    }

    //A button stop
    if(joystick.getRawButton(1) == true)
    {
      shoot.stopMotor();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shoot.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
