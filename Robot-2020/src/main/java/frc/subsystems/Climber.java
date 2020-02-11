/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANDigitalInput.LimitSwitch;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;


public class Climber extends PIDSubsystem {

  private final VictorSPX m_motor;
  private final Encoder m_encoder;
  private final PIDController m_pidLight;
  private final PIDController m_pidHeavy;
  private final DigitalInput m_limitSwitch;

  private final static int climberPosInc = 469;
  private final static int climberPosDec = -469;
  //ticks are 469 or 156
  //gearbox ratio

  private final int m_maxEncoder = 5000;

  private int currentPosition = 0;
  private int desiredPosition = 0;
  private boolean foundBottom = false;


  public Climber() {
    super(
        // The PIDController used by the subsystem
        new PIDController(0, 0, 0));

      //assigning objects to variables
        this.m_motor = new VictorSPX(8);
        this.m_encoder = new Encoder(1, 2);
        this.m_pidLight = new PIDController(0.3, 0, 0);
        this.m_pidHeavy = new PIDController(0.5, 0, 0);
        this.m_limitSwitch = new DigitalInput(3);
  }

  public int getDesiredPosition() {
    m_controller.getD();
    return desiredPosition;
  }

  public int getCurrentPosition() {
    return currentPosition;
  }

  public void setPIDLight() {
    m_pidLight.setPID(0.3, 0, 0);
  }

  public void setPIDHeavy() {
    m_pidHeavy.setPID(0.5, 0, 0);
  }

  public boolean findBottom() {
    if (m_limitSwitch.get()) {
      foundBottom = true;
    }  else {
      foundBottom = false;
      System.out.println("Bottom not found");
    }
    return foundBottom;
  }

  public void setPosition() {
    m_motor.set(ControlMode.Position, getDesiredPosition());
  }

  public void moveUp() {
    if (foundBottom) {

    System.out.println("Arm LIFTING up to " + getDesiredPosition());
    setPIDLight();
   
      if (currentPosition < m_maxEncoder) {
        setPosition();
        //m_motor.set(ControlMode.Position, getDesiredPosition());
      } 
      
      //do nothing if foundBottom is false
    }

  }


  public void pullUp() {
    System.out.println("Arm PULLING up to " + getDesiredPosition());
    setPIDHeavy();
    setPosition();
  }


  public void moveDown() {
    if (m_limitSwitch.get()) {
      foundBottom = true;
      
      //reset encoder
      //m_encoder.get() = ;

      setPosition();
    }
  }

  public void pullDown() {
    
  }

  @Override
  public void useOutput(double output, double setpoint) {
      // Use the output here
      //m_motor.set(ControlMode.PercentOutput,output + m_feedForward.calculate(setpoint));  
      m_motor.set(ControlMode.PercentOutput,output);

    }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return 0;
  }
}
