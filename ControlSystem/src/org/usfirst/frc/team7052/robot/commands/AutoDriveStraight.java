package org.usfirst.frc.team7052.robot.commands;

import java.awt.Robot;

import org.usfirst.frc.team7052.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveStraight extends CommandBase {

	private double startTime, currentTime, duration;
	private boolean timeToggle;
	
    public AutoDriveStraight() {
       requires(driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    		timeToggle = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (timeToggle == true) {
    		startTime = System.currentTimeMillis();
    		timeToggle = false;
    	}
    	
    	currentTime = System.currentTimeMillis();
    	duration = (currentTime - startTime);
    	
    	if (duration <= 3000) {
    		driveTrain.leftGroup.set(-0.55);
    		driveTrain.rightGroup.set(0.5);
    	}
    	else {
    		driveTrain.leftGroup.set(0);
    		driveTrain.rightGroup.set(0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
