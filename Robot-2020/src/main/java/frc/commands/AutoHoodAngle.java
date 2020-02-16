/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.subsystems.VisionArduino;
import frc.utils.Constants.ShootingTable;
import frc.subsystems.ShooterHood;
import frc.subsystems.ShooterWheel;

public class AutoHoodAngle extends CommandBase {
  private VisionArduino PixyCam;
  private ShooterHood hood;
  private double distance;
  ShootingTable table;

  public AutoHoodAngle(VisionArduino AutoSpeed, ShooterWheel ShooterSpeed) {
    // Use addRequirements() here to declare subsystem dependencies.
    PixyCam = AutoSpeed;
    addRequirements(PixyCam);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    distance = this.PixyCam.getDistance();
    hood.setAngle(table.getHoodSetting(distance));
 
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
