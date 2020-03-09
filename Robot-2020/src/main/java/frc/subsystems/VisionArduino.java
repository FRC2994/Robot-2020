/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.utils.Constants;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

public class VisionArduino extends SubsystemBase {
  private static I2C Wire = new I2C(Port.kOnboard, 4);//uses the i2c port on the RoboRIO
  //uses address 4 
  private static final int MAX_BYTES = 32;
  double y = 0,d = 0;
  double x;
  Solenoid led = new Solenoid(Constants.CAN_PCM, 7);

	private String read(){//function to read the data from arduino
		byte[] data = new byte[MAX_BYTES];//create a byte array to hold the incoming data
		Wire.read(4, MAX_BYTES, data);//use address 4 on i2c and store it in data
		String output = new String(data);//create a string from the byte array
		int pt = output.indexOf((char)255);
    return (String) output.subSequence(0, pt < 0 ? 0 : pt);
  }
  
  public void splitter() {
    String info = read();
    //.split("\\|");
    // if(info.length == 3){//if there is an x, y, and area value the length equals 3
		x = Double.parseDouble(info);//set x
			// y = Double.parseDouble(info[1]);//set y
      // d = Double.parseDouble(info[2]);//set area

  }

  public double getX() {
    return x;
  }
  
  // public double getY() {
  //   return y;
  // }

  // public double getDistance() {
  //   return d;
  // }
  public VisionArduino() {
    ledOff();
  }

  public void ledOn(){
    led.set(true);
  }
  public void ledOff(){
    led.set(false);
  }
  @Override
  public void periodic() {
  }
}
