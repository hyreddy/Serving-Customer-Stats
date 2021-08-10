/**
 * @HrishikeshYeluru
 * This class extends the Node class and implements a class that captures the information that is 
 * required to identify and calculate the time a customer waited in line.
 */

public class CustomerNode extends Node{ 
    public int id;
    public int time;
    public int wait;
    CustomerNode next;
    
    // Constructor
    public CustomerNode(int id, int time) { 
        this.id = id;
        this.time = time;
        this.next = null; 
    }
    
    public String toString() {
    	return id + "--" + time;
    }
} 