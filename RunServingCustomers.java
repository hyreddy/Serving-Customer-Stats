/**
 * @HrishikeshYeluru
 * This program takes in two txt files and outputs a txt file with the answers to the inputed queries.
 * The queries are related to how the time an employee takes to serve the inputed customers and the
 * time spent ideal as well as the waiting time for each customer.
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RunServingCustomers {

	public static void main(String[] args) {
		// Arguments for the inputed txt files
		String c_filename = args[0];
		String q_filename = args[1];
		
		// Initializes the queues used in the rest of the program either empty or from txt files
		Queue<CustomerNode> line = readCustomersTXT(c_filename);
		Queue<QueriesNode> queries = readQueriesTXT(q_filename);
		Queue<CustomerNode> servedCustomers = new Queue<>();
		
		// Initializes the variables used in the calculations
		int time = 32400;
		int idle_time = 0;
	    int max_break = 0;
	    int customers_served = 0;
	    int max_in_queue = 0;
	    int in_queue = 0;
	    int previous_time = 0;
		String printOut = "";
	    
	    /*
	     *  This while loop calculates the waiting time for each customer, the customers served, the
	     *  max customers in the queue, and the idle time spent by the employee
	     */
		while(line.size > 0 && time <= 61200) {
			if(time >= line.front.time) {
				line.front.wait = time - line.front.time;
				previous_time = line.front.time + line.front.wait;
				time = time + line.servRate;
				customers_served++;
				if(line.front.time + line.front.wait > previous_time){
					in_queue = 0;
				}
				in_queue++;
				if(in_queue > max_in_queue) {
					max_in_queue = in_queue;
				}
				servedCustomers.enqueue(line.dequeue());
			}
			else if(time < line.front.time) {
				int breaktime = line.front.time - time;
				if(breaktime > max_break) {
					max_break = breaktime;
				}
				line.front.wait = 0;
				idle_time = idle_time + breaktime;
				time = time + breaktime;
				time = time + line.servRate;
				customers_served++;
				in_queue = 0;
				servedCustomers.enqueue(line.dequeue());
			}
		}
		
		// This if statement ensures that the time stops at 5 and accounts for an breaktime the employee gets
		if(time < 61200) {
			int breaktime = 61200 - time;
			if(breaktime > max_break) {
				max_break = breaktime;
			}
			idle_time = idle_time + breaktime;
			time = 61200;
		}
		
		/*
		 * This while loop empties the line queue and adds the customers who did not get served but 
		 * still waited until 5 into the servedCustomers queue
		 */
		while(line.size > 0 && line.front.time < 61200) {
			line.front.wait = 61200 - line.front.time;
			in_queue++;
			if(in_queue > max_in_queue) {
				max_in_queue = in_queue;
			}
			servedCustomers.enqueue(line.dequeue());
		}
		
		/*
		 * This while loop takes the queries and prints the answer to the console and the output.txt
		 * file.
		 */
		while(queries.size > 0) {
			if(queries.front.customersServed == 1) {
				System.out.println(queries.front.name + ": " + customers_served);
				printOut = printOut + "\n" + queries.front.name + ": " + customers_served;
				queries.dequeue();
			}
			else if(queries.front.maxBreak == 1) {
				System.out.println(queries.front.name + ": " + max_break);
				printOut = printOut + "\n" + queries.front.name + ": " + max_break;
				queries.dequeue();
			}
			else if(queries.front.idleTime == 1) {
				System.out.println(queries.front.name + ": " + idle_time);
				printOut = printOut + "\n" + queries.front.name + ": " + idle_time;
				queries.dequeue();
			}
			else if(queries.front.maxNumberQueue == 1) {
				System.out.println(queries.front.name + ": " + max_in_queue);
				printOut = printOut + "\n" + queries.front.name + ": " + max_in_queue;
				queries.dequeue();
			}
			else if(queries.front.id != -1) {
				String waiting_time = servedCustomers.search(servedCustomers, queries.front.id);
				System.out.println(queries.front.name + ": " + waiting_time);
				printOut = printOut + "\n" + queries.front.name + ": " + waiting_time;
				queries.dequeue();
				
			}
		}
		writeOutputTXT(printOut.trim());
	}
	
	// This method take a filename and outputs the resulting customer information in a queue
	public static Queue<CustomerNode> readCustomersTXT(String fileName) {
		// Initializes the return variable
		Queue<CustomerNode> line = new Queue<>();
		try {
			// Initializes the variables used in the method
	    	int id = 0;
	    	int hr = 0;
	    	int min = 0;
	    	int sec = 0;
	    	
	    	Scanner sc = new Scanner(new File(fileName));
	    	
	    	String str = sc.nextLine();
	    	line.servRate = Integer.parseInt(str);
	    	
	    	/*
	    	 *  This while loop reads each line in the txt file and determines if the line contains
	    	 *  information about the customer id or arrival time and deals with it accordingly
	    	 */
	    	while (sc.hasNext()) {
	    		str = sc.nextLine();
	    		
	    		if(str.isBlank()) {
	    			continue;
	    		}
	    		
	    		else if(str.charAt(0) == 'I') {
	    			str = str.substring(10);
	    			id = Integer.parseInt(str.trim());
	    		}
	    		
	    		else if(str.charAt(0) == 'A') {
	    			str = str.substring(13);
	    			str = str.trim();
	    			hr = Integer.parseInt(str.substring(0, str.indexOf(':')));
	    			if(hr >= 7) {
	    				hr = hr * 60 * 60;
	    			}
	    			else if (hr >= 1 && hr < 7) {
	    				hr = (hr + 12) * 60 * 60;
	    			}
	    			min = Integer.parseInt(str.substring(str.indexOf(':') + 1, str.lastIndexOf(':')));
	    			min = min * 60;
	    			sec = Integer.parseInt(str.substring(str.lastIndexOf(':') + 1));
	    			sec = sec + hr + min;
		    		CustomerNode customer = new CustomerNode(id, sec);
	    			line.enqueue(customer);
	    		}
	      }
	      sc.close();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
		return line;
	  }
	
	// This method take a filename and outputs the resulting query information in a queue
	public static Queue<QueriesNode> readQueriesTXT(String fileName) {
		// Initializes the variables used in the method
		Queue<QueriesNode> queries = new Queue<>();
		try {
	    	Scanner sc = new Scanner(new File(fileName));
	    	
	    	String str;
	    	
	    	/*
	    	 *  This while loop reads each line in the txt file and determines what query each line is
	    	 *  asking for and creates a QueriesNode that corresponds to each line that is then stored
	    	 *  in a queue based on the order it is read from the file.
	    	 */
	    	while (sc.hasNext()) {
	    		// Initializes the variables used in the while loop
	    		int id = -1;
			    int customersServed = 0;
				int idleTime = 0;
			    int maxBreak = 0;
			    int maxNumberQueue = 0;
			    
	    		str = sc.nextLine();
	    		
	    		if(str.isBlank()) {
	    			continue;
	    		}
	    		else if(str.charAt(0) == 'N') {
	    			customersServed++;
	    		}
	    		else if(str.charAt(0) == 'L') {
	    			maxBreak++;
	    		}
	    		else if(str.charAt(0) == 'T') {
	    			idleTime++;
	    		}
	    		else if(str.charAt(0) == 'M') {
	    			maxNumberQueue++;
	    		}
	    		else if(str.charAt(0) == 'W') {
	    			id = Integer.parseInt(str.substring(15).trim());
	    		}
		    	QueriesNode query = new QueriesNode(str, id, customersServed, idleTime, maxBreak, maxNumberQueue);
		    	queries.enqueue(query);
	    	}
	    	
	      sc.close();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
		return queries;
	  }
	
	// This method takes a string and writes it to the output.txt file
	public static void writeOutputTXT(String str) {
		try {
			FileWriter myWriter = new FileWriter("output.txt");
			myWriter.write(str);
			myWriter.close();
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
				}
	}
}
