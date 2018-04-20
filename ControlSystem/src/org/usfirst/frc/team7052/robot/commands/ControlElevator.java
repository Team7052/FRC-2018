package org.usfirst.frc.team7052.robot.commands;

import org.usfirst.frc.team7052.robot.OI;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ControlElevator extends CommandBase {
    public OI oi;
    //CommandGroup commandGroup;
    public ControlElevator(OI oi) {
        this.oi = oi;
        requires(elevatorLift);
        //.commandGroup = new CommandGroup();
    }

    @Override
    protected void initialize() {
    	
    }

    @Override
    protected void execute() {
        //elevatorLift.liftElevator(oi);
        elevatorLift.elevatorManual(oi);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}

