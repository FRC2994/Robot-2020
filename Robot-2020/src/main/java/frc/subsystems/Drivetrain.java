/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.utils.Constants;

public class Drivetrain extends SubsystemBase {
	private CANSparkMax FrontRight = new CANSparkMax(Constants.CAN_RIGHT_FRONT_WHEEL, MotorType.kBrushless);
	private CANSparkMax FrontLeft = new CANSparkMax(Constants.CAN_LEFT_FRONT_WHEEL, MotorType.kBrushless);
	private CANSparkMax RearRight = new CANSparkMax(Constants.CAN_RIGHT_BACK_WHEEL, MotorType.kBrushless);
	private CANSparkMax RearLeft = new CANSparkMax(Constants.CAN_LEFT_BACK_WHEEL, MotorType.kBrushless);

	private CANPIDController rightPID = FrontRight.getPIDController();
	private CANPIDController leftPID = FrontLeft.getPIDController();
	private CANEncoder rightENC = FrontRight.getEncoder();
	private CANEncoder leftENC = FrontLeft.getEncoder();

	Solenoid gearShiftSolenoid = new Solenoid(0, 1);
	public static enum GearShiftState { HI, LO };

	public ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	public DifferentialDrive differentialDrive;

	private int startPosition;
	private int desiredPosition = 0; 
	private boolean stopArcadeDrive;
	private boolean reverse;
	
	private static final double leftDistancePerPulse = (4.0 / 12.0 * Math.PI) / 360.0;
	private static final double rightDistancePerPulse = (4.0 / 12.0 * Math.PI) / 360.0;

	public Drivetrain() {
		FrontRight.restoreFactoryDefaults();
		RearRight.restoreFactoryDefaults();
		FrontLeft.restoreFactoryDefaults();
		RearLeft.restoreFactoryDefaults();
		
		FrontRight.setInverted(false);
		FrontLeft.setInverted(true);
		// Set the rear drives to follow the left and right front drives
		RearLeft.follow(FrontLeft);
		RearRight.follow(FrontRight);

        differentialDrive = new DifferentialDrive(FrontLeft, FrontRight);

		gyro.calibrate();

		//Resets Encoder Position to 0
		leftENC.setPosition(0);
		rightENC.setPosition(0);

		// Let's name the sensors on the LiveWindow
		// TODO: figure out the 2020 version of this
        // addChild("Drive", differentialDrive);
		// addChild("Gyro", gyro);

		//Sets the motors to brake mode
		FrontRight.setIdleMode(IdleMode.kBrake);
		RearRight.setIdleMode(IdleMode.kBrake);
		FrontLeft.setIdleMode(IdleMode.kBrake);
		RearLeft.setIdleMode(IdleMode.kBrake);
		FrontLeft.setOpenLoopRampRate(0.1);
		FrontRight.setOpenLoopRampRate(0.1);

		reverse = false;
	}
	
	public void setDesiredPosition(int position) {
		desiredPosition = position;
		System.out.println("Setting encoders to " + desiredPosition + " Currently " + (getLeftEncoderValue()));
		leftPID.setReference(position, ControlType.kPosition);
		rightPID.setReference(position, ControlType.kPosition);
	}
	
	public int getDesiredPosition() {
		return desiredPosition;
	}

	public void stopTalonPID() {
		FrontLeft.set(0);
		FrontRight.set(0);
		RearRight.setInverted(false);
		RearLeft.setInverted(false);
	}
	
	
	public void configureTurboProfile() {
		leftPID.setP(0.4, 0);
		leftPID.setD(0.01, 0);

		rightPID.setP(0.4, 0);
		rightPID.setD(0.01, 0);
	}
	
	public void configureFastProfile() {
		leftPID.setP(0.2, 0);
		leftPID.setD(0.1, 0);

		rightPID.setP(0.2, 0);
		rightPID.setD(0.1, 0);
	}
	
	public void configureSlowProfile() {
		leftPID.setP(0.15, 0);
		leftPID.setD(0, 0);

		rightPID.setP(0.15, 0);
		rightPID.setD(0, 0);
	}
	
	public void driveWithCurve(double speed, double turn, boolean isQuickTurn) {
		differentialDrive.curvatureDrive(speed, turn, isQuickTurn);
	}
	

	public void arcadeDrive(double forward, double rotation) {
		differentialDrive.arcadeDrive(forward , rotation);
	}

	public void getJoystickValues(Joystick joystick) {
		System.out.println("Y VALUE: " + joystick.getY() + " X VALUE: " + joystick.getX());
	}
	
	public void tankDrive(double leftSpeed, double rightSpeed) {
		differentialDrive.tankDrive(-leftSpeed, -rightSpeed);
	}
	
	public void resetEncoders() {
		leftENC.setPosition(0);
		rightENC.setPosition(0);

		if (getLeftEncoderValue() != 0) {
			System.out.println("ERROR - Could not reset Left encoder!!");
		}

		if (getRightEncoderValue() != 0) {
			System.out.println("ERROR - Could not reset Right encoder!!");
		}
	}

	public void setGear(GearShiftState state) {
	   System.out.println("Trying to shift to gear state " + state);
	   gearShiftSolenoid.set(state==GearShiftState.HI?false:true);
	}

	public void highGear() {
		gearShiftSolenoid.set(true);
	}

	public void lowGear() {
		gearShiftSolenoid.set(false);
	}

	public void reverseDrive(boolean state) {
		reverse = state;
	}
	
	public void resetGyro() {
		gyro.reset();
	}
	
	public CANSparkMax getFrontLeftMotor() {
		return FrontLeft;
	}

	public CANSparkMax getRearLeftMotor() {
	//public TalonSRX getRearLeftMotor() {
		return RearLeft;
	}

	public CANSparkMax getFrontRightMotor() {
		return FrontRight;
	}

	public CANSparkMax getRearRightMotor() {
	//public TalonSRX getRearRightMotor() {
		return RearRight;
	}

	public double getLeftEncoderValue() {
		return leftENC.getPosition();
	}

	public double getRightEncoderValue() {
		return rightENC.getPosition();
	}
	public void zero() {
		startPosition = (int)getLeftEncoderValue();
	}

    public void	setStopArcadeDrive(boolean value) {
		stopArcadeDrive = value;
	}

    /**
     * This function is called periodically by Scheduler.run
     */
	@Override
	public void periodic() {
//		System.out.println("Left Encoder: " + getLeftEncoderValue() +" Right Encoder: " + getRightEncoderValue());
		// Logger.appendRecord(
		//  		getFrontLeftMotor().getMotorOutputVoltage() + "\t" + getFrontRightMotor().getMotorOutputVoltage() + 
		//  		"\t" + getLeftEncoderValue() + "\t" + getRightEncoderValue() + "\t" + getHeading() + "\t");
	}

   /**
    * Get the robot's heading.
    *
    * @return The robots heading in degrees.
    */
    public double getHeading() {
      return gyro.getAngle();
    }

   /**
    * Reset the robots sensors to the zero states.
    */
    public void reset() {
		setGear(GearShiftState.LO);
		
		resetEncoders();
		resetGyro();

		stopArcadeDrive = false;

		RearRight.setInverted(false);
		FrontRight.setInverted(false);
		
		zero();
        // Logger.appendRecord("dtLmtr\tdtRmtr\tdtLenc\tdtRenc\tdtGyro\t");
    }

   /**
    * Get the average distance of the encoders since the last reset.
    *
    * @return The distance driven (average of left and right encoders).
    */
    public double getDistance() {
	   return (getLeftEncoderValue()*leftDistancePerPulse + 
	         getRightEncoderValue()*rightDistancePerPulse) / 2;
    }
}
