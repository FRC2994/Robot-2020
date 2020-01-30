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


//Commands Imports
import frc.commands.DefaultDrive;
import frc.commands.Shoot;
import frc.commands.DetectColor;
import frc.commands.GoToColor;;

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
    private final Elevator elevator = new Elevator();
    private final ShooterHood shooterhood = new ShooterHood();
    
    //Joystick and Gamepad buttons
    // private final JoystickButton jsButnShifter = new JoystickButton(this.joystick, 12);
    private final JoystickButton trigger = new JoystickButton(this.joystick, 1);
    private final JoystickButton lvl1 = new JoystickButton(this.joystick, 12);
    private final JoystickButton lvl2 = new JoystickButton(this.joystick, 10);
    private final JoystickButton lvl3 = new JoystickButton(this.joystick, 8);
    private final JoystickButton ServoIncrement = new JoystickButton(this.joystick, 3);
    private final JoystickButton ServoDecrement = new JoystickButton(this.joystick, 4);
    // private final JoystickButton gpButnElevator = new JoystickButton(this.gamepad, 2);

    //Contains subsystems, OI devices, and commands.
    public RobotContainer(){
        configureButtons();

        this.drivetrain.setDefaultCommand(new DefaultDrive(drivetrain, joystick));
        this.shooterwheel.setDefaultCommand(new Shoot(this.shooterwheel, this.gamepad));
        this.controlpanel.setDefaultCommand(new GoToColor(this.controlpanel));
    }


    private void configureButtons() {
        //Instant commands
        /*GEAR SHIFTER*/
        // this.jsButnShifter.whenPressed(new InstantCommand(m_drivetrain::highGear, m_drivetrain));
        // this.jsButnShifter.whenReleased(new InstantCommand(m_drivetrain::lowGear, m_drivetrain));
        /*SHOOTER*/
        this.trigger.whenPressed(new InstantCommand(shooterwheel::toggle, shooterwheel));
        this.lvl1.whenPressed(new InstantCommand(shooterwheel::setLevelOne, shooterwheel));
        this.lvl2.whenPressed(new InstantCommand(shooterwheel::setLevelTwo, shooterwheel));
        this.lvl3.whenPressed(new InstantCommand(shooterwheel::setLevelThree, shooterwheel));
        /*SHOOTER HOOD*/
        this.ServoIncrement.whenPressed(new InstantCommand(shooterhood::ServoInc, shooterhood));
        this.ServoDecrement.whenPressed(new InstantCommand(shooterhood::ServoDec, shooterhood));
        /*ELEVATOR*/
        // this.gpButnElevator.whenPressed(new InstantCommand(elevator:: ascendingSpeed, elevator));
        // this.gpButnElevator.whenReleased(new InstantCommand(elevator:: stopMotor, elevator));
    }
}
