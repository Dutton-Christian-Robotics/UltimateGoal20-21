package org.firstinspires.ftc.teamcode.dcs15815;

import java.lang.reflect.Field;

abstract class DefenderBotConfiguration {
    public String motor_back_left_name = null;
    public String motor_front_left_name = null;
    public String motor_front_right_name = null;
    public String motor_back_right_name = null;

    public String get(String key) {
	   try {
		  Field f = DefenderBotConfiguration.class.getDeclaredField(key);
		  return (String) f.get(this);
	   } catch (Exception e) {
		  return "";
	   }

    }
}