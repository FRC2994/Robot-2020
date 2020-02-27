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
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANDigitalInput.LimitSwitch;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import frc.utils.Constants;

public class Climber extends PIDSubsystem {

  private final WPI_VictorSPX m_motor;
  private final Encoder m_encoder;
  private final PIDController m_pid;
  private final DigitalInput m_limitSwitch;
  private final Solenoid m_solenoid;

  private final static double climberPosInc = 469;
  private final static double climberPosDec = -469;
  //ticks are 469 or 156
  //gearbox ratio

  private final double maxPosition = 20000;

  private double bottomPosition = 0;
  private double desiredPosition = 0; // Re-think this - should it be a setting
  private boolean foundBottom = false;


  public Climber() {
    super(
        // The PIDController used by the subsystem
        new PIDController(0, 0, 0));

      //assigning objects to variables
        this.m_motor = new WPI_VictorSPX(Constants.CAN_CLIMBER);
        this.m_motor.setNeutralMode(NeutralMode.Brake);
        this.m_encoder = new Encoder(Constants.DIO_CLIMB_ENC_CHN_A, Constants.DIO_CLIMB_ENC_CHN_B);
        this.m_encoder.reset();
        this.m_pid = new PIDController(0.1, 0, 0);
        this.m_limitSwitch = new DigitalInput(Constants.DIO_CLIMB_LIMIT);
        this.m_solenoid = new Solenoid(Constants.SOLENOID_PORT, Constants.PCM_CLIMB);
        m_pid.setTolerance(10); // tolerence is 10 ticks
        m_pid.enableContinuousInput(0, 10000);

  }

  public double getDesiredPosition() {
    return desiredPosition;
  }

  public double getCurrentPosition() {
    return m_encoder.get();
  }

  // public void setPIDLight() {
  //   m_pid.setPID(0.3, 0, 0); // May need adjustmnet
  // }

  // public void setPIDHeavy() { // May need adjustmnet
  //   m_pid.setPID(0.5, 0, 0);
  // }

  public boolean findBottom() {
    if (m_limitSwitch.get()) {
      foundBottom = true;
      bottomPosition = getCurrentPosition();
      System.out.println("Bottom found. Bottom position = " +bottomPosition);
    }  
    else {
      foundBottom = false;
      System.out.println("Bottom not found");
    }
    return foundBottom;
  }

  public void setPosition(double setPoint) {
    m_pid.setSetpoint(setPoint);
  }


  public void moveUp() {
    if (getCurrentPosition() < maxPosition) { // Current setting 5000 ticks. Needs tuning/real numbers.
        desiredPosition = getCurrentPosition() + climberPosInc;
        setPosition(desiredPosition);
        System.out.println("GOING UP");
    } 
      System.out.println("encValues " + m_encoder.get() );
  }


  public void moveDown() {
    if (m_limitSwitch.get() == false) { // Only move down if we haven't hit the limit switch.
      desiredPosition = getCurrentPosition() + climberPosDec;
      setPosition(desiredPosition);
      System.out.println("GOING DOWN");
    }
    System.out.println(m_encoder.get());
  }

  @Override
  public void useOutput(double output, double setpoint) {
      // Use the output here
      //m_motor.set(ControlMode.PercentOutput,output + m_feedForward.calculate(setpoint));
      double motorOutput = m_pid.calculate(getCurrentPosition());
      // System.out.println("Output:" + motorOutput);
      m_motor.set(motorOutput);
    }
  
  public void move(double output){
    m_motor.set(ControlMode.PercentOutput, output);
  }


  /*Main Code for Now*/
  public void openLoopUp() {
    if(m_encoder.get() < 11000){
      move(1);
      System.out.println(m_encoder.get());
    }
  }
  public void openLoopDown() {
    if(m_limitSwitch.get() == true){
      move(-1);
    }
    else{
      move(0);
      m_encoder.reset();
    }
    System.out.println(m_encoder.get());
  }
  public void stopMotor(){
    move(0);
  }


  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return getCurrentPosition();
  }
  @Override
  public void periodic() {
    super.periodic();
    // System.out.println("Current Position: " + getCurrentPosition() + " Desired Position: " + m_pid.getSetpoint());
  }
}
