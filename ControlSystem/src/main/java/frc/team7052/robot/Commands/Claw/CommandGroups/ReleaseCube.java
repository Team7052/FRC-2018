package frc.team7052.robot.Commands.Claw.CommandGroups;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team7052.robot.Commands.Claw.OpenArms;
import frc.team7052.robot.Commands.Claw.SpinClawWheelsOut;
import frc.team7052.robot.Commands.CommandBase;

public class ReleaseCube extends CommandGroup {
    Command spinClawWheelsOut = new SpinClawWheelsOut();
    Command openArms = new OpenArms();
    public ReleaseCube() {
        addSequential(spinClawWheelsOut);
        addSequential(openArms);
    }
}
