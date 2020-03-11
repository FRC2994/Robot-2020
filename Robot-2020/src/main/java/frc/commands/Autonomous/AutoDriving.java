/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.subsystems.Drivetrain;

public class AutoDriving extends CommandBase {
  private Drivetrain drive;
  int counter;
  boolean isFinished;
  public AutoDriving(Drivetrain _drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    drive = _drive;
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    counter = 0;
    isFinished = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    counter++;
    if(counter == 170){
      isFinished = true;
    }
    else{
      drive.arcadeDrive(0.65, 0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isFinished;
  }
}
