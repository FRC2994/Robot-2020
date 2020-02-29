/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.utils.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShooterWheel extends SubsystemBase {
  private CANSparkMax Shooter;
  private CANEncoder enc;
  private CANPIDController pid;
  private double maxRPM = 5000;
  private double increments = 100; //Increments or decrements by 100
  private double desiredRPM;
  private double actualRPM;
  public boolean status;
  public int currentSet;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;

  public ShooterWheel() {
	
	//Initialize the motor objects
	Shooter = new CANSparkMax(Constants.CAN_SHOOTER_WHEEL, MotorType.kBrushless);
	enc = Shooter.getEncoder();
	pid = Shooter.getPIDController();


	Shooter.restoreFactoryDefaults();
	Shooter.setIdleMode(IdleMode.kCoast);
	this.Shooter.setInverted(true);
	desiredRPM = 5000;
	stopMotor();
	//Note: https://www.chiefdelphi.com/t/tune-rev-spark-max-pid-for-shooter/379068
	kP = 0; 
	kI = 0;
	kD = 0; 
	kIz = 0; 
	kFF = 1; 
	kMaxOutput = 1; 
	kMinOutput = -1;
	maxRPM = 5400;
	
	// set PID coefficients
	pid.setP(kP);
	pid.setI(kI);
	pid.setD(kD);
	pid.setIZone(kIz);
	pid.setFF(kFF);
	pid.setOutputRange(kMinOutput, kMaxOutput);
	
	// display PID coefficients on SmartDashboard
	SmartDashboard.putNumber("P Gain", kP);
	SmartDashboard.putNumber("I Gain", kI);
	SmartDashboard.putNumber("D Gain", kD);
	SmartDashboard.putNumber("I Zone", kIz);
	SmartDashboard.putNumber("Feed Forward", kFF);
	SmartDashboard.putNumber("Max Output", kMaxOutput);
	SmartDashboard.putNumber("Min Output", kMinOutput);
	SmartDashboard.putNumber("Set Velocity", 0);
  }


  //Code that stops the motor
  public void stopMotor() {
	//Stops the motor
	Shooter.stopMotor();

	//Variable changes:
	status = false;
  }

  //Code that will shoot
  public void shoot(){
	status = true;

	// System.out.println("Shoot");
	//Actually sets the velocity to the desired RPM
	// Shooter.set(1);
	pid.setReference(5000, ControlType.kVelocity);
  }

  //Increments the speed
  public void incrementSpeed()
  {
	//Checks if the rpm isnt at max and if the motor is even on
	if(desiredRPM != maxRPM && status == true)
	{
		System.out.println("Incremented");
	  	//increases the rpm and sets it
	  	desiredRPM = desiredRPM + increments;
	  	// pid.setReference(desiredRPM, ControlType.kVelocity);
	}
  }

  public double getRPM() {
	  return enc.getVelocity();
  }
  //Decrements the speed
  public void decrementSpeed()
  {
	//Checks if the rpm is low and if the motor is even on
	if(desiredRPM != 500 && status == true)
	{
		System.out.println("Decremented");
	  	//decrease the rpm and set it
	  	desiredRPM = desiredRPM - increments;
	  	// pid.setReference(desiredRPM, ControlType.kVelocity);
	}
  }

  //Prints the speeds
  public void printSpeeds()
  {
	actualRPM = enc.getVelocity();
	SmartDashboard.putNumber("Desired RPM: ", desiredRPM);
	SmartDashboard.putNumber("Actual RPM:", actualRPM);
  }

  /*
	  Tru FF to 0.00000481 
	  and P gain to 0.00002.
	  If not, set PID to 0, and keep changing FF till it reaches the goal,
	  then once that is complete, increase the P, start very very low, then increase I afterwards if there is a bit of error
  */
  public void tunePID() {
	    // read PID coefficients from SmartDashboard
		double p = SmartDashboard.getNumber("P Gain", 0);
		double i = SmartDashboard.getNumber("I Gain", 0);
		double d = SmartDashboard.getNumber("D Gain", 0);
		double iz = SmartDashboard.getNumber("I Zone", 0);
		double ff = SmartDashboard.getNumber("Feed Forward", 0);
		double max = SmartDashboard.getNumber("Max Output", 0);
		double min = SmartDashboard.getNumber("Min Output", 0);
		double setPoint = SmartDashboard.getNumber("Set Velocity", 0);
	
		// if PID coefficients on SmartDashboard have changed, write new values to controller
		if((p != kP)) { pid.setP(p); kP = p; }
		if((i != kI)) { pid.setI(i); kI = i; }
		if((d != kD)) { pid.setD(d); kD = d; }
		if((iz != kIz)) { pid.setIZone(iz); kIz = iz; }
		if((ff != kFF)) { pid.setFF(ff); kFF = ff; }
		if((max != kMaxOutput) || (min != kMinOutput)) { 
		  pid.setOutputRange(min, max); 
		  kMinOutput = min; kMaxOutput = max; 
		}
	
		pid.setReference(setPoint, ControlType.kVelocity);
		
		SmartDashboard.putNumber("SetPoint", setPoint);
		SmartDashboard.putNumber("ProcessVariable", enc.getVelocity());
	}
  

  @Override
  public void periodic() {
	printSpeeds();
	tunePID();
  }
}
