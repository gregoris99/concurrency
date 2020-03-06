import java.util.*;

class user implements Runnable {											
 
     public int id;
     public Buffer buff;
     public int elements;
     public int elementsCreated=0; 
   

     public int getId() {
          return id;
     }

     public void setElementsCreated() {
          elementsCreated++;
     }
  
     public user(int i, Buffer b, int el) {						//Created user will add a certain number of elements to the buffer.
     
          this.id=i;
          this.buff=b;
          this.elements=el;    
          
            
     }
    
     public void run() {         
          userAdd();   
     }

     public void userAdd() {

          while(elementsCreated<elements) {
               
               buff.addElement(this);              
                 
               // try{
               //      //Thread.sleep(50);
               //    }
               //   catch (InterruptedException e){
               //    e.printStackTrace();
               //   } 

          }        

     }
     
       
}   