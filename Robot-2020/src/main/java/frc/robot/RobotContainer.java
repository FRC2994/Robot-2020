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
    private final JoystickButton jsButnIntakePowerCell = new JoystickButton(this.joystick, 1);
    private final JoystickButton jsButnSpinControlPanel = new JoystickButton(this.joystick, 4);
    private final JoystickButton jsButnDriveHighAndLowGear = new JoystickButton(this.joystick, 5);
    private final JoystickButton jsButnDetectColour = new JoystickButton(this.joystick, 6);
    private final JoystickButton jsButnLowerClimb = new JoystickButton(this.joystick, 7);
    private final JoystickButton jsButnRaiseClimb = new JoystickButton(this.joystick, 8);
    private final JoystickButton jsButnOpenClaws = new JoystickButton(this.joystick, 9);
    private final JoystickButton jsButnCloseClaws = new JoystickButton(this.joystick, 10);
    private final JoystickButton gpButnShooter = new JoystickButton(this.gamepad, 1);
    private final JoystickButton gpButnElevator = new JoystickButton(this.gamepad, 2);
    private final JoystickButton gpButnServoDecrement = new JoystickButton(this.gamepad, 3);
    private final JoystickButton gpButnServoIncrement = new JoystickButton(this.gamepad, 4);
    private final JoystickButton gpButnHighSpeedShooter = new JoystickButton(this.gamepad, 5);
    private final JoystickButton gpButnLowSpeedShooter = new JoystickButton(this.gamepad, 6);
    private final JoystickButton gpButnIntakeDownAndUp = new JoystickButton(this.gamepad, 7);
    private final JoystickButton gpButnHopperDisturber = new JoystickButton(this.gamepad, 9);
    

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
        this.gpButnShooter.whenPressed(new InstantCommand(shooterwheel::toggle, shooterwheel));
        /*SHOOTER HOOD*/
        this.gpButnServoIncrement.whenPressed(new InstantCommand(shooterhood::ServoInc, shooterhood));
        this.gpButnServoDecrement.whenPressed(new InstantCommand(shooterhood::ServoDec, shooterhood));
        /*ELEVATOR*/
        this.gpButnElevator.whenPressed(new InstantCommand(elevator:: ascendingSpeed, elevator));
        this.gpButnElevator.whenReleased(new InstantCommand(elevator:: stopMotor, elevator));
        /*CONTROL PANEL*/
        this.jsButnDetectColour.whileHeld(new GoToColor(controlpanel));

        /*CLIMBER*/

        /*HOPPER*/
    }
}
