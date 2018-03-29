package org.usfirst.frc.team7052.robot.subsystems;

import org.usfirst.frc.team7052.robot.Constants;
import org.usfirst.frc.team7052.robot.DrivingState;
import org.usfirst.frc.team7052.robot.OI;
import org.usfirst.frc.team7052.robot.OIMap;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends PIDSubsystem {
    public static DifferentialDrive drive;
    public SpeedControllerGroup leftGroup;
    public SpeedControllerGroup rightGroup;

    public AHRS ahrs;

    public static boolean isMovingStraight = false;
    public static double beginMovementAngle = 0;

    public static double leftSpeed = 0.0;
    public static double rightSpeed = 0.0;
    
    public static double pidError = 0.0;

    private OI oi;

    private DriveTrain() {
        //TODO: Tune PID Values
        super("DriveTrain", 0.001, 0.01, 0.05);
        leftGroup = new SpeedControllerGroup(new Spark(Constants.kMotorFrontLeft), new Spark(Constants.kMotorBackLeft));
        rightGroup = new SpeedControllerGroup(new Spark(Constants.kMotorFrontRight), new Spark(Constants.kMotorBackRight));
        leftGroup.setInverted(true);
        rightGroup.setInverted(true);
        drive = new DifferentialDrive(leftGroup, rightGroup);
        try {
            ahrs = new AHRS(I2C.Port.kOnboard);
        }
        catch(RuntimeException exc) {
            exc.printStackTrace();
        }

        this.setAbsoluteTolerance(1.0);

        this.enable();
    }

    @Override
    protected void initDefaultCommand() {
    }

    public static DriveTrain instance;

    public static DriveTrain getInstance() {
        if (instance == null) instance = new DriveTrain();
        return instance;
    }

    public void setOI(OI oi) {
        this.oi = oi;
    }

    public static void tankDrive(OI oi, DrivingState drivingState) {
        double xAxis = oi.getAxis(OIMap.rightAxisX);
        double yAxis = oi.getAxis(OIMap.leftAxisY);

        double speed = yAxis;

        leftSpeed = speed;
        rightSpeed = speed;

        double turnValue = xAxis;

        if (turnValue > 0) { // turningRight
            rightSpeed *= 1 - turnValue * (1 - Constants.kSlowestRobotSpeed);
        }
        else if (turnValue < 0) { // turning left
            leftSpeed *= turnValue * (1 - Constants.kSlowestRobotSpeed) + 1;
        }

        if (Math.abs(speed) < 0.1  && turnValue != 0) {
            leftSpeed = -turnValue;
            rightSpeed = turnValue;
        }
        leftSpeed = getNormalizedSpeed(leftSpeed, drivingState) * pidError;
        rightSpeed = getNormalizedSpeed(rightSpeed, drivingState) * pidError;

        //slowest speed = kSlowestRobotSpeed
        //normalize speed so that it is always between kSlowestRobotSpeed and 1, -Constants.kSlowestRobotSpeed and -1, or 0

        drive.tankDrive(leftSpeed, rightSpeed);
    }

    public void stop() {
        drive.stopMotor();
    }

    private static double getNormalizedSpeed(double speed, DrivingState state) {
        //speed is always between -1 -> -kSlowestRobotSpeed or 0 or kSlowestRobotSpeed -> 1
        speed *= 1 - Constants.kSlowestRobotSpeed;
        if (Math.abs(speed) < 0.1) return 0;
        if (speed > 0) speed = speed + Constants.kSlowestRobotSpeed;
        else speed = speed - Constants.kSlowestRobotSpeed;

        if (state == DrivingState.careful) speed *= 0.6;
        else if (state == DrivingState.regular) speed *= 0.7;
        else if (state == DrivingState.turbo) speed *= 0.95;

        if (speed > 0 && speed < Constants.kSlowestRobotSpeed) speed = Constants.kSlowestRobotSpeed;
        else if (-Constants.kSlowestRobotSpeed < speed && speed < 0) speed = -Constants.kSlowestRobotSpeed;
        return speed;
    }

    @Override
    protected double returnPIDInput() {
        //get joystick input angle
        double xAxis = oi.getAxis(OIMap.rightAxisX);
        double yAxis = oi.getAxis(OIMap.leftAxisY);

        if (!isMovingStraight && Math.abs(yAxis) > 0.1 && Math.abs(xAxis) < 0.1) {
            isMovingStraight = true;
            setSetpoint(ahrs.getAngle());
            beginMovementAngle = ahrs.getAngle();
        }

        if (Math.abs(yAxis) > 0.1 && Math.abs(xAxis) < 0.1) { } // do nothing
        else {
            isMovingStraight = false;
            pidError = 0;
        }

        return ahrs.getAngle();
    }


    @Override
    protected void usePIDOutput(double output) {
        if (isMovingStraight) {
            pidError = output;
        }
        else {
        		pidError = 0;
        }
    }

    public void driveStraight() {
        leftGroup.set(0.6 + pidError);
        rightGroup.set(0.6 - pidError);
    }
}