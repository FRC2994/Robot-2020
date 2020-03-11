/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.utils;

import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.Joystick;

/**
 * Add your docs here.
 */
public class GamepadRightAxis extends Trigger {
  private Joystick gamepad;
  public GamepadRightAxis(Joystick _gamepad){
    gamepad = _gamepad;
  }
  @Override
  public boolean get() {
    return gamepad.getRawAxis(3) > 0.1;
  }
}
