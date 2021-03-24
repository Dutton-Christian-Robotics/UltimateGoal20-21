package org.firstinspires.ftc.teamcode.dcs15815;

import java.util.List;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;


public class DefenderBotRingDetector extends DefenderBotSystem {
    private static final String TFOD_MODEL_ASSET = "UltimateGoal.tflite";
    private static final String LABEL_FIRST_ELEMENT = "Quad";
    private static final String LABEL_SECOND_ELEMENT = "Single";
    private static final String VUFORIA_KEY =
		  " -- YOUR NEW VUFORIA KEY GOES HERE  --- ";

    /**
	* {@link #vuforia} is the variable we will use to store our instance of the Vuforia
	* localization engine.
	*/
    private VuforiaLocalizer vuforia;

    /**
	* {@link #tfod} is the variable we will use to store our instance of the TensorFlow Object
	* Detection engine.
	*/
    private TFObjectDetector tfod;

    DefenderBotRingDetector(HardwareMap hm, DefenderBotProperties props, DefenderBot b) {
	   super(hm, props, b);
	   initVuforia();
	   initTfod();
    }

    private void initVuforia() {
	   /*
	    * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
	    */
	   VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

	   parameters.vuforiaLicenseKey = VUFORIA_KEY;
	   parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");

	   //  Instantiate the Vuforia engine
	   vuforia = ClassFactory.getInstance().createVuforia(parameters);

	   // Loading trackables is not necessary for the TensorFlow Object Detection engine.
    }

    /**
	* Initialize the TensorFlow Object Detection engine.
	*/
    private void initTfod() {
	   int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
			 "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
	   TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
	   tfodParameters.minResultConfidence = 0.8f;
	   tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
	   tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);
    }
}
