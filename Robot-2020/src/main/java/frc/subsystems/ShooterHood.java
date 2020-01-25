/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Servo;

public class ShooterHood extends SubsystemBase {

  private Servo Hood = new Servo(1);
  private double HoodAngle;
  private double ConstantChange;

  public ShooterHood() {
    Hood.set(0);
    HoodAngle = 0;
    ConstantChange = 0.2;
  }

  public void ServoInc() {
    HoodAngle = HoodAngle + ConstantChange;
    Hood.set(HoodAngle);
  }

  public void ServoDec() {
    HoodAngle = HoodAngle - ConstantChange;
    Hood.set(HoodAngle);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
