package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ChesterBotShooter extends DefenderBotSystem {

    public DcMotor shooter;
    public Servo pusher;
    public double currentShooterPower;

    ChesterBotShooter(HardwareMap hm, DefenderBotConfiguration config, DefenderBot b) {
	   super(hm, config, b);

	   shooter = hm.dcMotor.get(configString("SHOOTER_MOTOR_NAME"));
	   shooter.setDirection(configMotorDirection("SHOOTER_MOTOR_DIRECTION"));

	   pusher = hm.servo.get(configString("PUSHER_SERVO_NAME"));
	   pusher.setDirection(configServoDirection("PUSHER_SERVO_DIRECTION"));
    }

    public void setShooterPower(double p) {
	   currentShooterPower = p;
	   shooter.setPower(currentShooterPower);
    }

    public void setShooterPowerByRatio(double r) {
	   setShooterPower(r * configDouble("SHOOTER_POWER_MAX"));
    }

    public double getCurrentShooterPower() {
	   return currentShooterPower;
    }

    public void startShooter(double ratio) {
	   setShooterPowerByRatio(ratio);
	   sleep("SHOOTER_SLEEP_AFTER_SPINUP");
    }

    public boolean isShooting() {
	   return shooter.getPower() != 0;
    }

    public void startShooter() {
	   startShooter(1);
    }

    public void stopShooter() {
	   setShooterPower(0);
    }

    // Note that the ratio is only used if the shooter is not already powered up.
    public void shoot(double ratio) {
	   if (!shooter.isBusy()) {
		  startShooter(ratio);
	   }
	   pushRing();
	   resetPusher();
    }

    public void shoot() {
	   shoot(1);
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
