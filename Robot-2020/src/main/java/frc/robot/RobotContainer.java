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
import frc.commands.SpinControlPanel;
import frc.commands.FindTarget;
import frc.commands.intakeArm;
import frc.commands.ControlPanelPiston;
import frc.commands.Shoot;

/**
 * Add your docs here.
 */
public class RobotContainer {    
    //Joystick and Gamepad
    public final Joystick joystick = new Joystick(0); 
    public final Joystick gamepad = new Joystick(1);
   
    //Subsystems
    private final Drivetrain drivetrain = new Drivetrain();
    // private final ControlPanel controlpanel = new ControlPanel(); 
    private final ShooterWheel shooterwheel = new ShooterWheel();
    private final Elevator elevator = new Elevator();
    // private final ShooterHood shooterhood = new ShooterHood();
    private final VisionArduino vision = new VisionArduino();
    private final Intake intake = new Intake();
    // private final Hopper hopper = new Hopper();
    // private final Climber climber = new Climber();
    
    //Joystick and Gamepad buttons
    // private final JoystickButton jsButnShifter = new JoystickButton(this.joystick, 12);
    //joystick
    private final JoystickButton jsButnIntakePowerCell = new JoystickButton(this.joystick, 1);
    private final JoystickButton jsButnRaiseAndLowerControlPanel = new JoystickButton(this.joystick, 3);
    private final JoystickButton jsButnRotationControl = new JoystickButton(this.joystick, 4);
    private final JoystickButton jsButnDriveHighAndLowGear = new JoystickButton(this.joystick, 5);
    private final JoystickButton jsButnDetectColour = new JoystickButton(this.joystick, 6);
    private final JoystickButton jsButnLowerClimb = new JoystickButton(this.joystick, 7);
    private final JoystickButton jsButnRaiseClimb = new JoystickButton(this.joystick, 8);
    private final JoystickButton jsButnClimbHeavyAndLightGear = new JoystickButton(this.joystick, 9);
  
    //gamepad
    private final JoystickButton gpButnShooter = new JoystickButton(this.gamepad, 1);
    private final JoystickButton gpButnElevator = new JoystickButton(this.gamepad, 2);
    private final JoystickButton gpButnServoDecrement = new JoystickButton(this.gamepad, 3);
    private final JoystickButton gpButnServoIncrement = new JoystickButton(this.gamepad, 4);
    private final JoystickButton gpButnIntakeDownAndUp = new JoystickButton(this.gamepad, 7);
    private final JoystickButton gpButnRunPixyCam = new JoystickButton(this.gamepad, 8);
    private final JoystickButton gpButnHopperDisturber = new JoystickButton(this.gamepad, 9);
    private final JoystickButton gpButnShoot = new JoystickButton(this.gamepad, 6);
    
    //Contains subsystems, OI devices, and commands.
    public RobotContainer(){
        configureButtons();

        this.drivetrain.setDefaultCommand(new DefaultDrive(drivetrain, joystick));
        this.shooterwheel.setDefaultCommand(new ShootSpeed(this.shooterwheel, this.gamepad));
        // this.controlpanel.setDefaultCommand(new GoToColor(this.controlpanel));
    }


    private void configureButtons() {
        //Instant commands
        /*GEAR SHIFTER*/
        // this.jsButnShifter.whenPressed(new InstantCommand(m_drivetrain::highGear, m_drivetrain));
        // this.jsButnShifter.whenReleased(new InstantCommand(m_drivetrain::lowGear, m_drivetrain));
        /*SHOOTER*/
        this.gpButnShooter.whileHeld(new InstantCommand(shooterwheel::shoot, shooterwheel));
        this.gpButnShooter.whenReleased(new InstantCommand(shooterwheel::stopMotor, shooterwheel));
        this.gpButnShoot.whileHeld(new Shoot(elevator, shooterwheel));

        /*SHOOTER HOOD*/
        // this.gpButnServoIncrement.whileHeld(new InstantCommand(shooterhood::ServoInc, shooterhood));
        // this.gpButnServoDecrement.whileHeld(new InstantCommand(shooterhood::ServoDec, shooterhood));
        /*ELEVATOR*/
        this.gpButnElevator.whileHeld(new InstantCommand(elevator::startMotor, elevator));
        this.gpButnElevator.whenReleased(new InstantCommand(elevator::stopMotor, elevator));
        /*CONTROL PANEL*/
        // this.jsButnDetectColour.whileHeld(new GoToColor(controlpanel));
        // this.jsButnRotationControl.whileHeld(new SpinControlPanel(controlpanel));
        // this.jsButnRaiseAndLowerControlPanel.whenPressed(new ControlPanelPiston(controlpanel));
        /*CLIMBER*/
        // this.jsButnRaiseClimb.whileHeld(new InstantCommand(climber:: moveUp, climber));
        // this.jsButnLowerClimb.whileHeld(new InstantCommand(climber:: moveDown, climber));
        // this.jsButnClimbHeavyAndLightGear.whileHeld(new InstantCommand(climber:: setPIDLight, climber));
        // this.jsButnClimbHeavyAndLightGear.whenReleased(new InstantCommand(climber:: setPIDHeavy, climber));
        /*HOPPER*/
        // this.gpButnHopperDisturber.whileHeld(new InstantCommand(hopper:: HopperDisturberExtend, hopper));
        // this.gpButnHopperDisturber.whenReleased(new InstantCommand(hopper:: HopperDisturberIntake, hopper));
        /*DRIVETRAIN*/
        this.jsButnDriveHighAndLowGear.whileHeld(new InstantCommand(drivetrain::highGear, drivetrain));
        this.jsButnDriveHighAndLowGear.whenReleased(new InstantCommand(drivetrain::lowGear, drivetrain));
        /*INTAKE*/
        this.jsButnIntakePowerCell.whileHeld(new InstantCommand(intake::motorOn, intake));
        this.jsButnIntakePowerCell.whenReleased(new InstantCommand(intake::motorOff, intake));
        this.gpButnIntakeDownAndUp.whenPressed(new intakeArm(intake));
        /*PIXYCAM*/
        this.gpButnRunPixyCam.whileHeld(new FindTarget(this.vision, this.drivetrain));
    }
}
