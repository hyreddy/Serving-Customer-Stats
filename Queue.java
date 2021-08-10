/**
 * @HrishikeshYeluru
 * This class creates a generic queue of any type that extends the Node class. Its method allows the
 * nodes to be enqueued and dequeued as well as searched to find the node with a specific id.
 */

class Queue<T extends Node> { 
    T front;
    T rear;
    int size = 0;
    int servRate;
    
    // Constructor
    public Queue() { 
        this.front = this.rear = null; 
    }
    
    // This method adds an object to the end of the queue
    public void enqueue(T temp) {
        if (this.rear == null) { 
            this.front = this.rear = temp; 
            size++;
            return; 
        } 
  
        this.rear.next = temp; 
        this.rear = temp;
        
        size++;
    } 
    
    // This method removes an object from the front of the queue
    @SuppressWarnings("unchecked")
	public T dequeue() 
    { 
        if (this.front == null) 
            return null; 
  
        T temp = this.front; 
        this.front = (T) this.front.next; 
  
        if (this.front == null) 
            this.rear = null; 
        
        size--;
        return temp;
    } 
    
    public boolean isEmpty() {
        return front == null;
    }
    
    public String toString() {
		return rear.toString();
    	
    }
    
    // This method takes a Queue<CustomerNode> and id to find the node in the queue that corresponds to the id
	public String search(Queue<CustomerNode> customers, int id) {
		int size = customers.size;
		
		// This for loop iterates through the queue until the node is found or determined to not be there
		for(int i = 1; i <= size; i ++) {
			if(customers.front.id == id) {
				String time = String.valueOf(customers.front.wait);
				return time;
			}
			else if(customers.front.id != id) {
				CustomerNode temp = customers.dequeue();
				customers.enqueue(temp);
			}
		}
		
		return "0";
	}
}