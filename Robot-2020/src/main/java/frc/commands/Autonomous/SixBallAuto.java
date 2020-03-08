/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.subsystems.Drivetrain;
import frc.subsystems.Elevator;
import frc.subsystems.Intake;
import frc.subsystems.ShooterWheel;
import frc.subsystems.VisionArduino;

import frc.commands.Shoot;
import frc.commands.DriveRotation;
import frc.commands.DriveToPosition;
import frc.commands.FindTarget;
import frc.commands.RunIntake;
import frc.commands.Autonomous.AutoDriving;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class SixBallAuto extends SequentialCommandGroup {
  /**
   * Creates a new SixBallAuto.
   */
  public SixBallAuto(Drivetrain drive, Elevator elevator, Intake intake, ShooterWheel shooter, VisionArduino vision) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super();
    addCommands(
      new Shoot(elevator, shooter).withTimeout(3.5),
      new DriveRotation(drive, -88),
      new DriveToPosition(drive, -39),
      // new WaitCommand(0.4),
      new DriveRotation(drive, -1),
      new InstantCommand(intake::lowerIntake, intake),
      // new RunIntake(intake).raceWith()
      new AutoDriving(drive).alongWith(new RunIntake(intake).withTimeout(4.2)),
      new InstantCommand(intake::motorOff, intake),
      new DriveToPosition(drive, 80),
      new FindTarget(vision, drive),
      new Shoot(elevator, shooter).withTimeout(4)
    );
  }
}
