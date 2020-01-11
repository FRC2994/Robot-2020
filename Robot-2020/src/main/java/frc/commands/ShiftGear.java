/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.subsystems.Drivetrain;
import frc.subsystems.Drivetrain.GearShiftState;

public class ShiftGear extends InstantCommand { 	
	public ShiftGear(Drivetrain drivetrain, GearShiftState state) {
		// sets the command to use the ShiftGearRunner as the "runner"
		// and sets the drivetrain as a required subsystem
		super(new ShiftGearRunner(drivetrain, state), drivetrain);
	}

	@Override
	public void initialize() {
	}
}

class ShiftGearRunner implements Runnable {
	private final Drivetrain drivetrain;
	private GearShiftState state;

	public ShiftGearRunner(Drivetrain drivetrain, GearShiftState state) {
		this.drivetrain = drivetrain;
		this.state = state;
	}

	public void run() {
		this.drivetrain.setGear(this.state);
	}
}