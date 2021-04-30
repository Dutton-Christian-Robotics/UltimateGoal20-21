package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ChesterBotWobbleArm extends DefenderBotSystem {
    public DcMotor arm;
    public Servo hand;

    ChesterBotWobbleArm(HardwareMap hm, DefenderBotConfiguration config, DefenderBot b) {
        super(hm, config, b);

        arm = hm.dcMotor.get(configString("WOBBLEARM_MOTOR_NAME"));
        hand = hm.servo.get(configString("WOBBLEHAND_SERVO_NAME"));

        arm.setDirection(configMotorDirection("WOBBLEARM_MOTOR_DIRECTION"));
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }

    public void moveArmToGrabPosition() {
        arm.setTargetPosition(configInt("WOBBLEARM_POSITION_GRAB"));
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setPower(configDouble("WOBBLEARM_POWER_GRAB"));

    }

    public void moveArmToCarryPosition() {
        arm.setTargetPosition(configInt("WOBBLEARM_POSITION_CARRY"));
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setPower(configDouble("WOBBLEARM_POWER_CARRY"));

    }

    public void moveHandToGrabPosition() {
        hand.setPosition(configDouble("WOBBLEHAND_POSITION_GRAB"));
    }

    public void moveHandToCarryPosition() {
        hand.setPosition(configDouble("WOBBLEHAND_POSITION_CARRY"));
    }

    public void moveToGrabPosition() {
        moveArmToGrabPosition();
        moveHandToGrabPosition();
    }

    public void moveToCarryPosition() {
        moveArmToCarryPosition();
        moveHandToCarryPosition();
    }


}