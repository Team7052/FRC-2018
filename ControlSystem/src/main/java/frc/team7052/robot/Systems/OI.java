package frc.team7052.robot.Systems;

import edu.wpi.first.wpilibj.Joystick;

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

    public double getAxis(int axis) {
        return joystick.getRawAxis(axis);
    }
    public boolean buttonPressed(int buttonNumber) {
        return joystick.getRawButton(buttonNumber);
    }
}