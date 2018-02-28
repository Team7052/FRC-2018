package frc.team7052.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team7052.robot.Systems.Claw;
import frc.team7052.robot.Systems.DriveTrain;
import frc.team7052.robot.Systems.ElevatorLift;
import frc.team7052.robot.Systems.OI;

public abstract class CommandBase extends Command {

    public static DriveTrain driveTrain;
    public static ElevatorLift elevatorLift;
    public static Claw claw;

    public static void init(OI oi) {
        driveTrain = driveTrain.getInstance();
        driveTrain.setOI(oi);
        elevatorLift = elevatorLift.getInstance();
        claw = claw.getInstance();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
