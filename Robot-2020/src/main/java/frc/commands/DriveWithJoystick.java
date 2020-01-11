/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj.Joystick;
import frc.subsystems.Drivetrain;
import java.util.*;

/**
 * Have the robot drive tank style using the PS3 Joystick until interrupted.
 */
public class DriveWithJoystick implements Command {
	// things we need direct references to
	private Drivetrain drivetrain;
	private Joystick joystick;

	// set of all the subsystems this command relies on
	private Set<Subsystem> requiredSubsystems;

	public DriveWithJoystick(Drivetrain drivetrain, Joystick joystick) {
		// keep a reference to things we need
		this.drivetrain = drivetrain;
		this.joystick = joystick;

		// also include the drivetrain in the list of required subsystems
		this.requiredSubsystems = new HashSet<Subsystem>();
		this.addSubsystemRequirement(drivetrain);
	}

	/**
	 * The initial subroutine of a command.  Called once when the command is initially scheduled.
	 */
	public void initialize() {
	}

	/**
	 * The main body of a command.  Called repeatedly while the command is scheduled.
	 */
	public void execute() {
		this.drivetrain.arcadeDrive();
	}

	/**
	 * The action to take when the command ends.  Called when either the command finishes normally,
	 * or when it interrupted/canceled.
	 *
	 * @param interrupted whether the command was interrupted/canceled
	 */
	public void end(boolean interrupted) {
		this.drivetrain.tankDrive(0, 0);
	}

	/**
	 * Whether the command has finished.  Once a command finishes, the scheduler will call its
	 * end() method and un-schedule it.
	 *
	 * @return whether the command has finished.
	 */
	public boolean isFinished() {
		return false;
	}

	/**
	 * Add a subsystem that this command will rely on
	 * 
	 * @param subsystem the subsystem to add
	 */
	public void addSubsystemRequirement(Subsystem subsystem) {
		this.requiredSubsystems.add(subsystem);
	}

	/**
	 *
	 * @return the set of subsystems that are required
	 */
	public Set<Subsystem> getRequirements() {
		return this.requiredSubsystems;
	}
}
