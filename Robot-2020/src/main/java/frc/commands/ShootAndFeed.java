// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.commands;

// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.subsystems.Elevator;
// import frc.subsystems.ShooterWheel;

// public class ShootAndFeed extends CommandBase {
//   private ShooterWheel shooter;
//   private Elevator elevator;
//   private double rpm_offset = 10;
//   private double desiredRPM = 5200;
//   private boolean ballQueued = false;
//   private boolean justShot = false;
//   private int timerTicks;
//   public ShootAndFeed(ShooterWheel _shooter, Elevator _elevator) {
//     shooter = _shooter;
//     elevator = _elevator;
//     // Use addRequirements() here to declare subsystem dependencies.
//     addRequirements(shooter);
//     addRequirements(elevator);
//   }

//   // Called when the command is initially scheduled.
//   @Override
//   public void initialize() {
//     timerTicks = 0;
//   }

//   // Called every time the scheduler runs while the command is scheduled.
//   @Override
//   public void execute() {
//     shooter.shoot();
//     ballQueued = elevator.isBallIn();
//     if(ballQueued == true){
//       elevator.stopMotor();
//       //Checks shooter RPM to see if it is ready to fire
//       if((shooter.getRPM() >= desiredRPM-rpm_offset) && (shooter.getRPM() <= desiredRPM+rpm_offset)){
//           elevator.startMotor();
//       }
//       else{
//         elevator.stopMotor();
//       }
//     } 
//     else {
//         elevator.semiIntake();
//     }
//   }

//   // Called once the command ends or is interrupted.
//   @Override
//   public void end(boolean interrupted) {
//     shooter.stopMotor();
//     elevator.stopMotor();
//   }

//   // Returns true when the command should end.
//   @Override
//   public boolean isFinished() {
//     return false;
//   }
// }