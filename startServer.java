import java.util.*;



public class startServer {

	public Buffer b; //Creation of buffer object  
	public static int bufferSize;
	public static int usersNum;
	public static int serversNum;
	public static int elements;
	public static int perUser, perServer, userExtra, serverExtra;

	public static user[] users;
	public static webserver[] servers;	

    public startServer() {	//Creates execution scenario between user and webservers on buffer
        
        long startTime = System.currentTimeMillis();		
																
		//Instantiate all objects (webserver, users, buffer)

		b = new Buffer(bufferSize);	

		//Arrays to hold Users and Servers

		users = new user[usersNum];
		servers = new webserver[serversNum];
		
		//Giving the right number of Elements to Users and Servers

		perUser=elements/usersNum;
		userExtra=elements%usersNum;

		perServer=elements/serversNum;
		serverExtra=elements%serversNum;

		//Initializing the Users	
		

		for(int i=0; i<usersNum; i++) {

			if(userExtra>0) {

				users[i]= new user(i, b, perUser+1);
				userExtra=userExtra-1;
				
			}
			else
				users[i]= new user(i, b, perUser);

		}

		//Initializing the Servers

		int serverSpeed=20;

		for(int i=0; i<serversNum; i++) {

			if(serverExtra>0) {

				servers[i]= new webserver(i, b, perServer+1, serverSpeed);
				serverExtra=serverExtra-1;
			}
			else
				servers[i]= new webserver(i, b, perServer, serverSpeed);

			if((i+1)%2==0)
			  serverSpeed=10;
			else
			  serverSpeed=70;  
		}

		//Arrays to hold the Threads

		Thread[] userThreads = new Thread[usersNum];
		Thread[] serverThreads = new Thread[serversNum];

		//Making the Threads

		for(int i=0; i<usersNum; i++) {

			userThreads[i]=new Thread(users[i]);
		}

		for(int i=0; i<serversNum; i++) {

			serverThreads[i]=new Thread(servers[i]);
		}

		//Starting the Threads


		for(int i=0; i<usersNum; i++) {

			userThreads[i].start();
		}

		for(int i=0; i<serversNum; i++) {

			serverThreads[i].start();
		}

		//Joining the Threads


		for(int i=0; i<usersNum; i++) {

			try {
				userThreads[i].join();
			} catch(InterruptedException e){
				e.printStackTrace();
			}

		}		


		for(int i=0; i<serversNum; i++) {

			try {
				serverThreads[i].join();
			} catch(InterruptedException e){
				e.printStackTrace();
			}

		}											

		System.out.println("-----------------------");  //Outputs the total number of elements added/removed from user and webserver					
						

		for(int i=0; i<usersNum; i++)
			System.out.println("User " + i + " created a total of " + users[i].elementsCreated + " elements");

		for(int i=0; i<serversNum; i++)
			System.out.println("Consumer " + i + " consumed a total of " + servers[i].elementsRemoved + " elements");	


		System.out.println("-----------------------"); //Check to see buffer if all elements produced from users have been successfully removed by webservers
		System.out.println("Buffer has " + b.elements + " elements remaining");			
		
		System.out.println("-----------------------"); //Checks if all users and web servers successfully finished

		for(int i=0; i<usersNum; i++)
			System.out.println("User Thread " + i + " is alive: " + userThreads[i].isAlive());

		for(int i=0; i<serversNum; i++)
			System.out.println("Server Thread " + i + " is alive: " + serverThreads[i].isAlive());							

				
		long endTime = System.currentTimeMillis();

		System.out.println("-----------------------");
     	System.out.println("Program took " + (endTime - startTime) + " milliseconds to complete");		
	
	}
	
	//////// M A I N   M E T H O D /////////////
  
	public static void main(String[] args) {

	    //Insert user inputted values for program execution

		Scanner x = new Scanner(System.in);
   	   
		System.out.println("Enter buffer capacity");				
		bufferSize=x.nextInt();
    
		System.out.println("Enter number of users");
		usersNum=x.nextInt();
    
		System.out.println("Enter number of servers");
		serversNum=x.nextInt();
    
		System.out.println("Enter total number of elements");
		elements=x.nextInt();

		x.close();
		
		startServer start = new startServer();
		
	}
	
}
