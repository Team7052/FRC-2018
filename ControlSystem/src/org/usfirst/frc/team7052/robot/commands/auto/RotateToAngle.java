package org.usfirst.frc.team7052.robot.commands.auto;

import org.usfirst.frc.team7052.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateToAngle extends CommandBase {
	double degrees;
	final double kMaxTime = 2000;
	double startTime;
	
	boolean timeToggle = true;

    public RotateToAngle(double degrees) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(driveTrain);
    	this.degrees = degrees;
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
        	this.isFinished();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (driveTrain.rotateToAngle(degrees)) {
    		return true;
    	}
    	if (System.currentTimeMillis()  - startTime> kMaxTime) return true;
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
		driveTrain.rotatingToAngle = false;
    	driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
