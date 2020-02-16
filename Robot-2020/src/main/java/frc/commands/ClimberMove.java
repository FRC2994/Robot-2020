/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.subsystems.Climber;
import edu.wpi.first.wpilibj.Joystick;

public class ClimberMove extends CommandBase {
  private Climber climb;
  private Joystick js;
  double kP = 0.5;
  double kI = 0;
  double kD = 0;

  double derivative;
  double integral;
  double dt;
  double error;
  double lastError;
  double currentPosition;
  double setpoint;
  double lastTimestamp;
  double threshhold = 50;

  public ClimberMove(Climber _climb, Joystick _js) {
    climb = _climb;
    js = _js;
    addRequirements(climb);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    derivative = 0;
    dt = 0;
    error = 0;
    lastError = 0;
    lastTimestamp = 0;
    integral = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(js.getRawButton(8)){
      climb.moveUp();
    }
    if(js.getRawButton(7)) {
      climb.moveDown();
    }

    currentPosition = climb.getCurrentPosition();
    setpoint = climb.getDesiredPosition();
    error = setpoint - currentPosition;
    dt = Timer.getFPGATimestamp() - lastTimestamp;

    if (Math.abs(error) < threshhold) {
      integral += error * dt;
    }

    derivative = (error - lastError) / dt;

    double output = (kP*error) + (kI*integral) + (kD*derivative);
    climb.move(output);
    lastTimestamp = Timer.getFPGATimestamp();
    lastError = error;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
