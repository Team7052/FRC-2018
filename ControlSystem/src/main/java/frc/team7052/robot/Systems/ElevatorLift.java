package frc.team7052.robot.Systems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team7052.robot.Constants;
import frc.team7052.robot.Enums.Direction;

public class ElevatorLift extends Subsystem {
    private static ElevatorLift instance;

    private static Spark liftMotor;
    private static AnalogInput limitSwitch;

    private static long prevRecordedTime = 0;
    public static int currentSwitch = 0;
    public static boolean acceptingInput = true;

    private static boolean slowingDownToTop = false;
    private static long slowingDownInitTime = 0;
    public static int currentStage = 0;
    public static Direction direction = Direction.up;

    private ElevatorLift() {
        liftMotor = new Spark(Constants.kMotorElevatorLift);
        liftMotor.setInverted(true); // invert so that positive motor values make the lift go up while negative values make the lift go down
        limitSwitch = new AnalogInput(Constants.kLiftLimitSwitchAnalog);
    }

    public static ElevatorLift getInstance() {
        if (instance == null) instance = new ElevatorLift();
        return instance;
    }

    public static void startTime() {
        prevRecordedTime = System.currentTimeMillis();
    }

    public static void elevatorManual(OI oi) {
        boolean leftPressed = oi.buttonPressed(Constants.kButtonSecondLeftBumper);
        boolean rightPressed = oi.buttonPressed(Constants.kButtonSecondRightBumper);

        if ((leftPressed && rightPressed) || (!leftPressed && !rightPressed)) {
            liftMotor.set(0.2);
        }
        else if (leftPressed) {
            liftMotor.set(-0.5);
        }
        else {
            liftMotor.set(0.6);
        }
    }

    public static void liftElevator(OI oi) {
        //get user input from the left and right second bumpers
        boolean leftPressed = oi.buttonPressed(Constants.kButtonSecondLeftBumper);
        boolean rightPressed = oi.buttonPressed(Constants.kButtonSecondRightBumper);


        // if both are pressed at the same time, the stop the lift
        if (leftPressed && rightPressed || !leftPressed && !rightPressed) {
            // stop lift
            acceptingInput = true;
            hover();
        }
        else if (leftPressed) { // left = down
            if (acceptingInput && currentStage != 0) {
                liftMotor.set(-0.9);
            }
            else hover();
        }
        else if (rightPressed) { // right = up
            if (acceptingInput && currentStage != 3) {
                liftMotor.set(1);
            }
            else hover();
        }
    }

    public static double changeFunctionSpeed(long beginTime) {
        // speed needs to go from 1.0 to 0 over 0.5 seconds
        // function is quadratic
        // when t = 0, v = 1;
        // when t = 500, v = 0;
        // function = (t - 500)^2 / 500

        long now = System.currentTimeMillis();
        long dX = now - beginTime;
        double val = Math.pow(dX - 500,2) / 500;
        return val > 0 ? val : 0;
    }

    public static boolean passedLimitSwitch() {
        double limitVoltage = limitSwitch.getVoltage();
        long now = System.currentTimeMillis();
        if (limitVoltage < 1) {
            // if now - prevRecorded < 200, disregard reading
            if (now - prevRecordedTime > 200) {
                // lift is going up if motor speed is < -0.25
                return true;
            }
            prevRecordedTime = now;
        }
        return false;
    }

    public static boolean lift1Stage(int initialSwitch) {
        if (initialSwitch == 3) {
            return true;
        }
        if (initialSwitch == 0) {
            if (currentSwitch == 2) return true;
        }
        else if (currentSwitch == initialSwitch + 1) {
            return true;
        }


        long now = System.currentTimeMillis();
        if (limitSwitch.getVoltage() < 1) {
            // if now - prevRecorded < 200, disregard reading
            if (now - prevRecordedTime > 200) {
                // lift is going up if motor speed is < -0.25
                currentSwitch += 1;
            }
            prevRecordedTime = now;
        }

        return false;
    }

    public static boolean lower1Stage(int initialSwitch) {
        if (initialSwitch == 1) return true;
        if (initialSwitch == 4) {
            if (currentSwitch == 2) return true;
        }
        else if (currentSwitch == initialSwitch - 1) return true;

        long now = System.currentTimeMillis();
        if (limitSwitch.getVoltage() < 1) {
            // if now - prevRecorded < 200, disregard reading
            if (now - prevRecordedTime > 200) {
                // lift is going up if motor speed is < -0.25
                currentSwitch += 1;
            }
            prevRecordedTime = now;
        }

        return false;
    }

    public static void hover() {
        liftMotor.set(0.2);
    }
    @Override
    protected void initDefaultCommand() {
    }
}
