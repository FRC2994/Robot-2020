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
import frc.subsystems.ShooterWheel;

public class SpeedAutoShooter extends CommandBase {
  private VisionArduino PixyCam;
  private ShooterWheel Shooter;
  private double distance;
  ShootingTable table;

  public SpeedAutoShooter(VisionArduino AutoSpeed, ShooterWheel ShooterSpeed) {
    // Use addRequirements() here to declare subsystem dependencies.
    Shooter = ShooterSpeed;
    PixyCam = AutoSpeed;
    addRequirements(PixyCam);
    addRequirements(Shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    distance = this.PixyCam.getDistance();
    Shooter.shoot(table.getShooterWheelSpeed(distance));
 
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
