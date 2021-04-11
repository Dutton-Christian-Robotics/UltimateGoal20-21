package org.firstinspires.ftc.teamcode.dcs15815;
import java.io.*;
import java.util.*;

import static java.lang.System.exit;


public class DefenderBotProperties extends Properties {

    public String filename;

    DefenderBotProperties(String fn) {
	   filename = fn;
	   try {
		  File propertiesFile = new File(filename);
		  InputStream propertiesStream = new FileInputStream(propertiesFile);
		  loadFromXML(propertiesStream);
		  System.out.println("Loaded properties file");
	   } catch (FileNotFoundException ex) {
		  System.out.println("Properties file not found");
		  //System.exit(-1);
	   } catch (IOException ex) {
		  System.out.println("IOException while loading properties file");
		  //System.exit(-1);

	   }
    }

    public String get(String property) {
	   return getProperty(property);
    }

    public String getMotorName(String which) {
	   return get(String.format("motor.%s.name", which));
    }
}
