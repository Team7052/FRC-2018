package frc.team7052.robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/*
 * Joystick Notes from Kevin Bai
 * left stick = getX(), getY(), getZ(); values between -1 to 1
 * right stick = TODO
 * d-pad = TODO
 * left bumper = getThrottle(); values between 0 and 1
 * left bumper 2 = TODO
 * right bumper = getTwist(); values between 0 and 1
 * right bumper 2 = TODO
 * mode = TODO
 * select = TODO
 * */

public class Robot extends IterativeRobot {
    Spark motor0 = new Spark(0); // left side
    Spark motor1 = new Spark(1); // left side
    Spark motor2 = new Spark(2); // right side
    Spark motor3 = new Spark(3); // right side

    SpeedControllerGroup leftGroup;
    SpeedControllerGroup rightGroup;
    Joystick stick = new Joystick(0);

    DifferentialDrive drive;

    Timer timer = new Timer();

    @Override
    public void robotInit() {
        leftGroup = new SpeedControllerGroup(motor0, motor1);
        rightGroup = new SpeedControllerGroup(motor2, motor3);

        drive = new DifferentialDrive(leftGroup, rightGroup);

        new Thread(() -> {
            UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
            camera.setResolution(320, 240);

            CvSink cvSink = CameraServer.getInstance().getVideo();
            CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 320, 240);

            Mat source = new Mat();
            Mat output = new Mat();

            while(!Thread.interrupted()) {
                cvSink.grabFrame(source);
                Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
                outputStream.putFrame(output);
            }
        }).start();
    }

    @Override
    public void disabledInit() {
    }

    @Override
    public void autonomousInit() {
        timer.start();
    }

    @Override
    public void teleopInit() {
    }

    @Override
    public void testInit() { }

    @Override
    public void disabledPeriodic() { }

    @Override
    public void autonomousPeriodic() {
        if (timer.get() < 2) {
            drive.arcadeDrive(0.5, 0);
        }
        else {
            drive.stopMotor();
        }

    }

    @Override
    public void teleopPeriodic() {
        double speed = 0;
        if (stick.getThrottle() > 0) {
            speed = -stick.getThrottle();
        }
        else if (stick.getTwist() > 0) speed = stick.getTwist();
        drive.arcadeDrive(speed, stick.getX() * 0.67);
    }

    @Override
    public void testPeriodic() { }
}
