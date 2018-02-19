package frc.team7052.robot.Commands.Claw.CommandGroups;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team7052.robot.Commands.Claw.CloseArms;
import frc.team7052.robot.Commands.Claw.SpinClawWheelsIn;

public class GrabCube extends CommandGroup {
    Command closeArms = new CloseArms();
    Command spinClawWheelsIn = new SpinClawWheelsIn();
    public GrabCube() {
        addSequential(spinClawWheelsIn);
        addSequential(closeArms);
    }
}
