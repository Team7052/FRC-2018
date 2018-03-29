package org.usfirst.frc.team7052.robot.subsystems;

import org.usfirst.frc.team7052.robot.Constants;
import org.usfirst.frc.team7052.robot.OI;
import org.usfirst.frc.team7052.robot.OIMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ElevatorLift extends PIDSubsystem {
	private enum LiftStage {
		bottom, middle, top
	}
	private static ElevatorLift instance;

    private static Spark liftMotor;
    private static AnalogInput encoder;
    public static LiftStage liftStage;

    private ElevatorLift() {
    		super(0.0,0.0,0.0);
        liftMotor = new Spark(Constants.kMotorElevatorLift);
        liftMotor.setInverted(true); // invert so that positive motor values make the lift go up while negative values make the lift go down
        encoder = new AnalogInput(Constants.kLiftEncoder);
        // lift stage starts at the bottom always
        liftStage = LiftStage.bottom;
        
        this.setAbsoluteTolerance(1.0);
        this.enable();
        this.setSetpoint(0);
    }

    public static ElevatorLift getInstance() {
        if (instance == null) instance = new ElevatorLift();
        return instance;
    }

    public static void elevatorManual(OI oi) {
        boolean leftPressed = oi.buttonPressed(OIMap.leftBumper2);
        boolean rightPressed = oi.buttonPressed(OIMap.rightBumper2);

        if ((leftPressed && rightPressed) || (!leftPressed && !rightPressed)) {
            liftMotor.set(0.2);
        }
        else if (leftPressed) { //down
            liftMotor.set(-0.5);
        }
        else {
            liftMotor.set(0.9); // up
        }
    }
    
    public void elevatorPID(OI oi) {
    		int dPadVal = oi.getDPad();
    		if (dPadVal == 1) liftStage = LiftStage.top;
    		else if (dPadVal == 5) liftStage = liftStage.bottom;
    		else if (dPadVal != 0) liftStage = liftStage.middle;
    		
    		if (liftStage == LiftStage.top) {
    			this.setSetpoint(20); // 20 turns
    		}
    		else if (liftStage == LiftStage.bottom) {
    			this.setSetpoint(0);
    		}
    		else if (liftStage == LiftStage.middle) {
    			this.setSetpoint(10);
    		}
    }

    @Override
    protected void initDefaultCommand() {
    }

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		
	}
}
