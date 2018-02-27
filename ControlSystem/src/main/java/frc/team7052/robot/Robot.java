package frc.team7052.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.team7052.robot.Commands.CommandBase;
import frc.team7052.robot.Commands.TeleopCommandGroup;
import frc.team7052.robot.Systems.OI;
import frc.team7052.robot.Vision.MainVision;

public class Robot extends IterativeRobot {
    OI oi;

    TeleopCommandGroup teleopCommandGroup;
    MainVision mainVision;

    Scheduler scheduler;

    @Override
    public void robotInit() {
        CommandBase.init();
        mainVision.getInstance(); // starts computer vision
        oi = new OI(); // get instance for the joystick
        teleopCommandGroup = new TeleopCommandGroup(oi); // add teleop command group
        scheduler = Scheduler.getInstance(); // local variable for static variable scheduler
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
        //teleop command
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
    }
}
