package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "Simple Autonomous Driving 2b", group = "LearningJava")
public class SimpleAutonomousDrivingMode2b extends LinearOpMode {

    DcMotor m1, m2, m3, m4;

    @Override
    public void runOpMode() {
	   m1 = hardwareMap.dcMotor.get("back_left_motor");
	   m2 = hardwareMap.dcMotor.get("front_left_motor");
	   m3 = hardwareMap.dcMotor.get("front_right_motor");
	   m4 = hardwareMap.dcMotor.get("back_right_motor");

	   m1.setDirection(DcMotorSimple.Direction.REVERSE);
	   m2.setDirection(DcMotorSimple.Direction.REVERSE);
	   m3.setDirection(DcMotorSimple.Direction.FORWARD);
	   m4.setDirection(DcMotorSimple.Direction.FORWARD);

	   telemetry.addData("Waiting:", "Press start...");
	   telemetry.update();

	   waitForStart();
	   while (opModeIsActive()) {
		  m1.setPower(1);
		  m2.setPower(1);
		  m3.setPower(1);
		  m4.setPower(1);
		  telemetry.addData("Power:", m1.getPower());
		  telemetry.update();

	   }
    }
}
