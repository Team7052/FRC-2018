package org.usfirst.frc.team7052.robot.commands.auto;

import org.usfirst.frc.team7052.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftToSwitch extends CommandBase {

    public LiftToSwitch() {
        // Use requires() here to declare subsystem dependencies
    	setTimeout(3);
    	requires(elevatorLift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	elevatorLift.autoSwitch();
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
