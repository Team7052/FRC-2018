/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team7052.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
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

    public Joystick joystick;

    public OI(int joystickPort) {
        joystick = new Joystick(joystickPort);
    }

    public double getAxis(int axis) {
        return joystick.getRawAxis(axis);
    }
    public boolean buttonPressed(int buttonNumber) {
        return joystick.getRawButton(buttonNumber);
    }
    public int getDPad() { return joystick.getPOV(); }
}
