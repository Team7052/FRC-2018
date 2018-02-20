package frc.team7052.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team7052.robot.Systems.Claw;
import frc.team7052.robot.Systems.DriveTrain;
import frc.team7052.robot.Systems.ElevatorLift;

public abstract class CommandBase extends Command {

    public static DriveTrain driveTrain;
    public static ElevatorLift elevatorLift;
    public static Claw claw;

    public static void init() {
        driveTrain = driveTrain.getInstance();
        elevatorLift = elevatorLift.getInstance();
        claw = claw.getInstance();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
