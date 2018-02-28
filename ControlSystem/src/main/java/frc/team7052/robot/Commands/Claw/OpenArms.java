package frc.team7052.robot.Commands.Claw;

import frc.team7052.robot.Commands.CommandBase;

public class OpenArms extends CommandBase {
    public OpenArms() {
        requires(claw);
    }

    @Override
    protected void initialize() {
        setTimeout(1.0);
        claw.openFloatingArm();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
