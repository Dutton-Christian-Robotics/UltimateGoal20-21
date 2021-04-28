package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp(name = "Driver Test", group = "Testing")
public class DriverTestOpMode extends LinearOpMode
{
	ChesterBot bot;
	boolean isShooting = false;

	@Override
	public void runOpMode()
	{
		bot = new ChesterBot(hardwareMap, ChesterBotConfiguration.class, telemetry);
		waitForStart();
		while (opModeIsActive())
		{
			bot.drivetrain.drive(-1 * gamepad1.left_stick_y, 0, gamepad1.left_stick_x);
			if (gamepad1.right_bumper) {
				bot.shooter.shoot();
				isShooting = true;
			}
			if (gamepad1.left_bumper) {
				if (isShooting) {
					bot.shooter.stopShooter();
					isShooting = false;
				} else {
					bot.shooter.startShooter();
					isShooting = true;
				}
			}
			
			telemetry.addData("X Value: ", gamepad1.left_stick_x);
			telemetry.addData("Y Value: ", gamepad1.left_stick_y);
			telemetry.update();

			
			// if ((gamepad1.b == true))
			// {
			// 	if (gamepad1.left_stick_x > 0)
			// 	{
			// 		// bot.turnLeft();
			// 		bot.drive(0,0,1);
			// 	} 
			// 	else if (gamepad1.left_stick_x < 0)
			// 	{
			// 		// bot.turnRight();
			// 		bot.drive(0,0,-1);
			// 	}
			// 	else
			// 	{
			// 		bot.stopDriving();
			// 	}
			// } 
			// else 
			// {
			// 	if (gamepad1.left_stick_y < 0) 
			// 	{
			// 		// bot.driveForward();
			// 		bot.drive(1,0,0);
			// 	}
			// 	else if (gamepad1.left_stick_y > 0)
			// 	{
			// 		// bot.driveBackward();
			// 		bot.drive(-1,0,0);
			// 	}
			// 	else
			// 	{
			// 		bot.stopDriving();
			// 	}
			// } 
			
		}
	}
}