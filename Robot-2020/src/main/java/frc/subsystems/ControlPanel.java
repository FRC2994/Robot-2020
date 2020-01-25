/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

public class ControlPanel extends SubsystemBase {

	private final ColorSensorV3 sensor;
	private final ColorMatch matcher;

	/**
	 * Creates a new ControlPanel.
	 */
	public ControlPanel() {
		I2C.Port i2cPort = I2C.Port.kOnboard;
		this.sensor = new ColorSensorV3(i2cPort);

		this.matcher = new ColorMatch();
	}

	@Override
	public void periodic() {
	// This method will be called once per scheduler run
	}

	// returns the subsystem's color sensor
	public ColorSensorV3 colorSensor() {
		return this.sensor;
	}
}
