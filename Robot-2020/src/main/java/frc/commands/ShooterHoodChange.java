/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Joystick;
import frc.subsystems.ShooterHood;

public class ShooterHoodChange extends CommandBase {
  private ShooterHood hood;
  private Joystick gamepad;
  int x = 0;
  public ShooterHoodChange(ShooterHood _hood, Joystick _gamepad) {
    this.hood = _hood;
    this.gamepad = _gamepad;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(hood);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    x = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    x++;
    int DPAD_STATE = this.gamepad.getPOV();
    if(DPAD_STATE == 270 && x == 1){
      this.hood.ServoDec();
    }
    if(DPAD_STATE == 90 && x == 1){
      this.hood.ServoInc();
    }
    if(x == 15){
      x=0;
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
