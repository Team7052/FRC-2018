package frc.team7052.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.team7052.robot.Commands.CommandBase;
import frc.team7052.robot.Commands.TeleopCommandGroup;
import frc.team7052.robot.Systems.OI;

import javax.swing.plaf.synth.SynthCheckBoxUI;

public class Robot extends IterativeRobot {
    OI oi;

    TeleopCommandGroup teleopCommandGroup;

    AHRS ahrs;
    Scheduler scheduler;

    @Override
    public void robotInit() {
        CommandBase.init();
        oi = new OI();
        teleopCommandGroup = new TeleopCommandGroup(oi);
        scheduler = Scheduler.getInstance();

        //motor = new Spark(5);

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
        Scheduler.getInstance().removeAll();
    }

    @Override
    public void autonomousInit() {
        scheduler.removeAll();
    }

    @Override
    public void teleopInit() {
        scheduler.removeAll();
        Scheduler.getInstance().add(teleopCommandGroup);
    }

    @Override
    public void autonomousPeriodic() {

    }

    @Override
    public void teleopPeriodic() {
        scheduler.run();
    }

    @Override
    public void testInit() {
        scheduler.removeAll();
    }

    @Override
    public void testPeriodic() {
        if (oi.buttonPressed(Constants.kButtonA)) {
            CommandBase.driveTrain.rotateToAngle(45);
        }
        else if (oi.buttonPressed(Constants.kButtonB)) {
            CommandBase.driveTrain.rotateToAngle(30);
        }
        else if (oi.buttonPressed(Constants.kButtonX)) {
            CommandBase.driveTrain.rotateToAngle(80);
        }
        else if (oi.buttonPressed(Constants.kButtonY)) {
            CommandBase.driveTrain.rotateToAngle(-90);
        }
    }
}
