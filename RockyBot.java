package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class RockyBot extends DefenderBot {
    RockyBot(HardwareMap hm, String path, Telemetry t) {
	   super(hm, path, t);
	   drivetrain = addSystem(RockyBotMecanumDrivetrain.class);
	   sensors = addSystem(RockyBotSensors.class);
	   navigation = addSystem(RockyBotNavigation.class);

    }
}
