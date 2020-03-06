import java.util.*;

public class Buffer {							//Provides data and operations onto the fixed-length buffer
    									
  private LinkedList<Integer> buf_list;		//Will always be an Integer.
  public int capacity;
  public int elements=0;

  public semaphore m = new semaphore(1);
	
  public Buffer(int n) {					//Buffer creation, with n indicating the maximum capacity
	
     buf_list = new LinkedList<Integer>();   
     this.capacity=n; 

  }

  public void addElement(user User) {    
   
      m.P();
      if(elements<capacity) {    

        buf_list.add(1);
        User.setElementsCreated();        
        elements++; 
        System.out.println("User " + User.getId()+ " adds an element " +elements+ "/" +capacity);         

      } 

      else System.out.println("Buffer is F U L L, User " + User.getId()+ "now sleeping");

    
      m.V();         
  }    
  
  public void removeElement(webserver Server) {    
    
      m.P();
      if(elements>0) { 

        buf_list.remove();  
        Server.setElementsRemoved();
        elements=elements-1;
        System.out.println("Server " + Server.getId() + " removed element " +elements+ "/" +capacity); 
         
      } 

      else System.out.println("Buffer is E M P T Y, Server " + Server.getId()+ " now sleeping");
      m.V(); 
  }	  


}
