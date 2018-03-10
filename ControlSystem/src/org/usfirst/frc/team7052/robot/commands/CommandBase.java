package org.usfirst.frc.team7052.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team7052.robot.OI;
import org.usfirst.frc.team7052.robot.subsystems.Claw;
import org.usfirst.frc.team7052.robot.subsystems.DriveTrain;
import org.usfirst.frc.team7052.robot.subsystems.ElevatorLift;


public class CommandBase extends Command {

	public static DriveTrain driveTrain;
    public static ElevatorLift elevatorLift;
    public static Claw claw;

    public static void init(OI driveOI, OI liftOI) {
        driveTrain = DriveTrain.getInstance();
        driveTrain.setOI(driveOI);
        elevatorLift = ElevatorLift.getInstance();
        claw = Claw.getInstance();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
	
}
