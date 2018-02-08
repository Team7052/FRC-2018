
package frc.team7052.robot.Systems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.team7052.robot.Constants;
import frc.team7052.robot.Structs.Vector3D;

public class DriveTrain extends Subsystem {
    public static DifferentialDrive drive;
    private SpeedControllerGroup leftGroup;
    private SpeedControllerGroup rightGroup;

    public static boolean drivingCarefully = false;
    public static double prevZValue = 0.0;

    private DriveTrain() {
        leftGroup = new SpeedControllerGroup(new Spark(Constants.kFrontLeftMotor), new Spark(Constants.kBackLeftMotor));
        rightGroup = new SpeedControllerGroup(new Spark(Constants.kFrontRightMotor), new Spark(Constants.kBackRightMotor));
        leftGroup.setInverted(true);
        rightGroup.setInverted(true);
        drive = new DifferentialDrive(leftGroup, rightGroup);
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
       /* if (leftStick.z == 0 && prevZValue != 0) drivingCarefully = !drivingCarefully; */
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

    public static void tankDrive(OI oi) {
        Vector3D leftStick = oi.getLeftStick();
        Vector3D rightStick = oi.getRightStick();
        double speed = leftStick.y;
        double leftSpeed = speed;
        double rightSpeed = speed;

        double turnValue = rightStick.x;
        System.out.println(speed + " " + turnValue);

        if (turnValue > 0) { // turningRight
            rightSpeed *= 1 - turnValue;
        }
        else if (turnValue < 0) {
            leftSpeed *= turnValue + 1;
        }

        if (Math.abs(speed) < 0.01  && turnValue != 0) {
            leftSpeed = -turnValue;
            rightSpeed = turnValue;
        }

        drive.tankDrive(leftSpeed, rightSpeed);
    }

    public void stop() {
        drive.stopMotor();
    }
}


