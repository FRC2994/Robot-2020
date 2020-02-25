/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import org.graalvm.compiler.hotspot.replacements.HubGetClassNode;

//Subsystems Imports
import frc.subsystems.ControlPanel;
import frc.subsystems.Drivetrain;
import frc.subsystems.ShooterWheel;
import frc.subsystems.Elevator;
import frc.subsystems.ShooterHood;
import frc.subsystems.VisionArduino;
import frc.subsystems.Intake;
import frc.subsystems.Hopper;
import frc.subsystems.Climber;

//Commands Imports
import frc.commands.DefaultDrive;
import frc.commands.ShootSpeed;
import frc.commands.GoToColor;
import frc.commands.SampleAuto;
import frc.commands.SpinControlPanel;
import frc.commands.FindTarget;
import frc.commands.intakeArm;
import frc.commands.ControlPanelPiston;
import frc.commands.Shoot;
import frc.commands.IntakeTrigger;

/**
 * Add your docs here.
 */
public class TestSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands
  boolean elevatorTest = false;
  boolean intakeTest = false;
  boolean shooterWheelTest = false;   
  boolean shooterHoodTest = false;
  
  public void elevatorTest(){
  
  if (elevatorTest == true){
    System.out.println("Elevator Working");
  }
  else if (elevatorTest == false){
    System.out.println("Elevator failed");
    }
  }

  public void intakeTest(){

  if (intakeTest == true){
    System.out.println("Intake Working");
  }
  else if (intakeTest == false){
      System.out.println("Intake failed");
    }
  }

  public void shooterWheelTest(){

  if (shooterWheelTest == true){
      System.out.println("Shooter Wheel Working");
    }
  else if (shooterWheelTest == false){
        System.out.println("Shooter Wheel failed");
      } 
  }

  public void shooterHoodTest(){

  if (shooterHoodTest == true){
        System.out.println("Shooter Hood Working");
      }
  else if (shooterHoodTest == false){
          System.out.println("Shooter Hood failed");
        } 
  }

  public void ControlPanelTest(){
    boolean elevatorTest = false;
    motorcontroller.set(ControlMode.PercentOutput, 0.6);
  
    if (elevatorTest == true){
      
      System.out.println("Elevator Working");
    }
    else if (elevatorTest == false){
      System.out.println("Elevator failed");

    }

    public void ClimberTest(){
      boolean elevatorTest = false;
      motorcontroller.set(ControlMode.PercentOutput, 0.6);
    
      if (elevatorTest == true){
        
        System.out.println("Elevator Working");
      }
      else if (elevatorTest == false){
        System.out.println("Elevator failed");
      }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
