package frc.team7052.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team7052.robot.Systems.DriveTrain;
import frc.team7052.robot.Systems.OI;
import frc.team7052.robot.Constants;

public class DriveRobot extends Command {
    OI oi;
    DriveTrain driveTrain;

    double prevZValue = 0;
    DrivingState drivingState = DrivingState.regular;
    public DriveRobot(OI oi) {
        this.oi = oi;
        driveTrain = DriveTrain.getInstance();
        requires(driveTrain);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        if (oi.buttonPressed(Constants.kButtonLeftJoystickPress)) {
            drivingState = DrivingState.turbo;
        }
        else {
            drivingState = DrivingState.regular;
        }
        driveTrain.tankDrive(oi, drivingState);

    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void interrupted() {
        end();
    }

    @Override
    protected void end() {
        //stop driveTrain
        driveTrain.stop();
    }
}
