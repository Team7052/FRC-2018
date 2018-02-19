package frc.team7052.robot.Commands.Claw;

import frc.team7052.robot.Commands.CommandBase;

public class CloseArms extends CommandBase {
    public CloseArms() {
        requires(claw);
    }

    @Override
    protected void initialize() {
        claw.closeArms();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
