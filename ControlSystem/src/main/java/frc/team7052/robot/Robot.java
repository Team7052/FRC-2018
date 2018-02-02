package frc.team7052.robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.*;
import frc.team7052.robot.Systems.OI;
import org.opencv.core.Mat;

public class Robot extends IterativeRobot {
    CommandBase commandBase;
    OI oi;
    @Override
    public void robotInit() {
        commandBase.init();
        oi = new OI();

        //TODO: Create Vision System
        new Thread(() -> {
            UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
            camera.setResolution(320, 240);

            CvSink cvSink = CameraServer.getInstance().getVideo();
            CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 320, 240);

            Mat source = new Mat();
            Mat output = new Mat();

            while(!Thread.interrupted()) {
                cvSink.grabFrame(source);
                outputStream.putFrame(output);
            }
        }).start();
    }

    @Override
    public void disabledInit() {
    }

    @Override
    public void autonomousInit() {
    }

    @Override
    public void teleopInit() {

    }

    @Override
    public void autonomousPeriodic() {

    }

    @Override
    public void teleopPeriodic() {
        /*double speed = 0;
        if (stick.getThrottle() > 0) {
            speed = -stick.getThrottle();
        }
        else if (stick.getTwist() > 0) speed = stick.getTwist();
        drive.arcadeDrive(speed, stick.getX() * 0.67);*/
    }

    @Override
    public void testPeriodic() { }
}
