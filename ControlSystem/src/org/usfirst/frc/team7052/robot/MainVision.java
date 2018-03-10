package org.usfirst.frc.team7052.robot;

import org.opencv.core.Mat;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class MainVision {

    public static MainVision instance;
    private static UsbCamera camera;

    public static MainVision getInstance() {
        if (instance == null) instance = new MainVision();
        return instance;
    }

    private MainVision() {
        new Thread(() -> {
            camera = CameraServer.getInstance().startAutomaticCapture();
            camera.setResolution(640, 480);

            CvSink cvSink = CameraServer.getInstance().getVideo();
            CvSource outputStream = CameraServer.getInstance().putVideo("Image", 320, 240);

            Mat source = new Mat();
            Mat output = new Mat();

            while(!Thread.interrupted()) {
                cvSink.grabFrame(source);
                outputStream.putFrame(output);
            }
        }).start();
    }

    public void setupCameraVisionTrackingMode() {
        camera.setExposureManual(1);
    }

    public void trackVisionTarget() {
        //reflective tape


    }
}