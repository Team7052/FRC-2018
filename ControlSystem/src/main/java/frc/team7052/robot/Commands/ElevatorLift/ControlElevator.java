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
        /*if (leftTriggered) {
            boolean finished = elevatorLift.lower1Stage(initElevatorStage);
            if (finished) {
                if (elevatorLift.currentSwitch == 1) elevatorLift.currentSwitch = 0;
                elevatorLift.hover();
                leftTriggered = false;
            }
        }
        else if (rightTriggered) {
            boolean finished = elevatorLift.lift1Stage(initElevatorStage);
            if (finished) {
                if (elevatorLift.currentSwitch == 3) elevatorLift.currentSwitch = 4;
                elevatorLift.hover();
                rightTriggered = false;
            }
        }
        else {
            elevatorLift.hover();
            if (oi.buttonPressed(Constants.kButtonSecondLeftBumper)) {
                leftTriggered = true;
            }
            else if (oi.buttonPressed(Constants.kButtonSecondRightBumper)) {
                rightTriggered = true;
            }
        }*/
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
