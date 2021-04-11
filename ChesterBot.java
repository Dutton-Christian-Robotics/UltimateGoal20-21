package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ChesterBot extends DefenderBot {
    ChesterBot(HardwareMap hm, Class<?> configClass, Telemetry t) {
	   super(hm, configClass, t);
	   drivetrain = addSystem(ChesterBotMecanumDrivetrain.class);
    }
}
