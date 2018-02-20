package frc.team7052.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.team7052.robot.Commands.CommandBase;
import frc.team7052.robot.Commands.Drive.DriveRobot;
import frc.team7052.robot.Commands.Drive.TeleopCommandGroup;
import frc.team7052.robot.Systems.OI;

public class Robot extends IterativeRobot {
    OI oi;

    TeleopCommandGroup teleopCommandGroup;

    @Override
    public void robotInit() {
        CommandBase.init();
        oi = new OI();
        teleopCommandGroup = new TeleopCommandGroup(oi);

        // TODO: Create Vision System
        /*new Thread(() -> {
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
        }).start();*/
    }

    @Override
    public void disabledInit() {
    }

    @Override
    public void autonomousInit() {
    }

    @Override
    public void teleopInit() {
        Scheduler.getInstance().add(teleopCommandGroup);
    }

    @Override
    public void autonomousPeriodic() {

    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void testPeriodic() { }
}
