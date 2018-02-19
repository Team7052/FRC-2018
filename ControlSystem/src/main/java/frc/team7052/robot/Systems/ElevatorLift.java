package frc.team7052.robot.Systems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team7052.robot.Constants;

public class ElevatorLift extends Subsystem {
    private static ElevatorLift instance;

    public static Spark liftMotor = new Spark(Constants.kMotorElevatorLift);

    private ElevatorLift() {
    }

    public static ElevatorLift getInstance() {
        if (instance == null) instance = new ElevatorLift();
        return instance;
    }

    @Override
    protected void initDefaultCommand() {

    }
}
