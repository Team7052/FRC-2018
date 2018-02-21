package frc.team7052.robot.Commands.ElevatorLift;

import edu.wpi.first.wpilibj.command.Scheduler;
import frc.team7052.robot.Commands.CommandBase;
import frc.team7052.robot.Constants;
import frc.team7052.robot.Systems.OI;

public class LiftElevator extends CommandBase {
    int initLiftStage = 0;
    private OI oi;
    public LiftElevator(OI oi) {
        this.oi = oi;
        requires(elevatorLift);
    }

    @Override
    protected void initialize() {
        elevatorLift.startTime();
        initLiftStage = elevatorLift.currentSwitch;
        setTimeout(1);
    }

    @Override
    protected void execute() {
        if (elevatorLift.lift1Stage(initLiftStage)) {
            if (elevatorLift.currentSwitch == 3) elevatorLift.currentSwitch = 4;
            end();
        }
    }

    @Override
    protected void end() {
        elevatorLift.hover();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
