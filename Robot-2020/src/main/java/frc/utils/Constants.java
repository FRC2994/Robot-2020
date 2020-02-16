/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.utils;

/**
 * Add your docs here.
 */
public class Constants {
    /* CAN IDS */
    public static final int CAN_PCM = 0;
    public static final int CAN_RIGHT_FRONT_WHEEL = 1;
    public static final int CAN_LEFT_FRONT_WHEEL = 2;
    public static final int CAN_RIGHT_BACK_WHEEL = 3;
    public static final int CAN_LEFT_BACK_WHEEL = 4;

    /**
     * Add to these constants as necessary to define presets for shooter wheel speed
     * settings and hood position settings. Integers were chosen arbitrarily and can
     * be changed to whatever type works best. Just be sure to change the ENUM below
     * to match.
     */

    private static final Integer MAX_SHOOTER_WHEEL_SPEED = 1000;
    private static final Integer LOW_SHOOTER_WHEEL_SPEED = 500;
    private static final Integer HIGHEST_HOOD_SETTING = 10;
    private static final Integer HOOD_SETTING_7 = 7;
    private static final Integer HOOD_SETTING_3 = 3;
    private static final Integer LOWEST_HOOD_SETTING = 1;

    /**
     * Enum used by shooter to position the shooter hood and adjust shooter wheel
     * speed. Key is an arbitrary distance for now but could be a return from
     * VisionArduino to keep things tight
     * 
     * Each distance will have a shooterWheelSPeed and ShooterHoodAngle associated
     * 
     * More details on enums here:
     * https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
     * 
     */
    public enum ShootingTable {

        // Add as many enum values as necessary to define shooting couplets
        // Constants above are used to make ENUM table readable.

        // SETTING_NAME(WHEEL_SPEED_CONSTANT, HOOD_SETTING_CONSTANT)
        MAX_DISTANCE (MAX_SHOOTER_WHEEL_SPEED, HIGHEST_HOOD_SETTING), 
        MED_LONG     (MAX_SHOOTER_WHEEL_SPEED, HOOD_SETTING_7),
        MED_CLOSE    (LOW_SHOOTER_WHEEL_SPEED, HOOD_SETTING_3), 
        MIN_DISTANCE (LOW_SHOOTER_WHEEL_SPEED, LOWEST_HOOD_SETTING);

        private final Integer shooterWheelSpeed;
        private final Integer hoodSetting;

        ShootingTable(Integer shooterWheelSpeed, Integer hoodSetting) {
            this.shooterWheelSpeed = shooterWheelSpeed;
            this.hoodSetting = hoodSetting;
        }

        // Basic get methods for wheel speed and hood setting.
        // More advanced methods can be added to the enum is required.

        Integer getShooterWheelSpeed(double distance) {
            if (distance < 24){ // adjust as necessary based on experimentation
                return MIN_DISTANCE.shooterWheelSpeed;
            } else if (distance < 48){ // adjust as necessary based on experimentation
                return MED_CLOSE.shooterWheelSpeed;
            } else if (distance < 72){// adjust as necessary based on experimentation
                return MED_LONG.shooterWheelSpeed;
            } else { // This will always be the max
                return MAX_DISTANCE.shooterWheelSpeed;
            }
        }

        Integer getHoodSetting() {
            return hoodSetting;
        }
    }
}