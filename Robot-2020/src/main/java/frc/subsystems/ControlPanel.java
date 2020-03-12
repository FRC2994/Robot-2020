/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.utils.Constants;
import edu.wpi.first.wpilibj.Solenoid;

import com.revrobotics.ColorSensorV3;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class ControlPanel extends SubsystemBase {

	private final VictorSPX motor;
	// private final ColorSensorV3 sensor;
	private Solenoid piston;
	public boolean controlPanelExtend;

	/**
	 * Creates a new ControlPanel.
	 */
	public ControlPanel() {
		this.motor = new VictorSPX(Constants.CAN_CONTROL_PANEL);
		// piston = new Solenoid(Constants.SOLENOID_PORT, Constants.PCM_CONTROL_PANEL); 
		// I2C.Port i2cPort = I2C.Port.kOnboard;
		// this.sensor = new ColorSensorV3(i2cPort);
		this.motor.setNeutralMode(NeutralMode.Brake);
	}

	public void raiseControlPanel() {
		controlPanelExtend = true;
		piston.set(controlPanelExtend);
	}
	
	public void lowerControlPanel(){
		controlPanelExtend = false;
		piston.set(controlPanelExtend);
	}
	
	public void moveMotor() {
		motor.set(ControlMode.PercentOutput, -0.2);
	}
	public void stopMotor() {
		motor.set(ControlMode.PercentOutput, 0);
	}

	@Override
	public void periodic() {
	// This method will be called once per scheduler run
	}


	// returns the ControlPanel motor
	public VictorSPX motor() {
		return this.motor;
	}

	// // returns the subsystem's color sensor
	// public ColorSensorV3 colorSensor() {
	// 	return this.sensor;
	// }
}
