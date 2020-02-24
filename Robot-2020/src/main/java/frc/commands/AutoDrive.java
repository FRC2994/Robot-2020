// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.commands;

// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.subsystems.DriveAutonomous;

// public class AutoDrive extends CommandBase {
// 	private DriveAutonomous autoDrive;

// 	/**
// 	 * Creates a new AutoDrive.
// 	 */
// 	public AutoDrive(DriveAutonomous autoDrive) {
// 		this.autoDrive = autoDrive;
// 	}

// 	// Called when the command is initially scheduled.
// 	@Override
// 	public void initialize() {
// 		System.out.println("[AutoDrive] initialize");
// 		this.autoDrive.start();
// 	}

// 	// Called every time the scheduler runs while the command is scheduled.
// 	@Override
// 	public void execute() {
// 	}

// 	// Called once the command ends or is interrupted.
// 	@Override
// 	public void end(boolean interrupted) {
// 		this.autoDrive.stop();
// 	}

// 	// Returns true when the command should end.
// 	@Override
// 	public boolean isFinished() {
// 		return false;
// 	}
// }
