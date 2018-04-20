package org.usfirst.frc.team7052.robot.commands.auto;

import org.usfirst.frc.team7052.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraight extends CommandBase {
	long duration;
	double speed;
	boolean timeToggle = true;
	long startTime;

    public DriveStraight(long duration, double speed) {
        // Use requires() here to declare subsystem dependencies
    	requires(driveTrain);
    	this.duration = duration;
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (timeToggle) {
    		timeToggle = false;
    		startTime = System.currentTimeMillis();
    	}
    	else {
    		driveTrain.driveStraight(speed);
    		this.isFinished();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (System.currentTimeMillis() < duration) {
            return false;
    	}
    	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	driveTrain.stop();
    	driveTrain.isMovingStraight = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
