import java.util.*;

public class semaphore {
    
   //This is an example class for using a primitive synchronization (semaphore, lock). Please note that you
   //can ONLY put the synchronization keyword within these type of classes, and nowhere else within the program.

   private int permits = 0;

   public semaphore(int p)
   {
     permits = p; 
   }
   
   public synchronized void P()
   {
     permits = permits - 1;
     if (permits < 0)
     try{
        wait();
      }
     catch (InterruptedException e){
      e.printStackTrace();
     } 
   }
   
   public synchronized void V()
   {
     permits = permits + 1;
     if (permits <= 0)
       notify(); 
   }

}

