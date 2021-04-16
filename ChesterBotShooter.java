package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ChesterBotShooter extends DefenderBotSystem {

    public DcMotor shooter;
    public Servo pusher;

    ChesterBotShooter(HardwareMap hm, DefenderBotConfiguration config, DefenderBot b) {
	   super(hm, config, b);

	   shooter = hm.dcMotor.get(configString("SHOOTER_MOTOR_NAME"));
	   shooter.setDirection(configMotorDirection("SHOOTER_MOTOR_DIRECTION"));

	   pusher = hm.servo.get(configString("PUSHER_SERVO_NAME"));
	   pusher.setDirection(configServoDirection("PUSHER_SERVO_DIRECTION"));
    }

    public void startShooter(double ratio) {
	   shooter.setPower(ratio * configDouble("SHOOTER_POWER_MAX"));
	   sleep("SHOOTER_SLEEP_AFTER_SPINUP");
    }

    public void startShooter() {
	   startShooter(1);
    }

    public void stopShooter() {
	   shooter.setPower(0);
    }

    public void shoot() {
	   if (!shooter.isBusy()) {
		  startShooter();
	   }
	   pushRing();
	   resetPusher();
    }

    public void resetPusher() {
	   pusher.setPosition(configDouble("PUSHER_POSITION_REST"));
	   sleep("SHOOTER_SLEEP_AFTER_RESET");
    }

    public void pushRing() {
	   pusher.setPosition(configDouble("PUSHER_POSITION_SHOOT"));
	   sleep("SHOOTER_SLEEP_AFTER_PUSH");
    }
}
