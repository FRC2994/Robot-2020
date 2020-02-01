/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.revrobotics.ColorSensorV3;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import edu.wpi.first.wpilibj.util.Color;
import com.ctre.phoenix.motorcontrol.ControlMode;

import frc.subsystems.ControlPanel;

public class GoToColor extends CommandBase {
  
 	private final VictorSPX motor;
  	private final ColorSensorV3 sensor;
  	private ColorMatch matcher;

 	private Color blueTarget;

	/**
	 * Creates a new DetectColor.
	 */
	public GoToColor(ControlPanel controlPanel) {
		this.addRequirements(controlPanel);
		
		this.motor = controlPanel.motor();
		this.sensor = controlPanel.colorSensor();
		this.matcher = new ColorMatch();
	}

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
	this.blueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
	this.matcher.addColorMatch(this.blueTarget);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
	Color currentColor = this.sensor.getColor();
	ColorMatchResult matchResult = this.matcher.matchClosestColor(currentColor);
	SmartDashboard.putNumber("Confidence", matchResult.confidence);

	if (matchResult.confidence > 0.9) {
		// System.out.println("NO MOVE");
		this.motor.set(ControlMode.PercentOutput, 0);
	} else {
		this.motor.set(ControlMode.PercentOutput, 0.75);
		// System.out.println("MOVE");
	}
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
