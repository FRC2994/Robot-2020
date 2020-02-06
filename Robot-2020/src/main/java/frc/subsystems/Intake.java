/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;


import edu.wpi.first.wpilibj.Solenoid;

public class Intake extends SubsystemBase {
  private double intakeMotorSpeed = 0;
  public boolean intakePistonExtend; 
  
  private VictorSPX intakeMotor;
  private Solenoid intakePiston;

  public Intake() {
    intakeMotor = new VictorSPX(5); //TODO: find the actual CAN id for this
    intakePiston = new Solenoid(6); //Same thing
  }

  public void motorOn() {
    intakeMotorSpeed = 0.75;
    intakeMotor.set(ControlMode.PercentOutput, intakeMotorSpeed);
  }

  public void motorOff(){
    intakeMotorSpeed = 0;
    intakeMotor.set(ControlMode.PercentOutput, intakeMotorSpeed);
  }

  public void lowerIntake() {
    intakePistonExtend = true;
    intakePiston.set(intakePistonExtend);
  }

  public void raiseIntake(){
    intakePistonExtend = false;
    intakePiston.set(intakePistonExtend);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}