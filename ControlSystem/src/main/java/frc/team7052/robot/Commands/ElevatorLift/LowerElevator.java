package frc.team7052.robot.Commands.ElevatorLift;

import frc.team7052.robot.Commands.CommandBase;
import frc.team7052.robot.Systems.ElevatorLift;
import frc.team7052.robot.Systems.OI;

public class LowerElevator extends CommandBase {
    OI oi;
    int initElevatorStage = 0;

    public LowerElevator(OI oi) {
        this.oi = oi;
        requires(elevatorLift);
    }

    @Override
    protected void initialize() {
        initElevatorStage = elevatorLift.currentSwitch;
        setTimeout(1.0);
    }

    @Override
    protected void execute() {
        if (elevatorLift.lower1Stage(initElevatorStage)) {
            if (elevatorLift.currentSwitch == 1) elevatorLift.currentSwitch = 0;
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
