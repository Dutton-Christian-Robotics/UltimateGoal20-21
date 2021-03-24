package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.hardware.bosch.BNO055IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.matrices.GeneralMatrixF;
import org.firstinspires.ftc.robotcore.external.matrices.MatrixF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.util.ElapsedTime;




public class RockyBotNavigation extends DefenderBotSystem {

    RockyBotNavigation(HardwareMap hm, DefenderBotProperties props, DefenderBot b) {
	   super(hm, props, b);
    }

    public boolean between(double x, double min, double max) {
	   return (x > min) && (x < max);
    }

    public void comeToHeading(double angle, double maxPower, double tolerance, double timeout) {
	   double difference, absDifference, currentAngle;
	   boolean keepTurning = true;
	   ElapsedTime timer = new ElapsedTime();
	   long sleepLength = 10;
	   //Orientation orientation;

	   do {
		  currentAngle = ((RockyBotSensors) bot.sensors).getIntegratedHeading();
		  difference = currentAngle - angle;
		  absDifference = Math.abs(difference);

		  try {
			 if (between(absDifference, tolerance, 2 * tolerance) && (difference < 0)) {
				bot.drivetrain.drive(0, 0, -1 * maxPower / 8);
				Thread.sleep(sleepLength);

			 } else if (between(absDifference, tolerance, 4 * tolerance) && (difference < 0)) {
				bot.drivetrain.drive(0, 0, -1 * maxPower / 4);
				Thread.sleep(sleepLength);

			 } else if ((absDifference > 4 * tolerance) && (difference < 0)) {
				bot.drivetrain.drive(0, 0, -1 * maxPower);
				Thread.sleep(sleepLength);

			 } else if (between(absDifference, tolerance, 2 * tolerance) && (difference > 0)) {
				bot.drivetrain.drive(0, 0, maxPower / 8);
				Thread.sleep(sleepLength);

			 } else if (between(absDifference, tolerance, 4 * tolerance) && (difference > 0)) {
				bot.drivetrain.drive(0, 0, maxPower / 4);
				Thread.sleep(sleepLength);

			 } else if ((absDifference > 4 * tolerance) && (difference > 0)) {
				bot.drivetrain.drive(0, 0, maxPower);
				Thread.sleep(sleepLength);
			 } else {
				keepTurning = false;
				bot.stopDriving();
			 }
		  } catch (InterruptedException exc) {
			 Thread.currentThread().interrupt();
		  }
		  if (timer.milliseconds() >= timeout) {
			 keepTurning = false;
		  }
	   } while (keepTurning);

    }

    public void comeToHeading(double angle) {
	   comeToHeading(angle, 0.15, 0.3, 10000);
    }

}
