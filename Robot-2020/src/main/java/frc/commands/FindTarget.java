/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.subsystems.VisionArduino;
import frc.subsystems.Drivetrain;

public class FindTarget extends CommandBase {
  private VisionArduino arduino;
  private Drivetrain drive;
  private int counter;
  private int maxCount;
  private boolean finished;
  public FindTarget(VisionArduino target, Drivetrain dt) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.arduino = target;
    this.drive = dt;
    addRequirements(this.arduino);
    addRequirements(this.drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    counter = 0;
    maxCount = 50;
    finished = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("[FindTarget] execute");

    String data = arduino.getX();

    if(data.equals("LEFT")){
      System.out.println("GO LEFT");
      drive.arcadeDrive(0, -0.3);
    }
    else if(data.equals("CENTER")){
      System.out.println("STOP");
      drive.arcadeDrive(0, 0);
      counter++;
    }
    else if(data.equals("RIGHT")){
      System.out.println("GO RIGHT");
      drive.arcadeDrive(0, 0.3);
    }

    if(counter == 25) {
      finished = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}
