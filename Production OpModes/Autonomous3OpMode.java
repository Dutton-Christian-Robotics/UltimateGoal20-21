package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.LinkedList;
import java.util.concurrent.Callable;

// Autonomously move into shooting position and aim for all three rings
// in the top goal. Then toss the wobble goal and park.

@Autonomous(name = "Top Goal", group = "Game")
public class Autonomous3OpMode extends LinearOpMode
{
    ChesterBot bot;
    boolean isFinished = false;

    @Override
    public void runOpMode()
    {
	   bot = new ChesterBot(hardwareMap, ChesterBotConfiguration.class, telemetry);


	   waitForStart();
	   while (opModeIsActive() && !isFinished) {
		  bot.navigation.driveToPosition(-12, 0);
		  bot.navigation.driveToPosition(0, 62);
		  bot.shooter.startShooter(0.75);
		  bot.navigation.driveToPosition(17, 62);
		  bot.navigation.comeToHeading(180);
		  for (int i = 0; i < 6; i++) {
			 bot.shooter.shoot();
			 sleep(300);
		  }
		  bot.wobbleArm.moveToGrabPosition();
		  bot.shooter.stopShooter();
		  bot.navigation.comeToHeading(0);

		  bot.wobbleArm.moveToCarryPosition();
		  sleep(1000);
		  bot.navigation.driveToPosition(17, 76);

		  isFinished = true;
	   }


    }
}
