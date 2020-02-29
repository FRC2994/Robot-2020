/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
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
import frc.commands.IntakeTrigger;
import frc.commands.ShooterHoodChange;
import frc.commands.Autonomous.SampleAuto;
import frc.commands.Autonomous.AutoShooting;

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
    private final ShooterHood shooterhood = new ShooterHood();
    private final VisionArduino vision = new VisionArduino();
    private final Intake intake = new Intake();
    //TODO: figure out if the hopper even needs to be a subsystem now
    // private final Hopper hopper = new Hopper();
    private final Climber climber = new Climber();
    
    //Joystick and Gamepad buttons
    //joystick
    private final JoystickButton jsButnIntakePowerCell           = new JoystickButton(this.joystick, 1);
    private final JoystickButton gpButnRunPixyCam                = new JoystickButton(this.joystick, 2);
    private final JoystickButton jsButnRaiseAndLowerControlPanel = new JoystickButton(this.joystick, 3);
    private final JoystickButton jsButnRotationControl           = new JoystickButton(this.joystick, 4);
    private final JoystickButton jsButnReverse                   = new JoystickButton(this.joystick, 5);
    private final JoystickButton jsButnDetectColour              = new JoystickButton(this.joystick, 6);
    private final JoystickButton jsButnRaiseClimb                = new JoystickButton(this.joystick, 7);
    private final JoystickButton jsButnLowerClimb                = new JoystickButton(this.joystick, 8);
    private final JoystickButton jsButnShifter                   = new JoystickButton(this.joystick, 12);
  
    //gamepad
    private final JoystickButton gpButnShoot                     = new JoystickButton(this.gamepad, 1);
    private final JoystickButton gpButnManualElevator            = new JoystickButton(this.gamepad, 2);
    private final JoystickButton gpButnIntakeDownAndUp           = new JoystickButton(this.gamepad, 4);
    private final JoystickButton gpButnShooter                   = new JoystickButton(this.gamepad, 6);
    //Contains subsystems, OI devices, and commands.

    public RobotContainer(){
        configureButtons();
    }


    private void configureButtons() {
        //Instant commands
        /*GEAR SHIFTER*/
        this.jsButnShifter.whenPressed(new InstantCommand(drivetrain::highGear, drivetrain));
        this.jsButnShifter.whenReleased(new InstantCommand(drivetrain::lowGear, drivetrain));
        this.jsButnReverse.whenPressed(new InstantCommand(drivetrain::enableReverse, drivetrain));
        this.jsButnReverse.whenReleased(new InstantCommand(drivetrain::disableReverse, drivetrain));
        /*SHOOTER*/
        this.gpButnShoot.whileHeld(new InstantCommand(shooterwheel::shoot, shooterwheel)); //Manual
        this.gpButnShoot.whenReleased(new InstantCommand(shooterwheel::stopMotor, shooterwheel));
        this.gpButnShooter.whileHeld(new Shoot(elevator, shooterwheel)); //With the elevator
        /*ELEVATOR*/
        this.gpButnManualElevator.whileHeld(new InstantCommand(elevator::startMotor, elevator)); //Manual
        this.gpButnManualElevator.whenReleased(new InstantCommand(elevator::stopMotor, elevator));
        /*CONTROL PANEL*/
        //TODO: test this
        // this.jsButnDetectColour.whileHeld(new GoToColor(controlpanel));
        // this.jsButnRotationControl.whileHeld(new SpinControlPanel(controlpanel));
        // this.jsButnRaiseAndLowerControlPanel.whenPressed(new ControlPanelPiston(controlpanel));
        /*CLIMBER*/
        this.jsButnRaiseClimb.whileHeld(new InstantCommand(climber::openLoopUp, climber));
        this.jsButnRaiseClimb.whenReleased(new InstantCommand(climber::stopMotor, climber));
        this.jsButnLowerClimb.whileHeld(new InstantCommand(climber::openLoopDown, climber));
        this.jsButnLowerClimb.whenReleased(new InstantCommand(climber::stopMotor, climber));
        /*HOPPER*/
        // this.gpButnHopperDisturber.whileHeld(new InstantCommand(hopper:: HopperDisturberExtend, hopper));
        // this.gpButnHopperDisturber.whenReleased(new InstantCommand(hopper:: HopperDisturberIntake, hopper));
        /*INTAKE*/
        // Removed this, this is a trigger now
        // this.jsButnIntakePowerCell.whileHeld(new InstantCommand(intake::motorOn, intake));
        // this.jsButnIntakePowerCell.whenReleased(new InstantCommand(intake::motorOff, intake));
        this.gpButnIntakeDownAndUp.whenPressed(new intakeArm(intake));
        /*PIXYCAM*/
        this.gpButnRunPixyCam.whileHeld(new FindTarget(this.vision, this.drivetrain));
    }


    /*AUTONOMOUS STUFF*/
    // Command sampleAuto = new SampleAuto(drivetrain, elevator, intake, vision, shooterwheel);
    
    public Command getAutoCommand(int whichAuto) {
        Command selectedAuto;
        switch(whichAuto){
            /*Lines up to target, shoots, goes back to get more balls*/
            case 0:     selectedAuto = new AutoShooting(drivetrain, elevator, vision, shooterwheel);
                        break;
            /*TODO: figure out?*/
            case 1:     selectedAuto = new SampleAuto(drivetrain, elevator, intake, vision, shooterwheel);
                        break;
            /*TODO: figure out?*/
            case 2:     selectedAuto = null;
                        break;
            default:    selectedAuto = new AutoShooting(drivetrain, elevator, vision, shooterwheel);
                        break;
        }
        return selectedAuto;
    }

    public void enableDefaultCommands() {
        this.drivetrain.setDefaultCommand(new DefaultDrive(drivetrain, joystick));
        this.shooterwheel.setDefaultCommand(new ShootSpeed(this.shooterwheel, this.gamepad));
        // this.controlpanel.setDefaultCommand(new GoToColor(this.controlpanel));
        // climber.enable();
        this.intake.setDefaultCommand(new IntakeTrigger(intake, gamepad));
        this.shooterhood.setDefaultCommand(new ShooterHoodChange(shooterhood, gamepad));
    }

}
