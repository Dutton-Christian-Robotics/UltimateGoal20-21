package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.LinkedList;
import java.util.concurrent.Callable;

@TeleOp(name = "Driving", group = "Game")
public class DrivingOpMode extends LinearOpMode
{
    ChesterBot bot;
    Gamepad manipulatorGamepad;
    boolean isShooting = false;
    LinkedList<Double> shooterPowerLevels;
    private DefenderDebouncer decreaseShooterScaleFactorDebouncer;
    private DefenderDebouncer increaseShooterScaleFactorDebouncer;
    private DefenderDebouncer leftBumperDebouncer;
//	private DefenderDebouncer intakeCaptureDebouncer;
//	private DefenderDebouncer intakeStopDebouncer;

    @Override
    public void runOpMode()
    {
	   bot = new ChesterBot(hardwareMap, ChesterBotConfiguration.class, telemetry);
	   manipulatorGamepad = gamepad1;
	   shooterPowerLevels = new LinkedList<>();
	   for (double d : new double[]{1, 0.75, 0.65, 0.55}) {
		  shooterPowerLevels.add(d);
	   }

//		shooterPowerLevels.add(1.0);
//		shooterPowerLevels.add(0.75);
//		shooterPowerLevels.add(0.65);
//		shooterPowerLevels.add(0.55);

	   decreaseShooterScaleFactorDebouncer = new DefenderDebouncer(400, new Callable<Void>() {
		  @Override
		  public Void call() {
			 double oldPower = shooterPowerLevels.pollFirst();
			 shooterPowerLevels.add(oldPower);
			 if (bot.shooter.isShooting()) {
				bot.shooter.setShooterPowerByRatio(shooterPowerLevels.getFirst());
			 }
			 return null;
		  }
	   });
	   increaseShooterScaleFactorDebouncer = new DefenderDebouncer(400, new Callable<Void>() {
		  @Override
		  public Void call() {
			 double newPower = shooterPowerLevels.pollLast();
			 shooterPowerLevels.offerFirst(newPower);
			 if (bot.shooter.isShooting()) {
				bot.shooter.setShooterPowerByRatio(newPower);
			 }
			 return null;
		  }
	   });
	   leftBumperDebouncer = new DefenderDebouncer(400, new Callable<Void>() {
		  @Override
		  public Void call() {
			 if (isShooting) {
				bot.shooter.stopShooter();
				isShooting = false;
			 } else {
				bot.shooter.startShooter(shooterPowerLevels.getFirst());
				isShooting = true;
			 }
			 return null;
		  }
	   });

//		intakeCaptureDebouncer = new DefenderDebouncer(400, new Callable<Void>() {
//			@Override
//			public Void call() {
//				if (isShooting) {
//					bot.shooter.stopShooter();
//					isShooting = false;
//				} else {
//					bot.shooter.startShooter(shooterPowerLevels.getFirst());
//					isShooting = true;
//				}
//				return null;
//			}
//		});

	   waitForStart();

	   while (opModeIsActive())
	   {
		  bot.drivetrain.drive(-1 * gamepad1.left_stick_y, (gamepad1.right_trigger - gamepad1.left_trigger), gamepad1.left_stick_x);



		  if (manipulatorGamepad.left_bumper) {
			 try {
				leftBumperDebouncer.run();
			 } catch (Exception e) {

			 }

			 // Seems to not actually shoot on the first press.
			 // Should we spin-up a separate runnable/thread?
		  } else if (manipulatorGamepad.right_bumper) {
			 bot.shooter.shoot(shooterPowerLevels.getFirst());
			 isShooting = true;
		  }

		  try {
			 if (manipulatorGamepad.dpad_down) {
				decreaseShooterScaleFactorDebouncer.run();

			 } else if (manipulatorGamepad.dpad_up) {
				increaseShooterScaleFactorDebouncer.run();
			 }
		  } catch (Exception e) {
			 telemetry.addData("Error",e);
			 telemetry.update();
			 sleep(5000);
		  }

		  if (manipulatorGamepad.dpad_right) {
			 bot.wobbleArm.moveToGrabPosition();
		  } else if (manipulatorGamepad.dpad_left) {
			 bot.wobbleArm.moveToCarryPosition();
		  }

		  // ability to go from 1 to 2-controller mode by pressing X on the "manipulator" controller
		  if (gamepad1.x) {
			 manipulatorGamepad = gamepad1;
		  } else if (gamepad2.x) {
			 manipulatorGamepad = gamepad2;
		  }

		  if (manipulatorGamepad.a) {
			 bot.intake.capture();
		  } else if (manipulatorGamepad.b) {
			 bot.intake.stop();
		  }


		  telemetry.addData("Manipulator: ", manipulatorGamepad == gamepad1 ? "1" : "2");
		  telemetry.addData("Shooter power: ", bot.shooter.getCurrentShooterPower());
		  telemetry.addData("Shooter ratio: ", shooterPowerLevels.getFirst());
		  telemetry.addData("Shooting: ", bot.shooter.isShooting());
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