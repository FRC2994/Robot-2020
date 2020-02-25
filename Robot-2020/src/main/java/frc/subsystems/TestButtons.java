/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

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
public class TestButtons extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
