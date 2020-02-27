/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Ultrasonic;

public class Elevator extends SubsystemBase {
  private VictorSPX motorcontroller;
  private Ultrasonic ultra;
  /**
   * Creates a new Elevator
   */

  double distance = ultra.getRangeInches();
  
  public Elevator() {
    motorcontroller = new VictorSPX(0);
  }

  public void UltrasonicSensor(){
    if (distance < 1) {
      motorcontroller.set(ControlMode.PercentOutput, 0);
    }
    else {
      motorcontroller.set(ControlMode.PercentOutput, 0.6);
    }
  }
  

  public void startMotor()
  {
    motorcontroller.set(ControlMode.PercentOutput, 0.8);
  }
  
  public void stopMotor()
  {
    motorcontroller.set(ControlMode.PercentOutput, 0);
  }

  @Override

  public void periodic() {
    // This method will be called once per scheduler run
  }
}
