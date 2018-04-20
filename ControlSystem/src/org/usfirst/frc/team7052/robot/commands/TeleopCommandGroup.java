package org.usfirst.frc.team7052.robot.commands;

import org.usfirst.frc.team7052.robot.Constants;
import org.usfirst.frc.team7052.robot.OI;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TeleopCommandGroup extends CommandGroup {
	public DriveRobot driveRobotCommand;
	public ControlElevator controlElevatorCommand;
	public ControlArm controlArmCommand;
    public TeleopCommandGroup() {
    	OI mainOI;
    	OI secondOI;
    	if (Constants.ois.size() > 1) {
    		mainOI = Constants.ois.get(0);
    		secondOI = Constants.ois.get(1);
    	}
    	else {
    		mainOI = Constants.ois.get(0);
    		secondOI = Constants.ois.get(0);
    	}
    	driveRobotCommand = new DriveRobot(mainOI);
    	controlElevatorCommand = new ControlElevator(secondOI);
    	controlArmCommand = new ControlArm(secondOI);
        addParallel(driveRobotCommand);
        addParallel(controlElevatorCommand);
        addParallel(controlArmCommand);
    }
}
