package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;

import java.lang.reflect.Field;

public class ChesterBotConfiguration extends DefenderBotConfiguration {

    public double DRIVETRAIN_POWER_MAX;

    public String SHOOTER_MOTOR_NAME;
    public String PUSHER_SERVO_NAME;
    public DcMotorSimple.Direction SHOOTER_MOTOR_DIRECTION;
    public double SHOOTER_POWER_MAX;
    public double[] SHOOTER_POWER_LEVELS;
    public Servo.Direction PUSHER_SERVO_DIRECTION;
    public double PUSHER_POSITION_REST;
    public double PUSHER_POSITION_SHOOT;
    public long SHOOTER_SLEEP_AFTER_SPINUP;
    public long SHOOTER_SLEEP_AFTER_PUSH;
    public long SHOOTER_SLEEP_AFTER_RESET;

    public String WOBBLEARM_MOTOR_NAME;
    public String WOBBLEHAND_SERVO_NAME;
    public Servo.Direction WOBBLEHAND_SERVO_DIRECTION;
    public int WOBBLEARM_POSITION_GRAB;
    public double WOBBLEARM_POWER_GRAB;
    public DcMotorSimple.Direction WOBBLEARM_MOTOR_DIRECTION;
    public int WOBBLEARM_POSITION_CARRY;
    public double WOBBLEARM_POWER_CARRY;
    public double WOBBLEHAND_POSITION_GRAB;
    public double WOBBLEHAND_POSITION_CARRY;


    public String RAMP_MOTOR_NAME;
    public DcMotorSimple.Direction RAMP_MOTOR_DIRECTION;
    public double RAMP_MOTOR_POSITION_DOWN;
    public double RAMP_MOTOR_POSITION_UP;
    public String INTAKE_MOTOR_NAME;
    public DcMotorSimple.Direction INTAKE_MOTOR_DIRECTION;
    public double INTAKE_MOTOR_POWER_MAX;

    public String IMU_SENSOR_NAME;
    public AxesOrder IMU_AXES_ORDER;

    public double NAVIGATION_POWER_DEFAULT;
    public long NAVIGATION_TIMEOUT_DEFAULT;
    public double NAVIGATION_TOLERANCE_ROTATION;
    public double NAVIGATION_TOLERANCE_X;
    public double NAVIGATION_TOLERANCE_Y;
    public double NAVIGATION_GEAR_RATIO;
    public long NAVIGATION_TICKS_PER_ROTATION;
    public double NAVIGATION_WHEEL_RADIUS;
    public double NAVIGATION_INCHES_PER_TICK;

    public String VISION_TFOD_MODEL_ASSET;
    public String VISION_VUFORIA_KEY;


    ChesterBotConfiguration() {
	   super();
	   DRIVETRAIN_BACKLEFT_MOTOR_NAME = "back_left";
	   DRIVETRAIN_FRONTLEFT_MOTOR_NAME = "front_left";
	   DRIVETRAIN_FRONTRIGHT_MOTOR_NAME = "front_right";
	   DRIVETRAIN_BACKRIGHT_MOTOR_NAME = "back_right";
	   DRIVETRAIN_POWER_MAX = 1.0;

	   SHOOTER_MOTOR_NAME = "shooter";
	   SHOOTER_MOTOR_DIRECTION = DcMotorSimple.Direction.REVERSE;
	   SHOOTER_POWER_MAX = 1;
	   SHOOTER_POWER_LEVELS = new double[]{1, 0.75, 0.65, 0.55};
	   SHOOTER_SLEEP_AFTER_PUSH = 450;
	   SHOOTER_SLEEP_AFTER_SPINUP = 1000;
	   SHOOTER_SLEEP_AFTER_RESET = 350;

	   PUSHER_SERVO_NAME = "pusher";
	   PUSHER_POSITION_REST = 0.6;
	   PUSHER_SERVO_DIRECTION = Servo.Direction.FORWARD;
	   PUSHER_POSITION_SHOOT = 0.1;


	   WOBBLEARM_MOTOR_NAME = "wobble_arm";
	   WOBBLEARM_POSITION_GRAB = 70;
	   WOBBLEARM_POSITION_CARRY = 0;
	   WOBBLEARM_POWER_GRAB = 1;
	   WOBBLEARM_POWER_CARRY = 0.5;
	   WOBBLEARM_MOTOR_DIRECTION = DcMotorSimple.Direction.FORWARD;

	   WOBBLEHAND_SERVO_NAME = "wobble_hand";
	   WOBBLEHAND_SERVO_DIRECTION = Servo.Direction.FORWARD;
	   WOBBLEHAND_POSITION_GRAB = 0.1;
	   WOBBLEHAND_POSITION_CARRY = 0.6;


	   RAMP_MOTOR_NAME = "ramp";
	   RAMP_MOTOR_DIRECTION = DcMotorSimple.Direction.REVERSE;
	   RAMP_MOTOR_POSITION_UP = 0;
	   RAMP_MOTOR_POSITION_DOWN = 100;

	   INTAKE_MOTOR_NAME = "intake";
	   INTAKE_MOTOR_DIRECTION = DcMotorSimple.Direction.FORWARD;
	   INTAKE_MOTOR_POWER_MAX = 1;

	   IMU_SENSOR_NAME = "imu";
	   IMU_AXES_ORDER = AxesOrder.XYZ;

	   NAVIGATION_POWER_DEFAULT = 0.42;
	   NAVIGATION_TIMEOUT_DEFAULT = 10000;
	   NAVIGATION_TOLERANCE_ROTATION = 0.3;
	   NAVIGATION_TOLERANCE_X = 1.0;
	   NAVIGATION_TOLERANCE_Y = 1.0;

	   NAVIGATION_GEAR_RATIO = 0.26; //is this right?
	   NAVIGATION_TICKS_PER_ROTATION = 280;
	   NAVIGATION_WHEEL_RADIUS = 2; // 4 inches diameter
	   NAVIGATION_INCHES_PER_TICK = (2 * Math.PI * NAVIGATION_GEAR_RATIO * NAVIGATION_WHEEL_RADIUS) / NAVIGATION_TICKS_PER_ROTATION;;

	   VISION_TFOD_MODEL_ASSET = "UltimateGoal.tflite";
	   VISION_VUFORIA_KEY = "";


	   for (Field f : ChesterBotConfiguration.class.getDeclaredFields()) {
		  fieldHashtable.put(f.getName(), f);
	   }

    }

}
