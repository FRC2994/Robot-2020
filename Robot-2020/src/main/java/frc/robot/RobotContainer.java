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
import frc.subsystems.Drivetrain;
import frc.subsystems.ShooterWheel;

import frc.commands.DefaultDrive;
import frc.commands.Shoot;

/**
 * Add your docs here.
 */
public class RobotContainer {
    //Joystick and Gamepad
    public final Joystick joystick = new Joystick(0); 
    public final Joystick gamepad = new Joystick(1);
   
    //Subsystems
    // private final Drivetrain m_drivetrain = new Drivetrain();
    private final ShooterWheel m_shooterwheel = new ShooterWheel();
    
    //Joystick and Gamepad buttons
    // private final JoystickButton jsButnShifter = new JoystickButton(this.joystick, 12);
    private final JoystickButton trigger = new JoystickButton(this.joystick, 1);
    private final JoystickButton lvl1 = new JoystickButton(this.joystick, 12);
    private final JoystickButton lvl2 = new JoystickButton(this.joystick, 10);
    private final JoystickButton lvl3 = new JoystickButton(this.joystick, 8);
    

    //Contains subsystems, OI devices, and commands.
    public RobotContainer(){
        
        configureButtons();

        // m_drivetrain.setDefaultCommand(new DefaultDrive(m_drivetrain, joystick));
        m_shooterwheel.setDefaultCommand(new Shoot(m_shooterwheel, gamepad));
    }


    private void configureButtons()
    {
        //Instant commands
        // this.jsButnShifter.whenPressed(new InstantCommand(m_drivetrain::highGear, m_drivetrain));
        // this.jsButnShifter.whenReleased(new InstantCommand(m_drivetrain::lowGear, m_drivetrain));
        this.trigger.whenPressed(new InstantCommand(m_shooterwheel::toggle, m_shooterwheel));
        this.lvl1.whenPressed(new InstantCommand(m_shooterwheel::setLevelOne, m_shooterwheel));
        this.lvl2.whenPressed(new InstantCommand(m_shooterwheel::setLevelTwo, m_shooterwheel));
        this.lvl3.whenPressed(new InstantCommand(m_shooterwheel::setLevelThree, m_shooterwheel));
    }
}