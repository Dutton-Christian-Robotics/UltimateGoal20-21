package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Simple Autonomous Driving 1b", group = "LearningJava")
public class SimpleAutonomousDrivingMode1b extends LinearOpMode {

    DcMotor m1;

    @Override
    public void runOpMode() {
	   m1 = hardwareMap.dcMotor.get("back_left_motor");

	   telemetry.addData("Waiting:", "Press start...");
	   telemetry.update();

	   waitForStart();
	   while (opModeIsActive()) {
		  m1.setPower(1);
		  telemetry.addData("Power:", m1.getPower());
		  telemetry.update();

	   }
    }
}
