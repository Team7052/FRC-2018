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
    private static int sameSwitchCounter = 0;
    private static int currentSwitch = 0;
    private static int switchOvershotState = 0;
    /*
     * For shouldBeStoppedAtSwitchCounter
     * 0 is should be stopped and if overshot, then it is too low (needs to lift elevator to find switch
     * 1 is should be stopped and if overshot, then it is too high (needs to lower elevator to find switch
     * 2 is should not be stopped at a switch (could be stopped or currently moving
     * */
    private static boolean isAdjusting = true;
    private static double currentMotorSpeed = 0.2;



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
            liftMotor.set(-0.9);
        }
        else {
            liftMotor.set(1.0);
        }
    }

    public static void liftElevator(OI oi) {
        //get user input from the left and right second bumpers
        boolean leftPressed = oi.buttonPressed(Constants.kButtonSecondLeftBumper);
        boolean rightPressed = oi.buttonPressed(Constants.kButtonSecondRightBumper);

        //check to see if switch is pressed
        boolean passingSwitch = passedLimitSwitch();
        boolean atSwitch = sameSwitchCounter >= 10; // if it is detecting a counter at least 10 times than it is at the switch
        // if over shot, then adjust it to a magnetic switch

        // if both are pressed at the same time or if none are pressed, the stop the lift
        if (leftPressed && rightPressed || !leftPressed && !rightPressed) {
            // stop lift
            if (switchOvershotState != 2 && !atSwitch && !passingSwitch) {
                isAdjusting = true;
                if (switchOvershotState == 0) {
                    currentMotorSpeed = 0.4; // go up slowly to find the overshot switch
                }
                else if (switchOvershotState == 1) {
                    currentMotorSpeed = -0.1; // go down slowly to find the overshot switch
                }
            }
            else {
                if (isAdjusting) isAdjusting = false;
                currentMotorSpeed = 0.2;
            }
        }
        else if (leftPressed && !isAdjusting) {
            // direction = down

            //TODO: Change passing vs at switch
            if (passingSwitch) {
                if (currentSwitch == 0) {
                    currentSwitch = 0;
                    currentMotorSpeed = 0.2; // hover speed;
                    switchOvershotState = 0; // should be stopped but if overshot, it will be too low
                }
                else if (currentSwitch == 1) {
                    currentSwitch = 0;
                    currentMotorSpeed = -0.9;
                    switchOvershotState = 0; // should not be stopped at a switch
                }
                else if (currentSwitch == 2) {
                    currentSwitch = 1;
                    currentMotorSpeed = -0.1;
                    switchOvershotState = 2; // should not be stopped at a switch
                }
            }
        }
        else if (rightPressed && !isAdjusting) {
            // direction = up
            if (passingSwitch) {
                if (currentSwitch == 0) {
                    currentSwitch = 1;
                    currentMotorSpeed = 1.0;
                    switchOvershotState = 2; // should not be stopped at a switch
                }
                else if (currentSwitch == 1) {
                    currentSwitch = 2;
                    currentMotorSpeed = 0.4; // slow down
                    switchOvershotState = 2; // should not be stopped at a switch
                }
                else if (currentSwitch == 2) {
                    currentMotorSpeed = 0.2; // hover
                    switchOvershotState = 1; // should be stopped but if overshot, it will be too high
                }
            }
        }
        liftMotor.set(currentMotorSpeed);
    }

    public static boolean passedLimitSwitch() {
        double limitVoltage = limitSwitch.getVoltage();
        long now = System.currentTimeMillis();
        if (limitVoltage < 1) {
            // if now - prevRecorded < 200, disregard reading
            if (now - prevRecordedTime > 200) {
                sameSwitchCounter = 0; // reset this counter
                return true;
            }
            else {
                sameSwitchCounter += 1;
            }
            prevRecordedTime = now;
        }
        return false;
    }

    @Override
    protected void initDefaultCommand() {
    }
}
