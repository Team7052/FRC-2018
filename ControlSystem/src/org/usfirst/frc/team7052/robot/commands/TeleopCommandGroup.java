package org.usfirst.frc.team7052.robot.commands;

import org.usfirst.frc.team7052.robot.OI;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TeleopCommandGroup extends CommandGroup {
    public TeleopCommandGroup(OI driveOI, OI liftOI) {
        addParallel(new DriveRobot(driveOI));
        addParallel(new ControlElevator(liftOI));
        addParallel(new ControlArm(liftOI));
    }
}
