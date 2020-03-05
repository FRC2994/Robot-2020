/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.utils;
import edu.wpi.first.wpilibj.AnalogInput;

/**
 * Add your docs here.
 */
public class AnalogSwitch {
    AnalogInput autoSwitch;
    int offset = 20;
    double[] analogValues = {
        2131, //Mode 0
        2456, //Mode 1
        2689, //Mode 2
        2994, //Mode 3
        3187, //Mode 4
        3318, //Mode 5
        3420, //Mode 6
        3502, //Mode 7
        3560, //Mode 8
        3654, //Mode 9
        3715, //Mode 10
        3778  //Mode 11
    };

    public AnalogSwitch() {
        autoSwitch = new AnalogInput(3);
    }

    public int getCurrentMode() {
        int mode = 0;
        int readValue = autoSwitch.getAverageValue();
        for(int x=0;x<=11;x++){
            if((analogValues[x]+offset >= readValue) && (analogValues[x]-offset <= readValue)){
                mode = x;
            }
        }
        return mode;
    }
}
