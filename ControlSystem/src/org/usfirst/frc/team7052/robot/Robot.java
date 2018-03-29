/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team7052.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

import java.util.*;

import org.usfirst.frc.team7052.robot.commands.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
    OI driveOI;
    OI liftOI;
    AutoDriveStraight autoCommand;

    TeleopCommandGroup teleopCommandGroup;
    MainVision mainVision;

    Scheduler scheduler;
    List<OI> ois = new ArrayList<>();

    @Override
    public void robotInit() {
    		// TODO: use smart dashboard to load joysticks
    		// fill here
		// initialize ois
        CommandBase.init();
        mainVision = MainVision.getInstance();
        teleopCommandGroup = new TeleopCommandGroup(driveOI, liftOI); // add teleop command group
        scheduler = Scheduler.getInstance(); // local variable for static variable scheduler
        autoCommand = new AutoDriveStraight();
    }

    @Override
    public void disabledInit() {
        Scheduler.getInstance().removeAll();
    }

    @Override
    public void autonomousInit() {
       scheduler.removeAll();
        //scheduler.add(new CoolAuton());
       autoCommand.start();
    }

    @Override
    public void teleopInit() {
        scheduler.removeAll();
        //teleop command
        Scheduler.getInstance().add(teleopCommandGroup);
    }

    @Override
    public void autonomousPeriodic() {
    	scheduler.run();
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
