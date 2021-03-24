package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.hardware.HardwareMap;

abstract class DefenderBotSystem {
    private HardwareMap hardwareMap;
    private DefenderBotProperties properties;
    protected DefenderBot bot;
    private boolean logging = false;

    // Systems are generally responsible for their own setup. To make that happen, this abstract
    // parent class saves references to the hardwareMap provided by the opmode, the properties
    // file that was used when creating the bot instances, and a reference to the bot itself
    // (useful for things like accessing telemetry)
    DefenderBotSystem(HardwareMap hm, DefenderBotProperties props, DefenderBot b) {
	   this.hardwareMap = hm;
	   this.properties = props;
	   this.bot = b;
    }

    public boolean isLogging() {
	   return logging;
    }

    public void setLogging(boolean logging) {
	   this.logging = logging;
    }
}
