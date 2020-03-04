/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.subsystems.Elevator;
import frc.subsystems.ShooterWheel;

public class Shoot extends CommandBase {
  private Elevator elevator;
  private ShooterWheel shooter;
  int ticks = 0;
  int maxTicks = 100;
  public Shoot(Elevator _elevator, ShooterWheel _shooter) {
    elevator = _elevator;
    shooter = _shooter;
    addRequirements(elevator);
    addRequirements(shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // ticks++;
    shooter.shoot();
    // if(ticks == 0 || ticks == 1){
    //   // if(shooter.getRPM() > 5050){
    //     elevator.startMotor();
    //     System.out.println("SHOOT");
    //   // }
    // }
    // else if (ticks == 75)
    // {
    //   elevator.stopMotor();
    //   System.out.println("Stopped");
    // }
    // else if (ticks == 100)
    // {
    //   ticks = 0;
    //   System.out.println("Stopped");
    // }
    // Sytsem.out.println(ticks);
    if(shooter.getRPM() > 5180){
      elevator.startMotor();
      // System.out.println("SHOOT");
    }
    else{
      elevator.stopMotor();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.stopMotor();
    elevator.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
