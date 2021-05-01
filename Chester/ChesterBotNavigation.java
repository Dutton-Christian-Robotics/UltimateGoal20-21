package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.hardware.DcMotor;
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

public class ChesterBotNavigation extends DefenderBotSystem {

    private final GeneralMatrixF encoderMatrix = new GeneralMatrixF(3, 1);
    private int frontLeftOffset;
    private int frontRightOffset;
    private int backRightOffset;
    private int backLeftOffset;

    private MatrixF conversion;

    private DcMotor backLeft, frontLeft, frontRight, backRight;

    private ChesterBotSensors sensors;


    ChesterBotNavigation(HardwareMap hm, DefenderBotConfiguration config, DefenderBot b) {
	   super(hm, config, b);

	   float[] data = {1.0f, 1.0f, 1.0f,
			 1.0f, -1.0f, -1.0f,
			 1.0f, -1.0f, 1.0f};
	   conversion = new GeneralMatrixF(3, 3, data);
	   conversion = conversion.inverted();

	   backLeft = ((ChesterBotMecanumDrivetrain) bot.drivetrain).backLeft;
	   frontLeft = ((ChesterBotMecanumDrivetrain) bot.drivetrain).frontLeft;
	   frontRight = ((ChesterBotMecanumDrivetrain) bot.drivetrain).frontRight;
	   backRight = ((ChesterBotMecanumDrivetrain) bot.drivetrain).backRight;

	   sensors = (ChesterBotSensors)((ChesterBot)bot).sensors;
	   setEncoderOffsets();
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
		  currentAngle = sensors.getIntegratedHeading();
		  difference = currentAngle - angle;
		  absDifference = Math.abs(difference);

		  if (between(absDifference, tolerance, 2 * tolerance) && (difference < 0)) {
			 bot.drivetrain.drive(0, 0, -1 * maxPower / 8);
			 sleep(sleepLength);

		  } else if (between(absDifference, tolerance, 4 * tolerance) && (difference < 0)) {
			 bot.drivetrain.drive(0, 0, -1 * maxPower / 4);
			 sleep(sleepLength);

		  } else if ((absDifference > 4 * tolerance) && (difference < 0)) {
			 bot.drivetrain.drive(0, 0, -1 * maxPower);
			 sleep(sleepLength);

		  } else if (between(absDifference, tolerance, 2 * tolerance) && (difference > 0)) {
			 bot.drivetrain.drive(0, 0, maxPower / 8);
			 sleep(sleepLength);

		  } else if (between(absDifference, tolerance, 4 * tolerance) && (difference > 0)) {
			 bot.drivetrain.drive(0, 0, maxPower / 4);
			 sleep(sleepLength);

		  } else if ((absDifference > 4 * tolerance) && (difference > 0)) {
			 bot.drivetrain.drive(0, 0, maxPower);
			 sleep(sleepLength);
		  } else {
			 keepTurning = false;
			 bot.stopDriving();
		  }

		  if (timer.milliseconds() >= timeout) {
			 keepTurning = false;
		  }
	   } while (keepTurning);

    }

    public void comeToHeading(double angle) {
	   comeToHeading(angle, configDouble("NAVIGATION_POWER_DEFAULT"), configDouble("NAVIGATION_TOLERANCE_DEFAULT"), configLong("NAVIGATION_TIMEOUT_DEFAULT"));
    }

    double[] getDistanceInches() {
	   double[] distances = {0.0, 0.0};

	   encoderMatrix.put(0, 0, (float) ((frontLeft.getCurrentPosition() - frontLeftOffset) * configDouble("NAVIGATION_INCHES_PER_TICK")));
	   encoderMatrix.put(1, 0, (float) ((frontRight.getCurrentPosition() - frontRightOffset) * configDouble("NAVIGATION_INCHES_PER_TICK")));
	   encoderMatrix.put(2, 0, (float) ((backLeft.getCurrentPosition() - backLeftOffset) * configDouble("NAVIGATION_INCHES_PER_TICK")));

	   MatrixF distanceMatrix = conversion.multiplied(encoderMatrix);
	   distances[0] = distanceMatrix.get(0, 0);
	   distances[1] = distanceMatrix.get(1, 0);

	   return distances;
    }

    public double powerDropoff(double target, double current) {
	   return 1 - Math.pow(1 - ((target - (current - 1)) / Math.abs(target - current)), 14);
    }

    public void setEncoderOffsets() {

	   frontRightOffset = frontRight.getCurrentPosition();
	   frontLeftOffset = frontLeft.getCurrentPosition();
	   backLeftOffset = backLeft.getCurrentPosition();
	   backRightOffset = backRight.getCurrentPosition();
    }

    public void driveToPosition(double x, double y, double heading) {

	   double[] d = getDistanceInches();
	   double deltaY, deltaX, deltaH;
	   double pX, pY, pH;
	   double h = sensors.getIntegratedHeading();
	   double rotation = 0;
	   double averageError = 0;

	   // should replace these constants with config values
	   while ((Math.abs(y - d[0]) > 1.0) || (Math.abs(x - d[1]) > 1.0) || (Math.abs(heading - h) > 1.0)) {
		  deltaX = x - d[1];
		  deltaY = y - d[0];
		  deltaH = h - heading;

		  bot.telemetry.addData("x", deltaX);
		  bot.telemetry.addData("y", deltaY);
		  bot.telemetry.addData("h", deltaH);
		  bot.telemetry.update();




//            if (h > heading) {
//                rotation = 0.5;
//            } else if (h < heading) {
//                rotation = -0.5;
//            }


		  averageError = (Math.abs(deltaX) + Math.abs(deltaY) + Math.abs(deltaH)) / 3;
		  bot.telemetry.addData("Avg", averageError);
//            pX = powerDropoff(x, d[1]);
//            pY = powerDropoff(y, d[0]);
//            pH = powerDropoff(heading, h);

//            pX = powerDropoff(x, d[1]) * (deltaX / averageError);
//            pY = powerDropoff(y, d[0]) * (deltaY / averageError);
//            pH = powerDropoff(heading, h) * (deltaH / averageError);
		  pX = (deltaX / averageError);
		  pY = (deltaY / averageError);
		  pH = (deltaH / averageError);

		  bot.telemetry.addData("px", pX);
		  bot.telemetry.addData("py", pY);
		  bot.telemetry.addData("ph", pH);


		  bot.telemetry.update();
		  bot.drive(powerDropoff(y, d[0]), powerDropoff(x, d[1]), 0);
//            bot.drive(pY, pX, pH);
		  d = getDistanceInches();
		  h = sensors.getIntegratedHeading();
	   }
	   bot.stopDriving();

    }

    public void driveToPosition(double x, double y) {
	   driveToPosition(x, y, 0.0);
    }


}
