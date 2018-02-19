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
    public static int kMotorFrontLeft = 3;
    public static int kMotorBackLeft = 2;
    public static int kMotorFrontRight = 0;
    public static int kMotorBackRight = 1;

    // elevator lift motor
    public static int kMotorElevatorLift = 4;

    // claw motors
    public static int kMotorClawWheels = 5;
    public static int kMotorClawArms = 6;

    // buttons
    public static int kButtonA = 1;
    public static int kButtonB = 2;
    public static int kButtonX = 3;
    public static int kButtonY = 4;
    public static int kButtonSecondLeftBumper = 5;
    public static int kButtonSecondRightBumper = 6;
    public static int kButtonBack = 7;
    public static int kButtonStart = 8;
    public static int kButtonLeftJoystickPress = 9;
    public static int kButtonRightJoystickPress = 10;
}
