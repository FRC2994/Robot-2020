/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANDigitalInput.LimitSwitch;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.utils.Constants;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;

public class Elevator extends SubsystemBase {
  private VictorSPX motorcontroller;
  /**
   * Creates a new Elevator
   */

  DigitalInput limitSwitch = new DigitalInput(Constants.DIO_ELEVATOR);
  Counter counter = new Counter(limitSwitch);
  
  public Elevator() {
    motorcontroller = new VictorSPX(Constants.CAN_ELEVATOR); //TODO: Find a CAN ID for the Elevator
  }

  public void LimitSwitch() {
    if (limitSwitch.get()) {
      motorcontroller.set(ControlMode.PercentOutput, 0.8);
    }
    else {
      motorcontroller.set(ControlMode.PercentOutput, 0);
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
