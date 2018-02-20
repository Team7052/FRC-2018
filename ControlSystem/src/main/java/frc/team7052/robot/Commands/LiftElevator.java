package frc.team7052.robot.Commands;

import frc.team7052.robot.Systems.OI;

public class LiftElevator extends CommandBase {

    private OI oi;
    public LiftElevator(OI oi) {
        this.oi = oi;
        requires(elevatorLift);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        elevatorLift.liftElevator(oi);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
