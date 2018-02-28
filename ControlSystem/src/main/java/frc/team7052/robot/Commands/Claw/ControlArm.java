package frc.team7052.robot.Commands.Claw;

import frc.team7052.robot.Commands.CommandBase;
import frc.team7052.robot.Constants;
import frc.team7052.robot.Enums.Direction;
import frc.team7052.robot.Systems.OI;

public class ControlArm extends CommandBase {
    private OI oi;
    public ControlArm(OI oi) {
        this.oi = oi;
        requires(claw);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        if (oi.buttonPressed(Constants.kButtonA)) {
            claw.openFloatingArm();
        }
        else if (oi.buttonPressed(Constants.kButtonB)) {
            claw.closeFloatingArm();
        }
        else {
            claw.stopFloatingArm();
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
