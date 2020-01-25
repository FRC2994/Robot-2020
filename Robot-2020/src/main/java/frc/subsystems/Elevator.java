/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase {
  private VictorSPX motorcontroller;
  /**
   * Creates a new Elevator.
   */
  public Elevator() {
    motorcontroller = new VictorSPX(1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
