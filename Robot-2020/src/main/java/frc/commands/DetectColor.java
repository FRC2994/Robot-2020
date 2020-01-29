/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import frc.subsystems.ControlPanel;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DetectColor extends CommandBase {

	private final ColorSensorV3 sensor;
	/**
	 * Creates a new DetectColor.
	 */
	public DetectColor(ControlPanel controlPanel) {
		this.addRequirements(controlPanel);
		this.sensor = controlPanel.colorSensor();
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		System.out.println("[DetectColor] initialize");
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		Color current = this.sensor.getColor();
		SmartDashboard.putNumber("red", current.red);
		SmartDashboard.putNumber("blue", current.blue);
		SmartDashboard.putNumber("green", current.green);
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
