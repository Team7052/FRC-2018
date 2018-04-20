package org.usfirst.frc.team7052.robot.subsystems;

import org.usfirst.frc.team7052.robot.Constants;
import org.usfirst.frc.team7052.robot.OI;
import org.usfirst.frc.team7052.robot.OIMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ElevatorLift extends Subsystem {
	public enum LiftStage {
		bottom, middle, top
	}
	private static ElevatorLift instance;

    private static TalonSRX liftMotor;
    public static LiftStage liftStage;
    
    private static final int kPIDLoopIdx = 0; // Only support primary PID loop
    private static final int kSlotIdx = 0; // choose which PID slot to pull gains from. Use 0 or 1
    private static final int kTimeoutMs = 10;
    
    private static boolean toggledManual = false;

    private ElevatorLift() {
        liftMotor = new TalonSRX(Constants.kMotorElevatorLift);
        // configure encoder
        liftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx , kTimeoutMs);
        // liftMotor.setSensorPhase(true); // uncomment this line if sensor is out of phase
        //set relevant to frame periods to be as fast as period rate
        liftMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, kTimeoutMs);
        liftMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, kTimeoutMs);
        
        //set peak and nominal motor outputs
        liftMotor.configNominalOutputForward(0, kTimeoutMs);
        liftMotor.configNominalOutputReverse(0, kTimeoutMs);
        liftMotor.configPeakOutputForward(1, kTimeoutMs);
        liftMotor.configPeakOutputReverse(-1, kTimeoutMs);
        
        // set closed loop PID constants
        liftMotor.selectProfileSlot(kSlotIdx, kPIDLoopIdx);
        liftMotor.config_kF(0, 0.2, kTimeoutMs);
        liftMotor.config_kP(0,  0.2,  kTimeoutMs);
        liftMotor.config_kI(0, 0, kTimeoutMs);
        liftMotor.config_kD(0, 0, kTimeoutMs);
        
        // set acceleration and vcruise velocity
        liftMotor.configMotionCruiseVelocity(15000, kTimeoutMs);
        liftMotor.configMotionAcceleration(6000, kTimeoutMs);
        // zero the sensor; set the sensor position
        liftMotor.setSelectedSensorPosition(0, kPIDLoopIdx, kTimeoutMs);
        // lift stage starts at the bottom always
        liftStage = LiftStage.bottom;
    }

    public static ElevatorLift getInstance() {
        if (instance == null) instance = new ElevatorLift();
        return instance;
    }

    public static void elevatorManual(OI oi) {
        boolean leftPressed = oi.getInput(OIMap.leftBumper2) == 1;
        boolean rightPressed = oi.getInput(OIMap.rightBumper2) == 1;

        if ((leftPressed && rightPressed) || (!leftPressed && !rightPressed)) {
            liftMotor.set(ControlMode.PercentOutput, -0.1);
        }
        else if (leftPressed) { //down
            liftMotor.set(ControlMode.PercentOutput, -0.5);
        }
        else {
            liftMotor.set(ControlMode.PercentOutput, 0.9); // up
        }
    }
    
    public void elevatorPID(OI oi) {
		int dPadVal = oi.getDPad();
		if (dPadVal == 0) {
			liftStage = LiftStage.top;
			toggledManual = false;
		}
		else if (dPadVal == 4) {
			liftStage = liftStage.bottom;
			toggledManual = false;
		}
		else if (dPadVal != -1) {
			liftStage = liftStage.middle;
			toggledManual = false;
		}
		
		// if user hits the left or right  bumpers
		boolean leftPressed = Math.abs(oi.getInput(OIMap.leftBumper)) > 0.1;
		boolean rightPressed = Math.abs(oi.getInput(OIMap.rightBumper)) > 0.1;
		
		if (leftPressed || rightPressed) toggledManual = true;
		
		if (!toggledManual) {
			if (liftStage == LiftStage.top) {
    			// set target position: 4096 ticks / revolution * # of revolutions
    			double targetSetpoint = 4096 * 10;
    			liftMotor.set(ControlMode.MotionMagic, targetSetpoint);
    		}
    		else if (liftStage == LiftStage.bottom) {
    			// set target position: 4096 ticks / revolution * # of revolutions
    			double targetSetpoint = 0;
    			liftMotor.set(ControlMode.MotionMagic, targetSetpoint);
    		}
    		else if (liftStage == LiftStage.middle) {
    			// set target position: 4096 ticks / revolution * # of revolutions
    			double targetSetpoint = 4096 * 5;
    			liftMotor.set(ControlMode.MotionMagic, targetSetpoint);
    		}
		}
		else {
			if (leftPressed) {
				liftMotor.set(ControlMode.PercentOutput, -0.5);
			}
			else if (rightPressed) {
				liftMotor.set(ControlMode.PercentOutput, 0.5);
			}
			else toggledManual = false;
		}
    		
    }
    
    public void autoSwitch() {
    	liftMotor.set(ControlMode.MotionMagic, 4096 * 5);
    }

    @Override
    protected void initDefaultCommand() {
    }
}
