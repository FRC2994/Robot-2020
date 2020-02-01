/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.subsystems.VisionArduino;

public class FindTarget extends CommandBase {
  private VisionArduino arduino;
  public FindTarget(VisionArduino target) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.arduino = target;
    addRequirements(this.arduino);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("[FindTarget] execute");

    String data = arduino.read();

    if(data.equals("LEFT")){
      System.out.println("GO LEFT");
    }
    else if(data.equals("CENTER")){
      System.out.println("STOP");
    }
    else if(data.equals("RIGHT")){
      System.out.println("GO RIGHT");
    }
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
