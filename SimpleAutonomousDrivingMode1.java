package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Simple Autonomous Driving 1", group = "LearningJava")
public class SimpleAutonomousDrivingMode extends LinearOpMode {

    DcMotor m1;

    @Override
    public void runOpMode() {
	   m1 = hardwareMap.dcMotor.get("back_left_motor");

	   waitForStart();
	   while (opModeIsActive()) {
		  m1.setPower(1);
	   }
    }
}