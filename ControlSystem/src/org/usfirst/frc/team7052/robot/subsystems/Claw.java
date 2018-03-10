package org.usfirst.frc.team7052.robot.subsystems;

import org.usfirst.frc.team7052.robot.Constants;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Claw extends Subsystem {
    private static Claw instance;
    Spark verticalLiftMotor = new Spark(Constants.kMotorClawVerticalLift);
    Spark floatingArmMotor = new Spark(Constants.kMotorClawFloatingArm);

    private Claw() {
    }
    public static Claw getInstance() {
        if (instance == null) instance = new Claw();
        return instance;
    }

    //TODO: Find Claw positions when opened and closed

    /* Functions for claw manipulation */
    public void closeFloatingArm() {
        floatingArmMotor.set(0.5);
    }
    public void openFloatingArm() {
        floatingArmMotor.set(-0.5);
    }

    public void stopFloatingArm() {
        floatingArmMotor.set(0);
    }

    public void liftArm() {
        verticalLiftMotor.set(-1);
    }
    public void lowerArm() {
        verticalLiftMotor.set(0.4);
    }
    public void hoverArm() {
        verticalLiftMotor.set(-0.2);
    }

    @Override
    protected void initDefaultCommand() {

    }
}
