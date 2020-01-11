/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


import frc.commands.ShiftGear;
import frc.subsystems.Drivetrain;
import frc.subsystems.Drivetrain.GearShiftState;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	private final Joystick joystick = new Joystick(0); 
	// private final Joystick gamepad = new Joystick(1);

	private final JoystickButton joystickButton;

	/**
	 * Construct the OI and all of the buttons on it.
	 */
	public OI() {
		this.joystickButton = new JoystickButton(this.joystick, 12);
	}

	public void mountJoystickButtonCommands(Drivetrain drivetrain) {
		this.joystickButton.whenPressed(new ShiftGear(drivetrain, GearShiftState.HI));
		this.joystickButton.whenReleased(new ShiftGear(drivetrain, GearShiftState.LO));
	}

	public Joystick getJoystick() {
		return this.joystick;
	}

	// public Joystick getGamepad() {
	// 	return m_gamepad;
	// }
}
