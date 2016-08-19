package com.query.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sourabh
 * @version 1.0
 * @since 8/16/2016
 * */
public class Query {

	/**
	 * @author Sourabh
	 * @param  commandLineArguments This contains all the query parameters from the command line provided by user 
	 * @return none 
	 * Method to query and read logs in a range of date and time provided by the user
	 */
	void readLogs(String[] commandLineArguments) {

		String logLine = "";
		String[] logString = new String[5];
		String serverIp = commandLineArguments[1];
		String cpuNo = commandLineArguments[2];
		String startDate = commandLineArguments[3];
		String startTime = commandLineArguments[4];
		String endDate = commandLineArguments[5];
		String endTime = commandLineArguments[6];
		String filePath = commandLineArguments[7];

		String[] startDatearray = startDate.split("-");
		String[] startTimearray = startTime.split(":");
		String[] endDatearray = endDate.split("-");
		String[] endTimearray = endTime.split(":");

		ArrayList<String> logContainer = new ArrayList<>();
		try {
			@SuppressWarnings("resource")
			Scanner logfileScanner = new Scanner(new File(filePath));
			while (logfileScanner.hasNextLine()) {
				logLine = logfileScanner.nextLine();
				logContainer.add(logLine);
			}
			Calendar calObj = Calendar.getInstance();
			calObj.set(Calendar.YEAR, Integer.parseInt(startDatearray[0]));
			calObj.set(Calendar.MONTH, Integer.parseInt(startDatearray[1]));
			calObj.set(Calendar.DAY_OF_MONTH, Integer.parseInt(startDatearray[2]));
			calObj.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTimearray[0]));
			calObj.set(Calendar.MINUTE, Integer.parseInt(startTimearray[1]));

			Date startRange = calObj.getTime();

			Calendar calObj1 = Calendar.getInstance();
			calObj.set(Calendar.YEAR, Integer.parseInt(endDatearray[0]));
			calObj.set(Calendar.MONTH, Integer.parseInt(endDatearray[1]));
			calObj.set(Calendar.DAY_OF_MONTH, Integer.parseInt(endDatearray[2]));
			calObj.set(Calendar.HOUR_OF_DAY, Integer.parseInt(endTimearray[0]));
			calObj.set(Calendar.MINUTE, Integer.parseInt(endTimearray[1]));
			Date endRange = calObj1.getTime();

			for (int i = 0; i < logContainer.size(); i++) {
				logString = logContainer.get(i).split(" ");
				String[] dateOnCurrentlogLine = logString[1].split("-");
				String[] timeOnCurrentLogline = logString[2].split(":");

				Calendar calObj2 = Calendar.getInstance();
				calObj2.set(Calendar.YEAR, Integer.parseInt(dateOnCurrentlogLine[0]));
				calObj2.set(Calendar.MONTH, Integer.parseInt(dateOnCurrentlogLine[1]));
				calObj2.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateOnCurrentlogLine[2]));
				calObj2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeOnCurrentLogline[0]));
				calObj2.set(Calendar.MINUTE, Integer.parseInt(timeOnCurrentLogline[1]));

				Date datetimeObjOnCurrentLogLine = calObj2.getTime();

				if (datetimeObjOnCurrentLogLine.getTime() >= startRange.getTime()
						&& datetimeObjOnCurrentLogLine.getTime() <= endRange.getTime() && logString[4].equals(cpuNo)
						&& logString[3].equals(serverIp)) {
					System.out.println("(" + logString[1] + " " + logString[2] + " " + logString[3] + " " + logString[4]
							+ " " + logString[5] + ")");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String[] commandLineArguments = new String[args.length];
		if (args.length == 0) {
			System.out.println("Invalid arguments");
		} else {
			for (int i = 0; i < args.length; i++) {
				commandLineArguments[i] = args[i];
			}
		}
		Query queryObject = new Query();
		queryObject.readLogs(commandLineArguments);
	}

}
