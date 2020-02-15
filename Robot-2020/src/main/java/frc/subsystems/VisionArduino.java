/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionArduino extends SubsystemBase {
  private static I2C Wire = new I2C(Port.kMXP, 4);//uses the i2c port on the RoboRIO
  //uses address 4 
  private static final int MAX_BYTES = 32;
  double y = 0,d = 0;
  String x = "";

	private String read(){//function to read the data from arduino
		byte[] data = new byte[MAX_BYTES];//create a byte array to hold the incoming data
		Wire.read(4, MAX_BYTES, data);//use address 4 on i2c and store it in data
		String output = new String(data);//create a string from the byte array
		int pt = output.indexOf((char)255);
		return (String) output.subSequence(0, pt < 0 ? 0 : pt);
  }
  
  private void splitter() {
    String info[] = read().split("\\|");
    if(info.length == 3){//if there is an x, y, and area value the length equals 3
			x = info[0];//set x
			y = Double.parseDouble(info[1]);//set y
      d = Double.parseDouble(info[2]);//set area
    }
  }

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
  public VisionArduino() {

  }

  @Override
  public void periodic() {
    SmartDashboard.putString("X Position:", getX());
    SmartDashboard.putNumber("Y Position:", getY());
    SmartDashboard.putNumber("Distance:", getDistance());
  }
}
