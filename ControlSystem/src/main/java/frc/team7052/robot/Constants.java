package frc.team7052.robot;

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


    // tank drive control constants

    // wiring constants directly related to hardware: Don't change unless rewiring the robot
    // drive
    public static final int kMotorFrontLeft = 3;
    public static final int kMotorBackLeft = 0;
    public static final int kMotorFrontRight = 1;
    public static final int kMotorBackRight = 2;

    // elevator lift motor
    public static final int kMotorElevatorLift = 4;
    public static final int kLiftLimitSwitchAnalog = 1;

    // claw motors
    public static final int kMotorClawWheels = 5;
    public static final int kMotorClawArms = 6;

    // buttons
    public static final int kButtonA = 1;
    public static final int kButtonB = 2;
    public static final int kButtonX = 3;
    public static final int kButtonY = 4;
    public static final int kButtonSecondLeftBumper = 5;
    public static final int kButtonSecondRightBumper = 6;
    public static final int kButtonBack = 7;
    public static final int kButtonStart = 8;
    public static final int kButtonLeftJoystickPress = 9;
    public static final int kButtonRightJoystickPress = 10;
}
