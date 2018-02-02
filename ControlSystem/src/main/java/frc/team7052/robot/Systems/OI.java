package frc.team7052.robot.Systems;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.team7052.robot.Constants;
import frc.team7052.robot.Structs.Vector3D;


public class OI {
    /*
     * Joystick Notes from Kevin Bai
     * left stick = getX(), getY(), getZ(); values between -1 to 1
     * right stick = TODO
     * d-pad = TODO
     * left bumper = getThrottle(); values between 0 and 1
     * left bumper 2 = TODO
     * right bumper = getTwist(); values between 0 and 1
     * right bumper 2 = TODO
     * mode = TODO
     * select = TODO
     * */

    public static int defaultPort = 0;

    public Joystick joystick;

    public OI() {
        joystick = new Joystick(defaultPort);
    }

    public Vector3D getLeftStick() {
        if (joystick != null) new Vector3D(joystick.getX(), joystick.getY(), joystick.getZ());
        return new Vector3D(0,0,0);
    }
    public double getRightBumper() {
        if (joystick != null) return joystick.getTwist();
        return 0;
    }
    public double getLeftBumper() {
        if (joystick != null) return joystick.getThrottle();
        return 0;
    }

}