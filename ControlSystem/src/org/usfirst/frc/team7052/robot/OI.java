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

    public double getAxis(OIMap map) {
    		switch (type) {
    			case logitech:
    				return joystick.getRawAxis(logitech.axis.get(map));
    			case xbox:
    				return joystick.getRawAxis(xbox.axis.get(map));
    			case ps4:
    				return joystick.getRawAxis(ps4.axis.get(map));
    		}
    		return 0;
    }
    public boolean buttonPressed(OIMap map) {
    		switch (type) {
    		case logitech:
    			return joystick.getRawButton(logitech.buttons.get(map));
    		case xbox:
    			return joystick.getRawButton(xbox.buttons.get(map));
    		case ps4:
    			return joystick.getRawButton(ps4.buttons.get(map));
    		}
    		return false;
    }
    public int getDPad() { 
    		return joystick.getPOV();
    	}
}
