package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.LinkedList;
import java.util.concurrent.Callable;

// Attempt to move and shoot autonomously using timing.

@Disabled
@Autonomous(name = "Autonomous 2", group = "Game")
public class Autonomous2OpMode extends LinearOpMode
{
    ChesterBot bot;
    boolean isFinished = false;

    @Override
    public void runOpMode()
    {
	   bot = new ChesterBot(hardwareMap, ChesterBotConfiguration.class, telemetry);


	   waitForStart();
	   while (opModeIsActive() && !isFinished) {
		  bot.drive(1, 0, 0);
		  sleep(2000);
		  bot.drivetrain.stopDriving();
		  bot.drive(0,0.5, 0);
		  sleep(800);
		  bot.shooter.startShooter(0.75);

		  bot.navigation.comeToHeading(180);
		  for (int i = 0; i < 8; i++) {
			 bot.shooter.shoot();
			 sleep(1800);
		  }

		  bot.shooter.stopShooter();
		  sleep(2000);
		  bot.drive(-1,0,0);
		  sleep(1000);
		  bot.stopDriving();
		  isFinished = true;
	   }


    }
}
