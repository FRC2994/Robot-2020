// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.commands;

// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.subsystems.Drivetrain;
// import frc.subsystems.VisionArduino;

// public class AlignPID extends CommandBase {
//   private VisionArduino arduino;
//   private Drivetrain drive;
//   double kP = 0.1;
//   double kI = 0;
//   double kD = 0;
//   double integral, derivative, previous_error = 0;

//   double error;

//   public AlignPID(VisionArduino ad, Drivetrain dt) {
//     this.arduino = ad;
//     this.drive = dt;
//     addRequirements(this.arduino);
//     addRequirements(this.drive);
//   }

//   // Called when the command is initially scheduled.
//   @Override
//   public void initialize() {
//     this.integral = 0;
//     this.derivative = 0;
//     this.previous_error = 0;
//   }

//   // Called every time the scheduler runs while the command is scheduled.
//   @Override
//   public void execute() {
//     this.previous_error = this.error;
//     int currentPos = arduino.getPos();
//     double steer;
//     System.out.println("currentPos:" + currentPos);
    
//     if(currentPos < 148){// The target is left so robot goes left
//       this.error = 148-currentPos;
//       this.integral += (error*.02); 
//       this.derivative = (error - this.previous_error) / .02;
      
//       steer = kP*error + kI*this.integral + kD*derivative;
//       drive.arcadeDrive(0, steer);
//     }
//     else if (currentPos > 168){ //The target is right so robot goes right
//       this.error = 168-currentPos;
//       this.integral += (error*.02); 
//       this.derivative = (error - this.previous_error) / .02;
      
//       steer = kP*error + kI*this.integral + kD*derivative;
//       drive.arcadeDrive(0, steer);
//     }
//     else{
//       drive.tankDrive(0,0);
//     }
//   }

//   // Called once the command ends or is interrupted.
//   @Override
//   public void end(boolean interrupted) {
//     drive.tankDrive(0,0);
//   }

//   // Returns true when the command should end.
//   @Override
//   public boolean isFinished() {
//     return false;
//   }
// }
