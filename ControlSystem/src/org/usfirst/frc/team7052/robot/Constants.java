package org.usfirst.frc.team7052.robot;

import java.util.*;

public class Constants {
	// global variables, not constants
	public static List<OI> ois = new ArrayList<>();
    //arcade control constants
    public static final double kSpeedFastMultiplier = 1.0;
    public static final double kRotationFastMultiplier = 0.67;

    public static final double kSpeedSlowMultiplier = 0.6;
    public static final double kRotationSlowMultiplier = 0.56;

    // tank drive constants
    public static final double kSlowestRobotSpeed = 0.45;

    // claw constants
    public static final double kClawPositionOpen = 0;
    public static final double kClawPositionClose = 0;

    // camera control constants
    public static final int kCameraExposureTime = 1; // in milliseconds

    /** tank drive control constants **/

    // wiring constants directly related to hardware: Don't change unless rewiring the robot
    // drive
    public static final int kMotorFrontLeft = 3;
    public static final int kMotorBackLeft = 0;
    public static final int kMotorFrontRight = 1;
    public static final int kMotorBackRight = 2;

    // elevator lift motor
    public static final int kMotorElevatorLift = 5;
    public static final int kLiftEncoder = 1;

    // claw motors
    public static final int kMotorClawVerticalLift = 6;
    public static final int kMotorClawFloatingArm = 4;
    
    
    // joystick buttons map
    // not true constants but are edited by joystick constants class
    public static int kAxisLeftStickX = -1;
    public static int kAxisLeftStickY = -1;
    public static int kAxisLeftBumper = -1;
    public static int kAxisRightBumper = -1;
    public static int kAxisRightStickX = -1;
    public static int kAxisRightStickY = -1;
    public static int kButtonA = -1;
    public static int kButtonB = -1;
    public static int kButtonX = -1;
    public static int kButtonY = -1;
    public static int kButtonLeftBumper = -1;
    public static int kButtonRightBumper = -1;
    public static int kButtonSecondLeftBumper = -1;
    public static int kButtonSecondRightBumper = -1;
    public static int kButtonBack = -1;
    public static int kButtonStart = -1;
    public static int kButtonLeftJoystickPress = -1;
    public static int kButtonRightJoystickPress = -1;
    public static int kDPadLeft = -1;
    public static int kDPadRight = -1;
    public static int kDPadUp = -1;
    public static int kDPadDown = -1;
    
    public enum JoystickType {
		xbox, logitech, ps4
    }

    public class Xbox {
    		// xbox remote
    		public HashMap<OIMap, Integer> axis = new HashMap<>();
    		public HashMap<OIMap, Integer> buttons = new HashMap<>();
    		public HashMap<OIMap, Double> dpad = new HashMap<>();
    		
    		public Xbox() {
    			axis.put(OIMap.leftAxisX, 0);
    			axis.put(OIMap.leftAxisY, 1);
    			axis.put(OIMap.leftBumper, 2);
    			axis.put(OIMap.rightBumper, 3);
    			axis.put(OIMap.rightAxisX, 4);
    			axis.put(OIMap.rightAxisY, 5);
    			
    			buttons.put(OIMap.buttonX, 1);
    			buttons.put(OIMap.buttonA, 2);
    			buttons.put(OIMap.buttonB, 3);
    			buttons.put(OIMap.buttonY, 4);
    			buttons.put(OIMap.leftBumper2, 5);
    			buttons.put(OIMap.rightBumper2, 6);
    			buttons.put(OIMap.buttonBack, 7);
    			buttons.put(OIMap.buttonStart, 8);
    			buttons.put(OIMap.buttonJoystickLeft, 9);
    			buttons.put(OIMap.buttonJoystickRight, 10);
    			
    			dpad.put(OIMap.dPadUp, 0.0);
    			dpad.put(OIMap.dPadRightUp, 0.125);
    			dpad.put(OIMap.dPadRight, 0.25);
    			dpad.put(OIMap.dPadRightDown, 0.375);
    			dpad.put(OIMap.dPadDown, 0.5);
    			dpad.put(OIMap.dPadLeftDown, 0.625);
    			dpad.put(OIMap.dPadLeft, 0.75);
    			dpad.put(OIMap.dPadLeftUp, 0.875);
    		}
    }
    
    public class Logitech {
		// xbox remote
		public HashMap<OIMap, Integer> axis = new HashMap<>();
		public HashMap<OIMap, Integer> buttons = new HashMap<>();
		
		public Logitech() {
			axis.put(OIMap.leftAxisX, 0);
			axis.put(OIMap.leftAxisY, 1);
			axis.put(OIMap.rightAxisX, 4);
			axis.put(OIMap.rightAxisY, 5);
			
			buttons.put(OIMap.buttonX, 1);
			buttons.put(OIMap.buttonA, 2);
			buttons.put(OIMap.buttonB, 3);
			buttons.put(OIMap.buttonY, 4);
			buttons.put(OIMap.leftBumper2, 5);
			buttons.put(OIMap.rightBumper2, 6);
			buttons.put(OIMap.buttonBack, 7);
			buttons.put(OIMap.buttonStart, 8);
			buttons.put(OIMap.buttonJoystickLeft, 9);
			buttons.put(OIMap.buttonJoystickRight, 10);
		}
    }
    public class PS4 {
		// xbox remote
		public HashMap<OIMap, Integer> axis = new HashMap<>();
		public HashMap<OIMap, Integer> buttons = new HashMap<>();
		
		public PS4() {
			axis.put(OIMap.leftAxisX, 0);
			axis.put(OIMap.leftAxisY, 1);
			axis.put(OIMap.rightAxisX, 4);
			axis.put(OIMap.rightAxisY, 5);
			
			buttons.put(OIMap.buttonX, 1);
			buttons.put(OIMap.buttonA, 2);
			buttons.put(OIMap.buttonB, 3);
			buttons.put(OIMap.buttonY, 4);
			buttons.put(OIMap.leftBumper2, 5);
			buttons.put(OIMap.rightBumper2, 6);
			buttons.put(OIMap.buttonBack, 7);
			buttons.put(OIMap.buttonStart, 8);
			buttons.put(OIMap.buttonJoystickLeft, 9);
			buttons.put(OIMap.buttonJoystickRight, 10);
		}
    }
}


