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
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShooterWheel extends SubsystemBase {
  private CANSparkMax Shooter;
  // private CANEncoder enc;
  // private CANPIDController pid;
  private double maxRPM = 5000;
  private double increments = 100; //Increments or decrements by 100
  private double desiredRPM;
  private double actualRPM;
  public boolean status;
  private double[] set = {3000, 3500, 4500}; //The 3 sets and their set speeds
  public int currentSet;

  private int level;

  public ShooterWheel() {
	this.level = 1;
	
	//Initialize the motor objects
	Shooter = new CANSparkMax(1, MotorType.kBrushless);
	// enc = Shooter.getEncoder();
	// pid = Shooter.getPIDController();

	//Ensures the motor stops
	stopMotor();

	Shooter.restoreFactoryDefaults();
	Shooter.setIdleMode(IdleMode.kCoast);
	this.Shooter.setInverted(false);
  }


  //Code that stops the motor
  public void stopMotor() {
	//Stops the motor
	Shooter.stopMotor();

	//Variable changes:
	desiredRPM = 0;
	// status = false;
  }

  //Code that will shoot
  public void shoot(){
	//The function needs an input of which set it wants, based on button input
	// currentSet = desiredSet;
	// status = true;
	// //Sets global desiredRPM based on the set number from the set
	// desiredRPM = set[desiredSet-1];

	// //Actually sets the velocity to the desired RPM
	// pid.setReference(desiredRPM, ControlType.kVelocity);
	if (this.status) {
	  this.Shooter.setVoltage((this.level*4)*-1);
	} else {
	  this.Shooter.setVoltage(0);
	}
	
  }

  public void setLevelOne() {
	this.level = 1;
  }

  public void setLevelTwo() {
	this.level = 2;
  }

  public void setLevelThree() {
	this.level = 3;
  }

  public void toggle() {
	System.out.println("toggling...");
	this.status = !this.status;
  }

  //Increments the speed
  public void incrementSpeed()
  {
	//Checks if the rpm isnt at max and if the motor is even on
	if(desiredRPM != maxRPM && status == true)
	{
	  //increases the rpm and sets it
	  desiredRPM = desiredRPM + increments;
	  // pid.setReference(desiredRPM, ControlType.kVelocity);
	}
  }

  //Decrements the speed
  public void decrementSpeed()
  {
	//Checks if the rpm is low and if the motor is even on
	if(desiredRPM != 500 && status == true)
	{
	  //decrease the rpm and set it
	  desiredRPM = desiredRPM - increments;
	  // pid.setReference(desiredRPM, ControlType.kVelocity);
	}
  }

  //Prints the speeds
  public void printSpeeds()
  {
	// actualRPM = enc.getVelocity();
	SmartDashboard.putNumber("Desired RPM: ", desiredRPM);
	SmartDashboard.putNumber("Actual RPM:", actualRPM);
  }

  @Override
  public void periodic() {
	printSpeeds();
  }
}