package com.generator.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author Sourabh
 * @version 1.0
 * @since
 * */
public class Generator {

	static Logger loggerObj = Logger.getLogger(Generator.class);

	/**
	 * @author Sourabh
	 * @param Nothing.
	 * @return Nothing. Method to generate Log for 1000 servers with CUP0 and CPU1
	 *         Note- For the smallness of log size and time to generate, only year:2016 is considered in the program.
	 */
	void logGenerator(String filePath) {
		String ipCache = "";

		for (int month = 1; month < 13; month++) {
			for (int day = 1; day < 32; day++) {
				for (int hour = 0; hour <= 23; hour++) {
					for (int minute = 0; minute <= 59; minute++) {
						for (int seconds = 0; seconds < 100; seconds++) {
							ipCache = Generator.generateRandomIP();
							loggerObj.info("Timestamp: 2016-" + month + "-" + day + " " + hour + ":" + minute + " "
									+ ipCache + " CPU0" + " " + +Generator.generateRandomCpuTime() + "%");
							loggerObj.info("Timestamp: 2016-" + month + "-" + day + " " + hour + ":" + minute + " "
									+ ipCache + " CPU1" + " " + +Generator.generateRandomCpuTime() + "%");
						}
					}
				}
			}
		}

	}

	/**
	 * @author Sourabh
	 * @param  Nothing.
	 * @return returns a string of randomly generated server-IP Method to
	 *         generate random IP addresses.
	 */
	static String generateRandomIP() {
		Random r = new Random();
		return r.nextInt(5) + "." + r.nextInt(4) + "." + r.nextInt(3) + "." + r.nextInt(2);
	}

	/**
	 * @author Sourabh
	 * @param Nothing.
	 * @return returns an integer value of randomly generated Cpu time Method to
	 *         generate random CPU time ranging values from 1 to 100
	 */
	static int generateRandomCpuTime() {

		int min = 0;
		int max = 100;
		Random ran = new Random();
		return ran.nextInt((max - min) + 1) + min;
	}

	/**
	 * @author Sourabh
	 * @param logFile This is the file path on which the user wants the log to be generated
	 * @return Nothing. method to update Log4j properties for dynamic file location
	 *
	 */
	private void updateLog4jConfiguration(String logFile) {
		Properties props = new Properties();
		try {
			InputStream configStream = getClass().getResourceAsStream("/log4j.properties");
			props.load(configStream);
			configStream.close();
		} catch (IOException e) {
			System.out.println("Errornot laod configuration file ");
		}
		props.setProperty("log4j.appender.FILE.file", logFile);
		LogManager.resetConfiguration();
		PropertyConfigurator.configure(props);

	}

	public static void main(String[] args) {

		String filePath = "";
		if (args.length == 0) {
			System.out.println("Invalid arguments");
		} else {
			for (String a : args) {
				filePath = filePath + a;
			}
		}
		System.setProperty("log", filePath);
		Generator genObject = new Generator();
		genObject.updateLog4jConfiguration(filePath);
		genObject.logGenerator(filePath);
	}

}
