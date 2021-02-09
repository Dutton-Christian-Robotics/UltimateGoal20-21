package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "SimpleTeleOp 1", group = "LearningJava")
public class SimpleTeleOp1 extends LinearOpMode {

    DcMotor m1, m2, m3, m4;

    public void setPower(double bl, double fl, double fr, double br) {
	   m1.setPower(bl);
	   m2.setPower(fl);
	   m3.setPower(fr);
	   m4.setPower(br);

    }

    public void driveForward() {
	   m1.setDirection(DcMotorSimple.Direction.REVERSE);
	   m2.setDirection(DcMotorSimple.Direction.REVERSE);
	   m3.setDirection(DcMotorSimple.Direction.FORWARD);
	   m4.setDirection(DcMotorSimple.Direction.FORWARD);

	   setPower(1, 1, 1,1);
    }

    public void driveBackward() {
	   m1.setDirection(DcMotorSimple.Direction.FORWARD);
	   m2.setDirection(DcMotorSimple.Direction.FORWARD);
	   m3.setDirection(DcMotorSimple.Direction.REVERSE);
	   m4.setDirection(DcMotorSimple.Direction.REVERSE);

	   setPower(1, 1, 1,1);
    }


    public void driveRight() {
	   m1.setDirection(DcMotorSimple.Direction.FORWARD);
	   m2.setDirection(DcMotorSimple.Direction.REVERSE);
	   m3.setDirection(DcMotorSimple.Direction.REVERSE);
	   m4.setDirection(DcMotorSimple.Direction.FORWARD);

	   setPower(1, 1, 1,1);
    }

    public void driveLeft() {
	   m1.setDirection(DcMotorSimple.Direction.REVERSE);
	   m2.setDirection(DcMotorSimple.Direction.FORWARD);
	   m3.setDirection(DcMotorSimple.Direction.FORWARD);
	   m4.setDirection(DcMotorSimple.Direction.REVERSE);

	   setPower(1, 1, 1,1);
    }

    public void stopDriving() {
	   setPower(0, 0, 0, 0);
    }



    @Override
    public void runOpMode() {
	   m1 = hardwareMap.dcMotor.get("back_left_motor");
	   m2 = hardwareMap.dcMotor.get("front_left_motor");
	   m3 = hardwareMap.dcMotor.get("front_right_motor");
	   m4 = hardwareMap.dcMotor.get("back_right_motor");

	   waitForStart();

	   while (opModeIsActive()) {
		  if (gamepad1.left_stick_y < 0) {
			 driveForward();
		  } else if (gamepad1.left_stick_y > 0) {
			 driveBackward();
		  } else {
			 stopDriving();
		  }

	   }

    }
}