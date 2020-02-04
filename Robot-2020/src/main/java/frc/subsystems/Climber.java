/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANDigitalInput.LimitSwitch;
import edu.wpi.first.wpilibj.DigitalInput;

import edu.wpi.first.wpilibj.Encoder;


public class Climber extends PIDSubsystem {

  private final VictorSPX m_motor;
  private final Encoder m_encoder;
  private final PIDController m_pid;
  private final DigitalInput m_limitSwitch;

  private final static int climberPosInc = 2500;
  private final static int climberPosDec = -2500;

  private int currentPosition = 0;
  private int desiredPosition = 0;


  public Climber() {
    //super("SusbystemName", 1, 2, 3); 



      //assigning objects to variables
        this.m_motor = new VictorSPX(8);
        this.m_encoder = new Encoder(1, 2);
        //this.m_pidUp = new PIDController(0.3, 0, 0);
        //this.m_pidDown = new PIDController(0.5, 0, 0);
        this.m_limitSwitch = new DigitalInput(3);
  }

  public int getDesiredPosition() {
    m_controller.getD();
    return desiredPosition;
  }

  public int getCurrentPosition() {
    return currentPosition;
  }

  public void setPIDUp() {
    m_pid.setPID(0.3, 0, 0);
  }

  public void setPIDDown() {
    m_pid.setPID(0.5, 0, 0);
  }

  public void moveUp() {
    System.out.println("Arm moving up to " + getDesiredPosition());
    setPIDUp();
    //setPosition(getRealPosition()+climberPosInc);
  }

  public void zero() {
    if (!m_limitSwitch.get()) {
      if (!printedZeroing) {
        //System.out.println("Elevator Zeroing! Old Start position " + startPosition + " New startPosition " )
      }
    }

  }


  @Override
  public void useOutput(double output, double setpoint) {
      // Use the output here
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return 0;
  }
}
