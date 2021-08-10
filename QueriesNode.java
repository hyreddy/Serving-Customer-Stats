/**
 * @HrishikeshYeluru
 * This class extends the Node class and implements a class that captures the information that is 
 * required to identify each query and the next one in the list.
 */

public class QueriesNode extends Node {
    String name;
    int id;
    int customersServed;
	int idleTime;
    int maxBreak;
    int maxNumberQueue;
    QueriesNode next; 
  
    // Constructor to create a new linked list node 
    public QueriesNode(String name, int id, int customersServed, int idleTime, int maxBreak, int maxNumberQueue) {
    	this.name = name;
    	this.id = id;
        this.customersServed = customersServed;
        this.idleTime = idleTime;
    	this.maxBreak = maxBreak;
        this.maxNumberQueue = maxNumberQueue;
        this.next = null; 
    }
    
    public String toString() {
    	return name + ": " + id + " / " + customersServed + " / " + idleTime + " / " + maxBreak + " / " + maxNumberQueue;
    }
}
