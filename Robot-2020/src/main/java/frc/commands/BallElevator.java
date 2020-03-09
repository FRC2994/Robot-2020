// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.commands;

// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.subsystems.Elevator;

// public class BallElevator extends CommandBase {
//   private Elevator elevator;
//   private boolean finished;

//   public BallElevator(Elevator el) {
//     elevator = el;
//     // Use addRequirements() here to declare subsystem dependencies.
//     addRequirements(elevator);
//   }

//   // Called when the command is initially scheduled.
//   @Override
//   public void initialize() {
//       finished = false;
//   }

//   // Called every time the scheduler runs while the command is scheduled.
//   @Override
//   public void execute() {
//     if(elevator.isBallIn() == false){
//         elevator.semiIntake();
//     } else {
//         finished = true;
//         elevator.stopMotor();
//        }
//   }

//   // Called once the command ends or is interrupted.
//   @Override
//   public void end(boolean interrupted) {
//       elevator.stopMotor();
//   }

//   // Returns true when the command should end.
//   @Override
//   public boolean isFinished() {
//     return finished;
//   }
// }
