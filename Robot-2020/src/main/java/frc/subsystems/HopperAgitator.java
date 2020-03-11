/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.utils.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class HopperAgitator extends SubsystemBase {
private VictorSPX agitatorMotor;
private double agitatorMotorSpeed = 0;

  public HopperAgitator() {
    agitatorMotor = new VictorSPX(Constants.CAN_CLIMBER); 
    //still have to update the constants and add a new CAN_ID for the agitator, right now the climber will be a placeholder
}

public void motorOn() {
  agitatorMotorSpeed = 0.6;
  agitatorMotor.set(ControlMode.PercentOutput, agitatorMotorSpeed);
}

public void motorOff() {
  agitatorMotorSpeed = 0;
  agitatorMotor.set(ControlMode.PercentOutput, agitatorMotorSpeed);
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
