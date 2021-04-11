package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class ChesterBotMecanumDrivetrain extends DefenderBotDrivetrain {

    public DcMotor backLeft, frontLeft, frontRight, backRight;
    private final double maxPower = 1.0;


    ChesterBotMecanumDrivetrain(HardwareMap hm, DefenderBotConfiguration config, DefenderBot b) {
	   super(hm, config, b);

	   //System.out.println("MOTOR: " + config.motor_back_left_name);
	   backLeft = hm.dcMotor.get(config.motor_back_left_name);
	   frontLeft = hm.dcMotor.get(config.motor_front_left_name);
	   frontRight = hm.dcMotor.get(config.motor_front_right_name);
	   backRight = hm.dcMotor.get(config.motor_back_right_name);
    }

    public void setPower(double bl, double fl, double fr, double br) {
	   backLeft.setPower(bl);
	   frontLeft.setPower(fl);
	   frontRight.setPower(fr);
	   backRight.setPower(br);
    }

    public void setPower(double p) {
	   setPower(p, p, p, p);
    }

    public void setMotorDirection(DcMotorSimple.Direction bl, DcMotorSimple.Direction fl,
						    DcMotorSimple.Direction fr, DcMotorSimple.Direction br) {
	   backLeft.setDirection(bl);
	   frontLeft.setDirection(fl);
	   frontRight.setDirection(fr);
	   backRight.setDirection(br);
    }

    public void setMotorDirection(DcMotorSimple.Direction left, DcMotorSimple.Direction right) {
	   setMotorDirection(left, left, right, right);
    }

    private void setProportionalPower(double bl, double fl, double fr, double br) {
	   double largest = maxPower;
	   largest = Math.max(largest, Math.abs(bl));
	   largest = Math.max(largest, Math.abs(fl));
	   largest = Math.max(largest, Math.abs(fr));
	   largest = Math.max(largest, Math.abs(br));

	   backLeft.setPower(bl / largest);
	   frontLeft.setPower(fl / largest);
	   frontRight.setPower(fr / largest);
	   backRight.setPower(br / largest);
    }

    @Override
    public void stopDriving() {
	   setPower(0);
    }

    public void drive(double arr[]) {
	   drive(arr[0], arr[1], arr[2]);
    }

    public void drive(double forward, double strafe, double rotate) {
	   setMotorDirection(DcMotorSimple.Direction.REVERSE, DcMotorSimple.Direction.REVERSE,
			 DcMotorSimple.Direction.FORWARD, DcMotorSimple.Direction.FORWARD);

	   double backLeftPower = forward - strafe + rotate;
	   double frontLeftPower = forward + strafe + rotate;
	   double frontRightPower = forward - strafe - rotate;
	   double backRightPower = forward + strafe - rotate;

	   setProportionalPower(backLeftPower, frontLeftPower, frontRightPower, backRightPower);
    }


}
