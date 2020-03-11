/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.subsystems.Elevator;
import edu.wpi.first.wpilibj.Joystick;
import frc.subsystems.Intake;
import frc.subsystems.Agitator;

public class IntakeAndElevator extends CommandBase {
  Elevator elevator;
  Joystick gamepad;
  Intake intake;
  Agitator agitator;
  boolean elevatorStatus;
  public IntakeAndElevator(Elevator _elevator, Joystick _gamepad, Intake _intake, Agitator _agitator) {
    // Use addRequirements() here to declare subsystem dependencies.
    elevator = _elevator;
    gamepad = _gamepad;
    intake = _intake;
    agitator = _agitator;
    addRequirements(elevator);
    addRequirements(intake);
    addRequirements(agitator);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    intake.intakeSpeed(gamepad.getRawAxis(3));
    if(Math.abs(gamepad.getRawAxis(3)) > 0.1){
      if(elevator.isBallIn() == false){
        elevator.semiIntake();
        agitator.motorOn();
      } else{
        elevator.stopMotor();
      }
      elevatorStatus = true;
    } else {
      if(elevatorStatus == true){
        elevator.stopMotor();
        elevatorStatus = false;
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    elevator.stopMotor();
    intake.motorOff();
    agitator.motorOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
