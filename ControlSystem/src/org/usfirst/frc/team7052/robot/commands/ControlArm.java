package org.usfirst.frc.team7052.robot.commands;

import org.usfirst.frc.team7052.robot.Constants;
import org.usfirst.frc.team7052.robot.OI;

public class ControlArm extends CommandBase {

    private OI oi;
    public ControlArm(OI oi) {
        this.oi = oi;
        requires(claw);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
    	System.out.println(oi.buttonPressed(Constants.kButtonA));
        if (oi.buttonPressed(Constants.kButtonA)) {
        	claw.liftArm();
        }
        else if (oi.buttonPressed(Constants.kButtonB)) {
        	claw.lowerArm();
        }
        else {
        	claw.hoverArm();
        }
        
        if (oi.buttonPressed(Constants.kButtonX)) {
        	System.out.println("AAA");
        	claw.openFloatingArm();
        }
        else if (oi.buttonPressed(Constants.kButtonY)) {
        	claw.closeFloatingArm();
        }
        else {
        	claw.stopFloatingArm();
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}