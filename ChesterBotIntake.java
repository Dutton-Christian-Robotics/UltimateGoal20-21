package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ChesterBotIntake extends DefenderBotSystem {

    public DcMotor intakeMotor;
    public DcMotor rampMotor;

    ChesterBotIntake(HardwareMap hm, DefenderBotConfiguration config, DefenderBot b) {
        super(hm, config, b);

        intakeMotor = hm.dcMotor.get(configString("INTAKE_MOTOR_NAME"));
        intakeMotor.setDirection(configMotorDirection("INTAKE_MOTOR_DIRECTION"));

        rampMotor = hm.dcMotor.get(configString("RAMP_MOTOR_NAME"));
        rampMotor.setDirection(configMotorDirection("RAMP_MOTOR_DIRECTION"));
    }

    public void capture(double ratio) {
        intakeMotor.setPower(ratio * configDouble("INTAKE_MOTOR_POWER_MAX"));
    }

    public void capture() {
        capture(1);
    }

    public void stop() {
        intakeMotor.setPower(0);
    }

    public void release(double ratio) {
        intakeMotor.setPower(-1 * ratio * configDouble("INTAKE_MOTOR_POWER_MAX"));
    }

    public void release() {
        release(1);
    }

    public void lowerRamp() {
//        rampMotor.setPosition(configDouble("RAMP_SERVO_POSITION_DOWN"));
    }

}
