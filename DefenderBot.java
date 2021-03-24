package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.lang.reflect.*;
import java.util.ArrayList;

public class DefenderBot {

    private HardwareMap hardwareMap;
    private DefenderBotProperties properties;
    private String propertiesPath;
    public DefenderBotDrivetrain drivetrain;
    public DefenderBotSystem sensors;
    public DefenderBotSystem navigation;
    private ArrayList<DefenderBotSystem> systems = new ArrayList<DefenderBotSystem>();
    protected Telemetry telemetry;


    DefenderBot(HardwareMap hm, String path, Telemetry t) {
	   hardwareMap = hm;
	   propertiesPath = path;
	   properties = new DefenderBotProperties(propertiesPath);
	   telemetry = t;

	   // In subclassess, use addSystem with the system class.
	   // This will automatically pass along hardwaremap properties, and the bot instance


    }

    // the DefenderBot, by itself, cannot do any driving. This method is intended as a "pass through"
    // to make it easier for one subsystem to request movement. If a drivetrain exists, the call
    // is passed through. This method uses the varargs approach. It can be called with any number
    // of double parameters. Those are collected in an array and passed along to the drivetrain's
    // drive method.
    public void drive(double... arr) {
	   if (drivetrain != null) {
		  drivetrain.drive(arr);
	   }
    }

    // This method is passed-through to the drivetrain, if one exists.
    public void stopDriving() {
	   if (drivetrain != null) {
		  drivetrain.stopDriving();
	   }
    }

    // This method uses generics in order to instantiate and add system classes to the bot. The
    // method works by being called with the class object for a DefenderBotSystem child class.
    // The method then uses the reflection API to find the constructor for the class that can be
    // called with a hardwaremap, a properties file, and a bot instance. It then calls that constructor
    // with the required paramers. The resulting object is explicitly cast to the desired system
    // class. This is added to the bot's array of systems, and the object returned so that it can
    // be assigned to specific instance variables.

    public <T extends DefenderBotSystem> T addSystem(Class<T> sc) {
	   try {
		  T system = sc.cast(sc.getConstructor(HardwareMap.class, DefenderBotProperties.class, DefenderBot.class).newInstance(hardwareMap, properties, this));
		  systems.add(system);
		  return system;

	   } catch (NoSuchMethodException|IllegalAccessException|InvocationTargetException|InstantiationException ex) {
		  System.exit(-1);
	   }
	   return null;
    }
}
