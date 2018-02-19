package frc.team7052.robot.Systems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team7052.robot.Constants;
import frc.team7052.robot.Enums.WheelDirection;

public class Claw extends Subsystem {
    private static Claw instance;
    Spark wheelsMotor = new Spark(Constants.kMotorClawWheels);
    Spark armsMotor = new Spark(Constants.kMotorClawArms);

    private Claw() {
    }
    public static Claw getInstance() {
        if (instance == null) instance = new Claw();
        return instance;
    }

    //TODO: Find Claw positions when opened and closed

    /* Functions for claw manipulation */
    public void spinWheels(WheelDirection wheelDirection) {
        if (wheelDirection == WheelDirection.in)
            wheelsMotor.set(1);
        else
            wheelsMotor.set(-1);
    }
    public void closeArms() {
        armsMotor.setPosition(Constants.kClawPositionClose);
    }
    public void openArms() {
        armsMotor.setPosition(Constants.kClawPositionOpen);
    }

    @Override
    protected void initDefaultCommand() {

    }
}
