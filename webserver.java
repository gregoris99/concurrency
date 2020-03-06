import java.util.*;

class webserver implements Runnable {  //Web server removes elements from the buffer
  										

    public int id;
    public Buffer buff;
    public int elements;
    public int elementsRemoved=0;

    public int speed;

    public int getId() {
      return id;
    }

    public void setElementsRemoved() {
      elementsRemoved++;
    }
    
    public webserver(int i, Buffer b, int el, int s) {

      this.id=i;
      this.buff=b;
      this.elements=el;     
      this.speed=s;

    }	

    public void run() {

        serverRemove();      
      
    }    
    
    public void serverRemove() {

        while(elementsRemoved<elements) {

            buff.removeElement(this);  
            
            // try {
            //   //Thread.sleep(speed);
            // }
            // catch (InterruptedException e){
            //   e.printStackTrace();
            // } 

        }
   
    }  
    
}  