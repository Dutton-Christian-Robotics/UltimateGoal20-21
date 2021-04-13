package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ChesterBotWobbleArm extends DefenderBotSystem {
    public DcMotor arm;
    public Servo hand;

    ChesterBotWobbleArm(HardwareMap hm, DefenderBotConfiguration config, DefenderBot b) {
	   super(hm, config, b);

	   arm = hm.dcMotor.get(cbConfig().MOTOR_WOBBLE_ARM_NAME);
	   hand = hm.servo.get(cbConfig().SERVO_WOBBLE_HAND_NAME);

	   arm.setDirection(cbConfig().WOBBLE_ARM_DIRECTION);
	   arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
	   arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public ChesterBotConfiguration cbConfig() {
	   return (ChesterBotConfiguration) configuration;
    }

    public void moveArmToGrabPosition() {
	   arm.setTargetPosition(cbConfig().WOBBLE_ARM_GRAB_POSITION);
	   arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
	   arm.setPower(cbConfig().WOBBLE_ARM_GRAB_POSITION_POWER);

    }

    public void moveArmToCarryPosition() {
	   arm.setTargetPosition(cbConfig().WOBBLE_ARM_CARRY_POSITION);
	   arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
	   arm.setPower(cbConfig().WOBBLE_ARM_CARRY_POSITION_POWER);
    }

    public void moveHandToGrabPosition() {

    }

    public void moveHandToCarryPosition() {

    }


}