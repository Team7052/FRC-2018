package org.usfirst.frc.team7052.robot.commands;

import org.usfirst.frc.team7052.robot.DrivingState;
import org.usfirst.frc.team7052.robot.OI;
import org.usfirst.frc.team7052.robot.OIMap;

public class DriveRobot extends CommandBase {
    OI oi;

    DrivingState drivingState = DrivingState.regular;
    public DriveRobot(OI oi) {
        this.oi = oi;
        requires(driveTrain);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        if (Math.abs(oi.getAxis(OIMap.leftBumper)) > 0.01) {
            drivingState = DrivingState.careful;
        }
        else if (Math.abs(oi.getAxis(OIMap.rightBumper)) > 0.01) {
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