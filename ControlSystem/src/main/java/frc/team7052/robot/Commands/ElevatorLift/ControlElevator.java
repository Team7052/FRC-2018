package frc.team7052.robot.Commands.ElevatorLift;


import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.team7052.robot.Commands.CommandBase;
import frc.team7052.robot.Constants;
import frc.team7052.robot.Enums.Direction;
import frc.team7052.robot.Systems.OI;

public class ControlElevator extends CommandBase {
    private OI oi;
    CommandGroup commandGroup;
    int initElevatorStage = 0;
    boolean leftTriggered = false;
    boolean rightTriggered = false;
    public ControlElevator(OI oi) {
        this.oi = oi;
        requires(elevatorLift);
        commandGroup = new CommandGroup();
    }

    @Override
    protected void initialize() {
        elevatorLift.startTime();
        initElevatorStage = elevatorLift.currentSwitch;
    }

    @Override
    protected void execute() {
        //elevatorLift.liftElevator(oi);
        elevatorLift.elevatorManual(oi);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
