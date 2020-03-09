// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.commands;

// import edu.wpi.first.wpilibj2.command.CommandBase;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import com.revrobotics.ColorSensorV3;
// import com.ctre.phoenix.motorcontrol.can.VictorSPX;
// import com.revrobotics.ColorMatch;
// import com.revrobotics.ColorMatchResult;
// import edu.wpi.first.wpilibj.util.Color;
// import com.ctre.phoenix.motorcontrol.ControlMode;

// import frc.subsystems.ControlPanel;

// public class SpinControlPanel extends CommandBase {

//   private final VictorSPX motor;
//   private final ColorSensorV3 sensor;
//   private ColorMatch matcher;
//   private int colorAmount = 0;

//   private Color blueTarget;
//   private Color greenTarget;
//   private Color yellowTarget;
//   private Color redTarget;
//   /**
//    * Creates a new SpinControlPanel.
//    */
//   public SpinControlPanel(ControlPanel controlPanel) {
// 		this.addRequirements(controlPanel);
		
//     this.motor = controlPanel.motor();
// 		this.sensor = controlPanel.colorSensor();
//     this.matcher = new ColorMatch();
// 	} 
//     // Use addRequirements() here to declare subsystem dependencies.

//   // Called when the command is initially scheduled.
//   @Override
//   public void initialize() {
//     this.blueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
//     this.matcher.addColorMatch(this.blueTarget);

//     this.greenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
//     this.matcher.addColorMatch(this.greenTarget);

//     this.yellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
//     this.matcher.addColorMatch(this.yellowTarget);

//     this.redTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
// 	  this.matcher.addColorMatch(this.redTarget);
//   }

//   // Called every time the scheduler runs while the command is scheduled.
//   @Override
//   public void execute() {
//     Color currentColor = this.sensor.getColor();
//   	ColorMatchResult matchResult = this.matcher.matchClosestColor(currentColor);

//     if (matchResult.color == blueTarget) {
//       ++colorAmount;
//       System.out.println("Blue: " + colorAmount);
//     } else if (matchResult.color == greenTarget) {
//       ++colorAmount;
//       System.out.println("Green: " + colorAmount);
//     } else if (matchResult.color == yellowTarget) {
//       ++colorAmount;
//       System.out.println("Yellow: " + colorAmount);
//     } else if (matchResult.color == redTarget)  {
//       ++colorAmount;
//       System.out.println("Red: " + colorAmount);
//     } else {
//       System.out.println("None");
//     }

//     if (colorAmount <= 12) {
//       this.motor.set(ControlMode.PercentOutput, 0.5);
//     } else {
//       this.motor.set(ControlMode.PercentOutput, 0);
//     }
//   }

//   // Called once the command ends or is interrupted.
//   @Override
//   public void end(boolean interrupted) {
//   }

//   // Returns true when the command should end.
//   @Override
//   public boolean isFinished() {
//     return false;
//   }
// }
