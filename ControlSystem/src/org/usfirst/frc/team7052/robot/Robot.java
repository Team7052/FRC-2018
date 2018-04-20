/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team7052.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

import java.util.*;

import org.usfirst.frc.team7052.robot.OI.JoystickType;
import org.usfirst.frc.team7052.robot.commands.*;
import org.usfirst.frc.team7052.robot.commands.auto.BasicAuton;

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
    BasicAuton autoCommand;

    TeleopCommandGroup teleopCommandGroup;
    MainVision mainVision;

    Scheduler scheduler;
    List<OI> ois = new ArrayList<>();
    String gameData;

    @Override
    public void robotInit() {
    	// TODO: use shuffleboard to load joysticks
    	// fill here
    	Constants.ois.add(new OI(0, JoystickType.xbox));
    	Constants.ois.add(new OI(1, JoystickType.logitech));
		// initialize ois
        CommandBase.init();
       // mainVision = MainVision.getInstance();
        teleopCommandGroup = new TeleopCommandGroup(); // add teleop command group
        scheduler = Scheduler.getInstance(); // local variable for static variable scheduler
        autoCommand = new BasicAuton();
    }

    @Override
    public void disabledInit() {
        Scheduler.getInstance().removeAll();
    }

    @Override
    public void autonomousInit() {
        // init game data
    	gameData = DriverStation.getInstance().getGameSpecificMessage();
    	if (gameData.length() > 0) {
    		if (gameData.charAt(0) == 'L') {
    			// autonomous to the left
    		}
    		else {
    			// autonomous to the right
    		}
    	}
    	scheduler.removeAll();
    	autoCommand.start();
    }
    
    public void resetJoysticks() {
    	if (Constants.ois.size() > 1) {
    		teleopCommandGroup.controlArmCommand.oi = Constants.ois.get(1);
    		teleopCommandGroup.controlElevatorCommand.oi = Constants.ois.get(1);
    		teleopCommandGroup.driveRobotCommand.oi = Constants.ois.get(0);
    		CommandBase.driveTrain.setOI(Constants.ois.get(0));
    	}
    	else if (Constants.ois.size() > 0){
    		teleopCommandGroup.controlArmCommand.oi = Constants.ois.get(0);
    		teleopCommandGroup.controlElevatorCommand.oi = Constants.ois.get(0);
    		teleopCommandGroup.driveRobotCommand.oi = Constants.ois.get(0);
    		CommandBase.driveTrain.setOI(Constants.ois.get(0));
    	}
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
