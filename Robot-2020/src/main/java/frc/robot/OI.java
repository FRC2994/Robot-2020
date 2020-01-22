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
import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.Robot;
import frc.commands.ShiftGear;
import frc.subsystems.Drivetrain;
import frc.subsystems.Drivetrain.GearShiftState;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public final Joystick joystick = new Joystick(0); 
	// public final Joystick gamepad = new Joystick(1);



	/*JOYSTICK BUTTONS*/
	private final JoystickButton jsButnShifter;

	/*GAMEPAD BUTTONS*/
	//private final JoystickButton gpButnExpamle; //This is an example of a gamepad buttn



	/**
	 * Construct the OI and all of the buttons on it.
	 */
	public OI() {
		this.jsButnShifter = new JoystickButton(this.joystick, 12);
	}

	public void initializeButtons() {
		//Wpilib suggest we run instand commands inline now
		this.jsButnShifter.whenPressed(new InstantCommand(Robot.m_drivetrain::highGear, Robot.m_drivetrain));
		this.jsButnShifter.whenReleased(new InstantCommand(Robot.m_drivetrain::lowGear, Robot.m_drivetrain));
	}

	
	public Joystick getJoystick() {
		return this.joystick;
	}

	// public Joystick getGamepad() {
	// 	return m_gamepad;
	// }
}
