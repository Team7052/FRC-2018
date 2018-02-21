package frc.team7052.robot.Commands.Claw;

import frc.team7052.robot.Commands.CommandBase;
import frc.team7052.robot.Enums.Direction;

public class SpinClawWheelsOut extends CommandBase {
    public SpinClawWheelsOut() {
        requires(claw);
    }

    @Override
    protected void initialize() {
        claw.spinWheels(Direction.out);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
