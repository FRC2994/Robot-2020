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
import edu.wpi.first.wpilibj.Ultrasonic;

public class Elevator extends SubsystemBase {
  private VictorSPX motorcontroller;
  private Ultrasonic ultra;
  DigitalInput limitSwitch = new DigitalInput(Constants.DIO_ELEVATOR);
  Counter counter = new Counter(limitSwitch);
  
  public Elevator() {
    motorcontroller = new VictorSPX(Constants.CAN_ELEVATOR); //TODO: Find a CAN ID for the Elevator
    motorcontroller.configOpenloopRamp(0);
    ultra = new Ultrasonic(7, 6);
    ultra.setAutomaticMode(true);
  }


  public void semiIntake() {
    motorcontroller.set(ControlMode.PercentOutput, 0.2);
  }

  public boolean isBallIn(){  
    if (ultra.getRangeInches() < 3) {
      return true;
    }

    return false;
  }

  public void startMotor() {
    motorcontroller.set(ControlMode.PercentOutput, 0.7);
  }

  public void startMotorGated() {
    if (this.isBallIn()) {
      motorcontroller.set(ControlMode.PercentOutput, 0.0);
      return;
    }

    motorcontroller.set(ControlMode.PercentOutput, 0.2);
    // System.out.print("ELEVATOR ACTIVE");
  }
  
  public void stopMotor() {
    motorcontroller.set(ControlMode.PercentOutput, 0);
    // System.out.print("ELEVATOR OFF");
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // System.out.println(ultra.getRangeInches());
  }
}
