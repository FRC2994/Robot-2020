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

public class Hopper extends SubsystemBase {

  private Servo HopperDisturber = new Servo(Constants.PWM_HOPPER);
  private double HopperPosition;
  private double ConstantChange;
  /**
   * Creates a new Hopper.
   */
  public Hopper() {
    HopperPosition = 0.3;
    HopperDisturber.set(HopperPosition);
    ConstantChange = 0.1;
  }

  public void HopperDisturberExtend(){
    // The hopper actuator will extend(push-out) once this is called
    if(HopperPosition <= 0.9) {
      HopperPosition += ConstantChange;
      HopperDisturber.set(HopperPosition);
    }
  }
  public void HopperDisturberIntake(){
    //The hopper actuator will be pulled back once this is called
    if(HopperPosition >= 0.3); {
      HopperPosition += ConstantChange;
      HopperDisturber.set(HopperPosition);
    }
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
