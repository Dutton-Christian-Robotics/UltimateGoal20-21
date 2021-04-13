package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class ChesterBotConfiguration extends DefenderBotConfiguration {

    public String motor_shooter_name;
    public String servo_pusher_name;
    public DcMotorSimple.Direction shooter_direction;
    public double shooter_power;
    public Servo.Direction pusher_direction;
    public double pusher_rest_position;
    public double pusher_shoot_position;
    public long sleep_after_spinup;
    public long sleep_after_push;
    public long sleep_after_reset;

    public String MOTOR_WOBBLE_ARM_NAME;
    public String SERVO_WOBBLE_HAND_NAME;
    public int WOBBLE_ARM_GRAB_POSITION;
    public double WOBBLE_ARM_GRAB_POSITION_POWER;
    public DcMotorSimple.Direction WOBBLE_ARM_DIRECTION;
    public int WOBBLE_ARM_CARRY_POSITION;
    public double WOBBLE_ARM_CARRY_POSITION_POWER;

    public String servo_ramp_name;


    ChesterBotConfiguration() {
	   motor_back_left_name = "back_left";
	   motor_front_left_name = "front_left";
	   motor_front_right_name = "front_right";
	   motor_back_right_name = "back_right";

	   motor_shooter_name = "shooter";
	   shooter_direction = DcMotorSimple.Direction.REVERSE;
	   servo_pusher_name = "pusher";
	   shooter_power = 1.0;
	   pusher_rest_position = 0.1;
	   pusher_direction = Servo.Direction.REVERSE;
	   pusher_shoot_position = 0.5;
	   sleep_after_push = 650;
	   sleep_after_spinup = 1000;
	   sleep_after_reset = 350;


	   MOTOR_WOBBLE_ARM_NAME = "wobble_arm";
	   SERVO_WOBBLE_HAND_NAME = "wobble_hand";
	   WOBBLE_ARM_GRAB_POSITION = 70;
	   WOBBLE_ARM_CARRY_POSITION = 0;
	   WOBBLE_ARM_GRAB_POSITION_POWER = 1;
	   WOBBLE_ARM_CARRY_POSITION_POWER = 0.5;
	   WOBBLE_ARM_DIRECTION = DcMotorSimple.Direction.REVERSE;


	   servo_ramp_name = "ramp";
    }

}
