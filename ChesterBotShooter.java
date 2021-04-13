package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ChesterBotShooter extends DefenderBotSystem {

    public DcMotor shooter;
    public Servo pusher;

    ChesterBotShooter(HardwareMap hm, DefenderBotConfiguration config, DefenderBot b) {
	   super(hm, config, b);

	   shooter = hm.dcMotor.get(cbConfig().motor_shooter_name);
	   shooter.setDirection(cbConfig().shooter_direction);

	   pusher = hm.servo.get(cbConfig().servo_pusher_name);
	   pusher.setDirection(cbConfig().pusher_direction);
    }

    public ChesterBotConfiguration cbConfig() {
	   return (ChesterBotConfiguration) configuration;
    }

    public void startShooter(double ratio) {
	   shooter.setPower(ratio * cbConfig().shooter_power);
	   sleep(cbConfig().sleep_after_spinup);
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
	   pusher.setPosition(cbConfig().pusher_rest_position);
	   sleep(cbConfig().sleep_after_reset);
    }

    public void pushRing() {
	   pusher.setPosition(cbConfig().pusher_shoot_position);
	   sleep(cbConfig().sleep_after_push);
    }
}
