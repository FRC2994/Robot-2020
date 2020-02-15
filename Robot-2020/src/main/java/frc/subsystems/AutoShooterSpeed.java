/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.subsystems.VisionArduino;
import frc.subsystems.ShooterWheel;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import com.revrobotics.CANSparkMax;

public class AutoShooterSpeed extends SubsystemBase {

  private static I2C Wire = new I2C(Port.kMXP, 4);
  private static final int MAX_BYTES = 32;
  private ShooterWheel shooterwheel = new ShooterWheel();
  private int LowSpeedRPM = 3000;
  private int HighSpeedRPM = 5000;
  private CANSparkMax ShooterWheel;
  
  public String getX() {
    splitter();
    return x;
  }
  public double getY() {
    splitter();
    return y;
  }
  public double getDistance() {
    splitter();
    return d;
  }

  public AutoShooterSpeed() {
   
      
  }
  
	
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
