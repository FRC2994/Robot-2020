/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.subsystems.Intake;


public class intakeArm extends InstantCommand {
  private boolean intakeArmExtend = false;
  private Intake intake;

  public intakeArm(Intake Arm) {
    intake = Arm;
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (intakeArmExtend == false){
      intakeArmExtend = true;
      intake.lowerIntake();
      System.out.println("LOWER");
    }
    else if (intakeArmExtend == true){
      intakeArmExtend = false;
      intake.raiseIntake();
      System.out.println("RAISE");
    }
  }
}
