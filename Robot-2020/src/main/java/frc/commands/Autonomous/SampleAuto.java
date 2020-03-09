/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
// import frc.commands.FindTarget;

import frc.subsystems.Drivetrain;
import frc.subsystems.Elevator;
import frc.subsystems.Intake;
import frc.subsystems.VisionArduino;
import frc.subsystems.ShooterWheel;
// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class SampleAuto extends SequentialCommandGroup {
  /**
   * Creates a new SampleAuto.
   */
  public SampleAuto(Drivetrain drive, Elevator elv, Intake intake, VisionArduino vision, ShooterWheel shooter) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super();
    addCommands(
      //Aligns the robot
      // new FindTarget(vision, drive),
      //Start Shooter motor with the elevator
      new InstantCommand(elv::startMotor, elv),
      new InstantCommand(shooter::shoot, shooter),
      //Waits 3 seconds for the balls to be shot
      new WaitCommand(3),
      new InstantCommand(elv::stopMotor, elv),
      new InstantCommand(shooter::stopMotor, shooter),
      //Drives back
      new StartEndCommand(
        () -> drive.arcadeDrive(-0.5, 0), 
        () -> drive.arcadeDrive(0,0),
        drive)
        .beforeStarting(drive::resetEncoders)
        .withTimeout(1.5),        
      //Lowers Intake
      new InstantCommand(intake::lowerIntake, intake),
      new WaitCommand(0.5),
      new InstantCommand(intake::motorOn, intake),
      //Drives a bit to intake the ball
      new StartEndCommand(
        () -> drive.arcadeDrive(-0.5, 0), 
        () -> drive.arcadeDrive(0,0),
        drive)
        .beforeStarting(drive::resetEncoders)
        .withTimeout(1.5),

      new InstantCommand(intake::motorOff, intake),
      new WaitCommand(0.5),
      new InstantCommand(intake::raiseIntake, intake)
    );
  }
}
