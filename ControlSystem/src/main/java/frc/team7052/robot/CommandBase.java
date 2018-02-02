package frc.team7052.robot;

import edu.wpi.first.wpilibj.command.Command;
import frc.team7052.robot.Systems.DriveTrain;

public class CommandBase extends Command {
    //singleton instances of subsystems
    public static DriveTrain driveTrain;

    public static void init() {
        driveTrain = DriveTrain.getInstance();
    }
    @Override
    protected boolean isFinished() {
        return false;
    }
}