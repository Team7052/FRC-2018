package frc.team7052.robot.Commands.Claw;

import frc.team7052.robot.Commands.CommandBase;
import frc.team7052.robot.Enums.Direction;

public class SpinClawWheelsIn extends CommandBase {
    public SpinClawWheelsIn() {
        requires(claw);
    }

    @Override
    protected void initialize() {
        claw.spinWheels(Direction.in);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
