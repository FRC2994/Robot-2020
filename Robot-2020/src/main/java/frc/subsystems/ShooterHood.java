/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;;

public class ShooterHood extends SubsystemBase {

  private Servo Hood = new Servo(1);
  private double HoodAngle;
  private double ConstantChange;

  public ShooterHood() {
    HoodAngle = 0.3;
    Hood.set(HoodAngle);
    ConstantChange = 0.1;
  }

  public void ServoInc() {
    if (HoodAngle <= 0.9) {
      HoodAngle += ConstantChange;
      Hood.set(HoodAngle);
    }
  }

  public void ServoDec() {
    if(HoodAngle >= 0.3){
      HoodAngle -= ConstantChange;
      Hood.set(HoodAngle);
    }
  }  
  public void setAngle(double hoodSetting) {
    Hood.set(hoodSetting);
  }

  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Position", HoodAngle);
  }

}
