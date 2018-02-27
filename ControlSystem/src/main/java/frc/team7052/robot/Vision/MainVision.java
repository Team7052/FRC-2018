package frc.team7052.robot.Vision;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import frc.team7052.robot.Constants;
import org.opencv.core.Mat;

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

    }

    public void trackVisionTarget() {
        //reflective tape

    }
}
