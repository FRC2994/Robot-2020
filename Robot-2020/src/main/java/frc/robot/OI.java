/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


import frc.commands.ShiftGear;
import frc.subsystems.Drivetrain.GearShiftState;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  private final Joystick m_joystick = new Joystick(0);
  private final Joystick m_gamepad = new Joystick(1);

/**
   * Construct the OI and all of the buttons on it.
   */
  public OI() {


    //Joystick
    // final JoystickButton jsButnRecord                = new JoystickButton(m_joystick, 11);
    final JoystickButton jsButnShifter               = new JoystickButton(m_joystick, 12);


    //GamePad
    // final JoystickButton gpButnLEDOff                = new JoystickButton(m_gamepad, 7); //Gamepad Back button


    /* Connect the buttons to commands */
    
    //JOYSTICK
    //jsButnClimb.whenPressed(new LiftUpOrDown(LiftDirection.UP));
    jsButnShifter.whenPressed(new ShiftGear(GearShiftState.HI));
    jsButnShifter.whenReleased(new ShiftGear(GearShiftState.LO));

    //GAMEPAD
    // gpButnLEDOff.whenPressed(new LEDcontrol(LEDstate.OFF));
  }

  public Joystick getJoystick() {
    return m_joystick;
  }

  public Joystick getGamepad() {
    return m_gamepad;
  }
}
