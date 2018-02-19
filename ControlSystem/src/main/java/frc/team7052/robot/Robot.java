package frc.team7052.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.team7052.robot.Commands.Drive.DriveRobot;
import frc.team7052.robot.Systems.OI;

public class Robot extends IterativeRobot {
    DriveRobot driveCommand;
    Spark motor = new Spark(4);
    OI oi;
    @Override
    public void robotInit() {
        oi = new OI();
        driveCommand = new DriveRobot(oi);

        //TODO: Create Vision System
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
        Scheduler.getInstance().add(driveCommand);

    }

    @Override
    public void autonomousPeriodic() {

    }

    @Override
    public void teleopPeriodic() {
        System.out.println(oi.buttonPressed(Constants.kButtonSecondLeftBumper));
        double left = oi.getLeftBumper();
        double right = oi.getRightBumper();
        if (left > 0.3) {
            motor.set(0.7);
            System.out.println("Left");
        }
        else if (right > 0.3) {
            motor.set(-1.0);
            System.out.println("Right");
        }
        else {
            motor.set(-0.2);
            System.out.println("stop");
        }
        Scheduler.getInstance().run();
    }

    @Override
    public void testPeriodic() { }
}
