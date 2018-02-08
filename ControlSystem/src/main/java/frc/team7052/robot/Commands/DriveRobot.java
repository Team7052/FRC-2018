package frc.team7052.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team7052.robot.Constants;
import frc.team7052.robot.Structs.Vector3D;
import frc.team7052.robot.Systems.DriveTrain;
import frc.team7052.robot.Systems.OI;

public class DriveRobot extends Command {
    OI oi;
    DriveTrain driveTrain;
    boolean drivingCarefully = false;
    double prevZValue = 0;

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
        driveTrain.tankDrive(oi);
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
