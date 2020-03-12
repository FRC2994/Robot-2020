/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.subsystems.ControlPanel;

public class ControlPanelPiston extends InstantCommand {
  private ControlPanel controlpanel;
  private boolean controlPanelExtend = false;
  
  public ControlPanelPiston(ControlPanel Piston) {
    controlpanel = Piston;
    addRequirements(controlpanel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (controlPanelExtend == false){
      controlPanelExtend = true;
      controlpanel.raiseControlPanel();
    }
    else if (controlPanelExtend == true){
      controlPanelExtend = false;
      controlpanel.lowerControlPanel();
    }
  }
}
