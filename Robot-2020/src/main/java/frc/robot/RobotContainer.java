/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.subsystems.ControlPanel;
import frc.subsystems.Drivetrain;
import frc.subsystems.ShooterWheel;
import frc.subsystems.Elevator;
import frc.subsystems.ShooterHood;
import frc.subsystems.VisionArduino;
import frc.subsystems.intakeSubsystem;

//Commands Imports
import frc.commands.DefaultDrive;
import frc.commands.Shoot;
import frc.commands.FindTarget;
import frc.commands.AlignPID;

/**
 * Add your docs here.
 */
public class RobotContainer {    
    //Joystick and Gamepad
    public final Joystick joystick = new Joystick(0); 
    public final Joystick gamepad = new Joystick(1);
   
    //Subsystems
    private final Drivetrain drivetrain = new Drivetrain();
    private final ControlPanel controlpanel = new ControlPanel(); 
    private final ShooterWheel shooterwheel = new ShooterWheel();
    private final VisionArduino vision = new VisionArduino();

    
    //Joystick and Gamepad buttons
    private final JoystickButton jsButnShifter = new JoystickButton(this.joystick, 12);
    

    //Contains subsystems, OI devices, and commands.
    public RobotContainer(){
        configureButtons();

        this.drivetrain.setDefaultCommand(new DefaultDrive(drivetrain, joystick));
    }


    private void configureButtons() {
        //Instant commands
        /*GEAR SHIFTER*/
        this.jsButnShifter.whenPressed(new InstantCommand(drivetrain::highGear, drivetrain));
        this.jsButnShifter.whenReleased(new InstantCommand(drivetrain::lowGear, drivetrain));
    }
}
