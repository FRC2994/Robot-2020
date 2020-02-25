/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;

//Command Imports
import frc.commands.ControlPanelPiston;
import frc.commands.intakeArm;
import frc.commands.SpinControlPanel;
//Subsystem Imports
import frc.subsystems.Climber;
import frc.subsystems.ControlPanel;
import frc.subsystems.Elevator;
import frc.subsystems.Intake;
import frc.subsystems.ShooterWheel;
// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class TestButton extends SequentialCommandGroup {
  /**
   * Creates a new TestButton.
   */
  public TestButton(Elevator elevator, Intake intake, ShooterWheel shooterwheel, ControlPanel controlpanel, Climber climber) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super();
    addCommands(
      //tests the elevator
      new InstantCommand(elevator:: startMotor, elevator),
      








    )
  }
}