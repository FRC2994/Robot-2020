/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.subsystems.Drivetrain;
import frc.subsystems.Drivetrain.GearShiftState;

public class ShiftGear extends InstantCommand {
	private GearShiftState state;
  
  public ShiftGear(GearShiftState state) {
    super();
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_drivetrain);
    this.state = state;
  }

  @Override
	protected void initialize() {
    Robot.m_drivetrain.setGear(state);
	}


}
