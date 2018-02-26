
package frc.team7052.robot.Systems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.team7052.robot.Constants;
import frc.team7052.robot.Structs.Vector3D;
import frc.team7052.robot.Enums.DrivingState;

public class DriveTrain extends Subsystem {
    public static DifferentialDrive drive;
    private SpeedControllerGroup leftGroup;
    private SpeedControllerGroup rightGroup;

    public static AHRS ahrs;

    public static boolean drivingCarefully = false;
    public static double prevZValue = 0.0;

    private DriveTrain() {
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

        ahrs.reset();

    }

    @Override
    protected void initDefaultCommand() {
    }

    public static DriveTrain instance;

    public static DriveTrain getInstance() {
        if (instance == null) instance = new DriveTrain();
        return instance;
    }

    public static void arcadeDrive(OI oi) {
        Vector3D leftStick = oi.getLeftStick();
        //if prevZValue was just released, then toggle driving carefully
        prevZValue = leftStick.z;

        //set speed of the motor
        double speed = oi.getRightBumper();
        if (oi.getLeftBumper() > 0) speed = -oi.getLeftBumper();
        //arcade drive
        //choose multiplier based on if they are currently driving slowly
        double speedMultiplier = drivingCarefully ? Constants.kSpeedSlowMultiplier : Constants.kSpeedFastMultiplier;
        double rotationMultiplier = drivingCarefully ? Constants.kRotationSlowMultiplier : Constants.kRotationFastMultiplier;
        drive.arcadeDrive(speed * speedMultiplier,leftStick.x * rotationMultiplier);
    }

    public static void tankDrive(OI oi, DrivingState drivingState) {
        Vector3D leftStick = oi.getLeftStick();
        Vector3D rightStick = oi.getRightStick();

        double speed = leftStick.y;

        double leftSpeed = speed;
        double rightSpeed = speed;

        double turnValue = rightStick.x;

        if (turnValue > 0) { // turningRight
            rightSpeed *= 1 - turnValue * 0.55;
        }
        else if (turnValue < 0) { // turning left
            leftSpeed *= turnValue * 0.55 + 1;
        }

        if (Math.abs(speed) < 0.01  && turnValue != 0) {
            leftSpeed = -turnValue;
            rightSpeed = turnValue;
        }

        leftSpeed = getNormalizedSpeed(leftSpeed, drivingState);
        rightSpeed = getNormalizedSpeed(rightSpeed, drivingState);

        //slowest speed = kSlowestRobotSpeed
        //normalize speed so that it is always between kSlowestRobotSpeed and 1, -Constants.kSlowestRobotSpeed and -1, or 0

        drive.tankDrive(leftSpeed, rightSpeed);
    }

    public void stop() {
        drive.stopMotor();
    }

    private static double getNormalizedSpeed(double speed, DrivingState state) {
        //speed is always between -1 -> -0.45 or 0 or 0.45 -> 1
        speed *= 1 - Constants.kSlowestRobotSpeed;
        if (Math.abs(speed) < 0.01) return 0;
        if (speed > 0) speed = speed + Constants.kSlowestRobotSpeed;
        else speed = speed - Constants.kSlowestRobotSpeed;

        if (state == DrivingState.careful) speed *= 0.6;
        else if (state == DrivingState.regular) speed *= 0.8;
        else if (state == DrivingState.turbo) speed *= 0.95;

        if (speed > 0 && speed < 0.45) speed = 0.45;
        else if (-0.45 < speed && speed < 0) speed = -0.45;
        return speed;
    }
}