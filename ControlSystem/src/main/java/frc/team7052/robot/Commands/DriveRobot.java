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
        Vector3D leftStick = oi.getLeftStick();
        //if prevZValue was just released, then toggle driving carefully
        if (leftStick.z == 0 && prevZValue != 0) drivingCarefully = !drivingCarefully;
        prevZValue = leftStick.z;

        //set speed of the motor
        double speed = oi.getRightBumper();
        if (oi.getLeftBumper() > 0) speed = -oi.getLeftBumper();
        //arcade drive
        //choose multiplier based on if they are currently driving slowly
        double speedMultiplier = drivingCarefully ? Constants.kSpeedSlowMultiplier : Constants.kSpeedFastMultiplier;
        double rotationMultiplier = drivingCarefully ? Constants.kRotationSlowMultiplier : Constants.kRotationFastMultiplier;
        driveTrain.driveArcade(speed * speedMultiplier,leftStick.x * rotationMultiplier);
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
        driveTrain.driveArcade(0,0);
    }
}
