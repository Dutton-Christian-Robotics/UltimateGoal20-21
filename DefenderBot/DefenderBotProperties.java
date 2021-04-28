package org.firstinspires.ftc.teamcode.dcs15815;
import java.io.*;
import java.util.*;

import static java.lang.System.exit;

// This class is not actually used. The original plan was to use Java's
// built-in property functionality, but I couldn't get it to properly find
// the properties file on the bot. I think it comes from not knowing what
// the active directory is when an opmode runs.
//
// So, I reused the class-based configuration used during Skystone,
// with modifications for ease of use.

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
