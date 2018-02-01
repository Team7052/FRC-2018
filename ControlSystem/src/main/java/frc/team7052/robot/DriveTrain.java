
package frc.team7052.robot;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends Subsystem {
    public DifferentialDrive drive;
    private SpeedControllerGroup leftGroup;
    private SpeedControllerGroup rightGroup;

    private DriveTrain() {

    }

    @Override
    protected void initDefaultCommand() {

    }

    public static DriveTrain instance;

    public static DriveTrain getInstance() {
        if (instance == null) instance = new DriveTrain();
        return instance;
    }
}


