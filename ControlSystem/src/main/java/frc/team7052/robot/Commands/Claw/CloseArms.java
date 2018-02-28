package frc.team7052.robot.Commands.Claw;

import frc.team7052.robot.Commands.CommandBase;

public class CloseArms extends CommandBase {
    public CloseArms() {
        requires(claw);
        setTimeout(1.0);
    }

    @Override
    protected void initialize() {
        claw.closeFloatingArm();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
