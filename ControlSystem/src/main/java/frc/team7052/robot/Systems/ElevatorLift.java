package frc.team7052.robot.Systems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team7052.robot.Constants;

public class ElevatorLift extends Subsystem {
    private static ElevatorLift instance;

    public static Spark liftMotor;

    private ElevatorLift() {
        liftMotor = new Spark(Constants.kMotorElevatorLift);
    }

    public static ElevatorLift getInstance() {
        if (instance == null) instance = new ElevatorLift();
        return instance;
    }

    public static void liftElevator(OI oi) {
        boolean leftPressed = oi.buttonPressed(Constants.kButtonSecondLeftBumper);
        boolean rightPressed = oi.buttonPressed(Constants.kButtonSecondRightBumper);

        if (leftPressed && rightPressed) {
            // stop lift
            liftMotor.set(-0.2);
        }
        else if (leftPressed) {
            liftMotor.set(0.7);
        }
        else if (rightPressed) {
            liftMotor.set(-1);
        }
        else {
            // stop lift
            liftMotor.set(-0.2);
        }
    }

    @Override
    protected void initDefaultCommand() {

    }
}
