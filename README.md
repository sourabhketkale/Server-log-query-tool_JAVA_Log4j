# Command line tool to monitor logs for 1000 server each having 2 CPU's : CPU0 and CPU1

#The project contains 2 jar. 
	1: Generator.jar: Generates server logs with random values and specific format for 1000 servers over for year 2016(just for simplicity),
		12 months, 30 days, 24 hours, 60 minutes, and 60 seconds.
		
		Format: Timestamp: 2016-1-1 0:0 2.3.2.0 CPU0 28%
		
	2: Query.jar: Tool to Query the logs generated from the same above tool.
					
		Format for query: QUERY <server_id> <CPUno> <start date> <start time> <end date> <end time> <filepath\filename>
		E.g: QUERY 4.1.1.1 CPU1 2016-0-0 0:0 2016-0-0 6:43 C:\Users\Sourabh\commandlinetool\LogGenerator\generator_log4j_application.log
		
#commands to run the tools:
	1: Generator.jar:
		
		navigate to the folder of the jar-> open the command prompt
		filepath: path at which the logs are to be generated
		
		> java -jar Generator.jar <filepath>
		 eg: >Generator.jar C:\EduStuff\logs
		 
		 Output: The log file with name: generator_log4j_application.log will be generated at the given filepath as well as on console
		 
		 > java -jar Generator.jar exit
			Exits the tool
	
	2: Query.jar:
		
		navigate to the folder of the jar-> open the command prompt
		
		>java -jar Query.jar QUERY <server_id> <CPUno> <start date> <start time> <end date> <end time> <filepath\filename>
		 e.g: QUERY 4.1.1.1 CPU1 2016-0-0 0:0 2016-0-0 6:43 C:\Users\Sourabh\commandlinetool\LogGenerator\generator_log4j_application.log
		 
		 Output: logs from the query in the below format
		 (2016-1-1 11:56 3.3.2.0 CPU1 49%)
		 
		> java -jar Query.jar exit
			Exits the tool 
			
			
	
	Note: Plse make sure there are no space while putting the file path.
	