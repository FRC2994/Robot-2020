/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Solenoid;

public class intakeSubsystem extends SubsystemBase {
  private double intakeMotorSpeed = 0;
  public boolean intakePistonExtend; 
  
  VictorSPX intakeMotor;
  Solenoid intakePiston;

  public intakeSubsystem() {
    intakeMotor = new VictorSPX(5); //Y on the controller
    intakePiston = new Solenoid(6); //Aon the controllerr
  }

  public void motorOn() {
    intakeMotorSpeed = 0.75; 
  }

  public void motorOff(){
    intakeMotorSpeed = 0;
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