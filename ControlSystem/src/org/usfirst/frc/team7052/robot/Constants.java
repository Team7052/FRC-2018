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
    public static final int kMotorElevatorLift = 5; // can id
    public static final int kLiftEncoder = 1;

    // claw motors
    public static final int kMotorArmVerticalLift = 4;
    public static final int kMotorArmRotatingLeft = 5;
    public static final int kMotorArmRotatingRight = 6;
    public static final int kMotorArmWheelsLeft = 7;
    public static final int kMotorArmWheelsRight = 8;

    public static class Xbox {
		// xbox remote
		public HashMap<OIMap, Integer> axis = new HashMap<>();
		public HashMap<OIMap, Integer> buttons = new HashMap<>();
		
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
		}
    }
    
    public static class Logitech {
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
    
    public static class PS4 {
		// xbox remote
		public HashMap<OIMap, Integer> axis = new HashMap<>();
		public HashMap<OIMap, Integer> buttons = new HashMap<>();
		
		public PS4() {
			axis.put(OIMap.leftAxisX, 0);
			axis.put(OIMap.leftAxisY, 1);
			axis.put(OIMap.rightAxisX, 2);
			axis.put(OIMap.leftBumper, 3);
			axis.put(OIMap.rightBumper, 4);
			axis.put(OIMap.rightAxisY, 5);
			
			buttons.put(OIMap.buttonX, 1);
			buttons.put(OIMap.buttonA, 2);
			buttons.put(OIMap.buttonB, 3);
			buttons.put(OIMap.buttonY, 4);
			buttons.put(OIMap.leftBumper2, 5);
			buttons.put(OIMap.rightBumper2, 6);
			buttons.put(OIMap.leftBumper, 7);
			buttons.put(OIMap.rightBumper, 8);
			buttons.put(OIMap.buttonBack, 9);
			buttons.put(OIMap.buttonStart, 10);
			buttons.put(OIMap.buttonJoystickLeft, 11);
			buttons.put(OIMap.buttonJoystickRight, 12);
		}
    }
}


