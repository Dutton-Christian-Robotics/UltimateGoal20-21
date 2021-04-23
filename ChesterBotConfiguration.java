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


    public String RAMP_SERVO_NAME;
    public Servo.Direction RAMP_SERVO_DIRECTION;
    public double RAMP_SERVO_POSITION_DOWN;
    public String INTAKE_MOTOR_NAME;
    public DcMotorSimple.Direction INTAKE_MOTOR_DIRECTION;
    public double INTAKE_MOTOR_POWER_MAX;

    public String IMU_SENSOR_NAME;
    public AxesOrder IMU_AXES_ORDER;


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
	   SHOOTER_SLEEP_AFTER_PUSH = 650;
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


	   RAMP_SERVO_NAME = "ramp";
	   RAMP_SERVO_DIRECTION = Servo.Direction.REVERSE;
	   RAMP_SERVO_POSITION_DOWN = 0;

	   INTAKE_MOTOR_NAME = "ramp";
	   INTAKE_MOTOR_DIRECTION = DcMotorSimple.Direction.REVERSE;
	   INTAKE_MOTOR_POWER_MAX = 1.0;

	   IMU_SENSOR_NAME = "imu";
	   IMU_AXES_ORDER = AxesOrder.ZYX;

	   for (Field f : ChesterBotConfiguration.class.getDeclaredFields()) {
		  fieldHashtable.put(f.getName(), f);
	   }

    }

}
