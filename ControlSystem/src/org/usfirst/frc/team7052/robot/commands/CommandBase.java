package org.usfirst.frc.team7052.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import java.util.*;

import org.usfirst.frc.team7052.robot.Constants;
import org.usfirst.frc.team7052.robot.OI;
import org.usfirst.frc.team7052.robot.subsystems.Claw;
import org.usfirst.frc.team7052.robot.subsystems.DriveTrain;
import org.usfirst.frc.team7052.robot.subsystems.ElevatorLift;


public class CommandBase extends Command {

	public static DriveTrain driveTrain;
    public static ElevatorLift elevatorLift;
    public static Claw claw;

    public static void init() {
        driveTrain = DriveTrain.getInstance();
        if (Constants.ois.size() == 1) {
            driveTrain.setOI(Constants.ois.get(0));
        }
        elevatorLift = ElevatorLift.getInstance();
        claw = Claw.getInstance();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
	
}
