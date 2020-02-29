/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.utils.Constants;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;;

public class ShooterHood extends SubsystemBase {

  private Servo Hood = new Servo(Constants.PWM_HOOD);
  private double HoodAngle;
  private double ConstantChange;

  public ShooterHood() {
    HoodAngle = 0.3;
    Hood.set(HoodAngle);
    ConstantChange = 0.1;
  }

  public void ServoInc() {
    if(Hood.get() <= 0.8) {
      HoodAngle += ConstantChange;
      Hood.set(Hood.get() + ConstantChange);
      System.out.println("Hood Extend");
    }
  }

  public void ServoDec() {
    if(Hood.get() >= 0.3){
      HoodAngle -= ConstantChange;
      Hood.set(Hood.get() - ConstantChange);
    }
  }
  @Override
  public void periodic() {
    SmartDashboard.putNumber("Position", Hood.get());
  }
}
