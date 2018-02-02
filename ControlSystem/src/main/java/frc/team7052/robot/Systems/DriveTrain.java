
package frc.team7052.robot.Systems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.team7052.robot.Constants;

public class DriveTrain extends Subsystem {
    public DifferentialDrive drive;
    private SpeedControllerGroup leftGroup;
    private SpeedControllerGroup rightGroup;

    private DriveTrain() {
        leftGroup = new SpeedControllerGroup(new Spark(Constants.kFrontLeftMotor), new Spark(Constants.kBackLeftMotor));
        rightGroup = new SpeedControllerGroup(new Spark(Constants.kFrontRightMotor), new Spark(Constants.kBackRightMotor));

        drive = new DifferentialDrive(leftGroup, rightGroup);
    }

    @Override
    protected void initDefaultCommand() {

    }

    public static DriveTrain instance;

    public static DriveTrain getInstance() {
        if (instance == null) instance = new DriveTrain();
        return instance;
    }

    public void driveArcade(double speed, double rotation) {
        drive.arcadeDrive(speed, rotation);
    }
}


