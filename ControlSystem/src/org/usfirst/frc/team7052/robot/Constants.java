package org.usfirst.frc.team7052.robot;

public class Constants {
    //arcade control constants
    public static double kSpeedFastMultiplier = 1.0;
    public static double kRotationFastMultiplier = 0.67;

    public static double kSpeedSlowMultiplier = 0.6;
    public static double kRotationSlowMultiplier = 0.56;

    // tank drive constants
    public static double kSlowestRobotSpeed = 0.45;

    // claw constants
    //TODO: Find arm positions when opened and closed
    public static double kClawPositionOpen = 0;
    public static double kClawPositionClose = 0;

    // camera control constants
    public static int kCameraExposureTime = 1; // in milliseconds

    // tank drive control constants

    // wiring constants directly related to hardware: Don't change unless rewiring the robot
    // drive
    public static final int kMotorFrontLeft = 3;
    public static final int kMotorBackLeft = 0;
    public static final int kMotorFrontRight = 1;
    public static final int kMotorBackRight = 2;

    // elevator lift motor
    public static final int kMotorElevatorLift = 5;
    public static final int kLiftLimitSwitchAnalog = 1;

    // claw motors
    public static final int kMotorClawVerticalLift = 6;
    public static final int kMotorClawFloatingArm = 4;

    // buttons
    public static final int kAxisLeftStickX = 0;
    public static final int kAxisLeftStickY = 1;
    public static final int kAxisLeftBumper = 2;
    public static final int kAxisRightBumper = 3;
    public static final int kAxisRightStickX = 4;
    public static final int kAxisRightStickY = 5;

    public static final int kButtonA = 2;
    public static final int kButtonB = 3;
    public static final int kButtonX = 1;
    public static final int kButtonY = 4;
    public static final int kButtonSecondLeftBumper = 5;
    public static final int kButtonSecondRightBumper = 6;
    public static final int kButtonBack = 7;
    public static final int kButtonStart = 8;
    public static final int kButtonLeftJoystickPress = 9;
    public static final int kButtonRightJoystickPress = 10;

    public static final int kDPadLeft = 0;
    public static final int kDPadRight = 1;
    public static final int kDPadUp = 2;
    public static final int kDPadDown = 3;
}


