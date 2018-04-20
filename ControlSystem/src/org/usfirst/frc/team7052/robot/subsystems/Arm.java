package org.usfirst.frc.team7052.robot.subsystems;

import org.usfirst.frc.team7052.robot.Constants;
import org.usfirst.frc.team7052.robot.OI;
import org.usfirst.frc.team7052.robot.OIMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem {
    private static Arm instance;
    private Spark verticalLiftMotor;
    private Spark rotatingLeftMotor;
    private Spark rotatingRightMotor;
    private Spark leftWheelsMotor;
    private Spark rightWheelsMotor;

    private Arm() {
    	verticalLiftMotor = new Spark(Constants.kMotorArmVerticalLift);
        rotatingLeftMotor = new Spark(Constants.kMotorArmRotatingLeft);
        rotatingRightMotor = new Spark(Constants.kMotorArmRotatingRight);
        leftWheelsMotor = new Spark(Constants.kMotorArmWheelsLeft);
        rightWheelsMotor = new Spark(Constants.kMotorArmWheelsRight);
    }
    public static Arm getInstance() {
        if (instance == null) instance = new Arm();
        return instance;
    }

    //TODO: Find Arm positions when opened and closed

    /* Functions for arm manipulation */
    public void spinWheels(OI oi) {
    	double leftX = oi.getInput(OIMap.leftAxisX) * 0.1; // normalize speed since speed difference between wheels will be small
    	double leftY = oi.getInput(OIMap.leftAxisY);
    	
    	double leftSpeed = leftY;
    	double rightSpeed = leftY;
    	
    	leftSpeed -= leftX;
    	rightSpeed -= leftX;
    	
    	if (leftSpeed < -1) leftSpeed = -1;
    	else if (leftSpeed > 1) leftSpeed = 1;
    	if (rightSpeed < -1) rightSpeed = -1;
    	else if (rightSpeed > 1) rightSpeed = 1;
    	
    	leftWheelsMotor.set(leftSpeed);
    	rightWheelsMotor.set(rightSpeed);
    }

    public void openRotatingArms() {
    	rotatingLeftMotor.set(0.2);
    	rotatingRightMotor.set(0.2);
    }
    
    public void closeRotatingArms() {
    	rotatingLeftMotor.set(0);
    	rotatingRightMotor.set(0);
    }
    
    public void squeezeCube() {
    	rotatingLeftMotor.set(-0.2);
    	rotatingRightMotor.set(0.2);
    }
    
    public void liftArm() {
        verticalLiftMotor.set(-0.5);
    }
    public void lowerArm() {
        verticalLiftMotor.set(0.4);
    }
    public void hoverArm() {
        verticalLiftMotor.set(-0.1);
    }

    @Override
    protected void initDefaultCommand() {

    }
}
