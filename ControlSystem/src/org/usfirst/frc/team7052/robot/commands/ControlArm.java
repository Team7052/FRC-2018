package org.usfirst.frc.team7052.robot.commands;

import org.usfirst.frc.team7052.robot.Constants;
import org.usfirst.frc.team7052.robot.OI;
import org.usfirst.frc.team7052.robot.OIMap;

import edu.wpi.first.wpilibj.Spark;

public class ControlArm extends CommandBase {
	
	Spark verticalLiftMotor;
	Spark wheelsLeftMotor;
	Spark wheelsRightMotor;
	Spark rotatingLeftMotor;
	Spark rotatingRightMotor;

    public OI oi;
    public ControlArm(OI oi) {
        this.oi = oi;
        requires(arm);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
    	arm.spinWheels(oi);
    	if (oi.getInput(OIMap.buttonX) == 1) {
    		arm.openRotatingArms();
    	}
    	else if (oi.getInput(OIMap.buttonA) == 1) {
    		arm.squeezeCube();
    	}
    	else {
    		arm.closeRotatingArms();
    	}
    	
    	double rightAxisY = oi.getInput(OIMap.rightAxisY);
    	if (rightAxisY > 0.3) {
    		arm.liftArm();
    	}
    	else if (rightAxisY < -0.3) {
    		arm.lowerArm();
    	}
    	else arm.hoverArm();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}