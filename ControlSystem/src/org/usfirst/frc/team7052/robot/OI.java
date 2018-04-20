/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team7052.robot;

import org.usfirst.frc.team7052.robot.Constants.*;
import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
    public enum JoystickType {
		xbox, logitech, ps4
    }
    
    public Joystick joystick;
    public JoystickType type;	
    
    Xbox xbox;
    Logitech logitech;
    PS4 ps4;

    public OI(int joystickPort) {
        joystick = new Joystick(joystickPort);
        type = JoystickType.logitech;
    }
    
    public OI(int joystickPort, JoystickType type) {
    	joystick = new Joystick(joystickPort);
        this.type = type;
        
        switch (type) {
	        case xbox:
        		xbox = new Xbox();
        		break;
	        case logitech:
        		logitech = new Logitech();
        		break;
	        case ps4:
        		ps4 = new PS4();
        		break;
        }
    }

    public double getInput(OIMap map) {
    	switch (type) {
    	case logitech:
    		if (logitech.axis.containsKey(map)) return joystick.getRawAxis(logitech.axis.get(map));
    		else if (logitech.buttons.containsKey(map)) return joystick.getRawButton(logitech.buttons.get(map)) ? 1.0 : 0.0;
    		else return 0.0;
    	case xbox:
    		if (xbox.axis.containsKey(map)) return joystick.getRawAxis(xbox.axis.get(map));
    		else if (xbox.buttons.containsKey(map)) return joystick.getRawButton(xbox.buttons.get(map)) ? 1.0 : 0.0;
    		else return 0.0;
    	case ps4:
    		if (ps4.axis.containsKey(map)) return joystick.getRawAxis(ps4.axis.get(map));
    		else if (ps4.buttons.containsKey(map)) return joystick.getRawButton(ps4.buttons.get(map)) ? 1.0 : 0.0;
    		else return 0.0;
    	}
    	return 0.0;
    }
    
    public int getDPad() { 
    	return joystick.getPOV();
    }
}
