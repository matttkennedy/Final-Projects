

import java.io.*;
import java.util.Scanner;


/**
 * This class overrides some of the methods in the abstract 
 * superclass in order to give more functionality to the server.

 */
public class EchoServer extends AbstractServer 
{
  //Class variables *************************************************
  
  /**
   * The default port to listen on.
   */
	ChatIF serverUI;
  final public static int DEFAULT_PORT = 5555;
  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the echo server.
   *
   * @param port The port number to connect on.
   */
  public EchoServer(int port) // echo server extends abstract server
  {
    super(port);
  }

  
  public EchoServer(int port, ChatIF serverUI) // echo server extends abstract server
  {
    super(port);
    this.serverUI = serverUI;
  }

  
  //Instance methods ************************************************
  
  /**
   * This method handles any messages received from the client.
   * THIS IS IMPORTANT
   * @param msg The message received from the client.
   * @param client The connection from which the message originated.
   */
  public void handleMessageFromClient
    (Object msg, ConnectionToClient client)
  {
	 // System.out.println("Hello2");
	  if( msg instanceof Envolope )
	  {
		  serverUI.display("Hey, i got mail...");
		  Envolope e = (Envolope)msg;
		  
		  //used this to test if they were the same
		  //System.out.println(e.getData().toString()=="#testEnvolope");
		  
		  if(e.getData().toString().equals("#testEnvolope")){
			  
			  handleClientCommand("#ping", client);
		  }
		  
		  if(e.getData().toString().equals("#TicTacToe")){
	
				  sendToAClient(e.getPlayer2().toString(), "Your turn", client);
				  assignTTTGame(e.getPlayer2().toString(),e);					
			  	  assignTTTGame(e.getPlayer2().toString(),e);
		  }
		  
		  if(e.getData().toString().equals("#Connect4")){
			  
			  sendToAClient(e.getPlayer2().toString(), "Your Go!", client);
			  playConnect4(e.getPlayer2().toString(),e);					
			  playConnect4(e.getPlayer2().toString(),e);
		  }
		  
	  }
	  else if ( ((String)msg).charAt(0) =='#')
	  {
		  handleClientCommand(msg, client);
	  }
	  else
	  {
    System.out.println("Message received: " + msg + " from " + client);
    this.sendToAllClients(msg, client);
   }
  }
  
  
  //Handles messages from Server
	  public void handleMessageFromServerUI(String message)
	    {
	  	if (message.indexOf("#") == 0) {
	  		
	  		//handleServerCommand(message);
	  	}
	  	else {
	  	
	  	    try
	  	    {
	  	    	sendToAllClients("SERVER MSG> " + message);
	  	    }
	  	    catch(Exception e)
	  	    {
	  	    	serverUI.display
	  	        ("Could not send message to server.  ");
	  	     
	  	    }
	  	}
	    }

  //Handles user input from the client
 public void handleClientCommand(Object msg, ConnectionToClient client)
 {
	 String message = (String)msg;
	 
	 if (message.indexOf("#Login") == 0){
		 
		 int start = message.indexOf(" ");
		 int end = message.length();
		 
		 String userName = message.substring(start, end);
		 userName = userName.trim();
			 		  
		 client.setInfo("userName", userName);
		 sendToAllClients(client.getInfo("userName").toString() + " just logged in \n");
			 		  			 
	 }
	 
	 
	 else if (message.indexOf("#C4")==0){
		 String move = message.substring(message.indexOf(" "), message.length());
		 move = move.trim();
		 
		 int moveNum = Integer.parseInt(move);
		 Envolope e = (Envolope)(client.getInfo("C4"));
		 
		 int marker = 1;
		 if(e.getPlayer2().toString().equals(client.getInfo("userName").toString()))
		 {
			 marker = 2;
		 }
		 
	
	 }
	 
	 
	 
	 
	 //Start Tic-Tac-Toe
	 else if (message.indexOf("#TTT")==0){
		 String move = message.substring(message.indexOf(" "), message.length());
		 move = move.trim();
		 
		 int moveNum = Integer.parseInt(move);
		 Envolope e = (Envolope)(client.getInfo("TTT"));
		 
		 int marker = 1;
		 if(e.getPlayer2().toString().equals(client.getInfo("userName").toString()))
		 {
			 marker = 2;
		 }
		 int[][] board = (int[][])e.getCommand();
		 if(moveNum == 0)
			 board[0][0] = marker;
		 if(moveNum == 1)
			 board[0][1] = marker;
		 if(moveNum == 2)
			 board[0][2] = marker;
		 if(moveNum == 3)
			 board[1][0] = marker;
		 if(moveNum == 4)
			 board[1][1] = marker;
		 if(moveNum == 5)
			 board[1][2] = marker;
		 if(moveNum == 6)
			 board[2][0] = marker;
		 if(moveNum == 7)
			 board[2][1] = marker;
		 if(moveNum == 8)
			 board[2][2] = marker;
		 
		  assignTTTGame(e.getPlayer2().toString(),e);					
	  	  assignTTTGame(e.getPlayer1().toString(),e);
	  	  e.addTurn();
	  	  if(e.getTurn()%2==0){
	  			  sendToAClient(e.getPlayer2().toString(), "Your turn", client);
	  			  sendEnvolope(e.getPlayer2().toString(), e);
	  	  }
	  	  else{
  			  sendToAClient(e.getPlayer1().toString(), "Your turn", client);
  			  sendEnvolope(e.getPlayer1().toString(), e);
  	  }
	  	  		
	 }
	 
	 if(message.indexOf("#w")==0)
	 {
		 int start = message.indexOf(" ");
		 int end = message.length();
		 
		 String messageWithoutCommand = message.substring(start, end);
		 messageWithoutCommand = messageWithoutCommand.trim();
		 
		 String user = messageWithoutCommand.substring(0, messageWithoutCommand.indexOf(" "));
		 user = user.trim();
		 
		 String sweetNuthing = messageWithoutCommand.substring(messageWithoutCommand.indexOf(" "), messageWithoutCommand.length());
		 sweetNuthing = sweetNuthing.trim();
		 
		 sendToAClient(user, sweetNuthing, client);
	 }
	 
	 else if (message.indexOf("join")==0)
	 {
		 int start = message.indexOf(" ");
		 
	 }
	 
	 else if(message.indexOf("#ping")==0){
		 
		 if(client.getInfo("pingCount")==null){
			 client.setInfo("pingCount", 1);
		 }
		 else{
			 int pingCount = ((int)(client.getInfo("pingCount")));
			 pingCount++;
			 client.setInfo("pingCount", pingCount);
		 }
		 
		 tryToSendToClient("pong "+client.getInfo("pingCount"), client);
	 }
 }
 
 
 public void assignTTTGame(String user, Envolope e)
 {
   Thread[] clientThreadList = getClientConnections();

   for (int i=0; i<clientThreadList.length; i++)
   {
	 ConnectionToClient currentClient =  ((ConnectionToClient)clientThreadList[i]);
	 
	 if(currentClient.getInfo("userName").toString().equals(user))
     {
    	currentClient.setInfo("TTT", e);
     }
    
     }
   }
 
 public void playConnect4(String user, Envolope e)
 {
	   Thread[] clientThreadList = getClientConnections();

	   for (int i=0; i<clientThreadList.length; i++)
	   {
		 ConnectionToClient currentClient =  ((ConnectionToClient)clientThreadList[i]);
		 
		 if(currentClient.getInfo("userName").toString().equals(user))
	     {
	    	currentClient.setInfo("C4", e);
	     }
	    
	     }
	   }
 
 public void sendEnvolope(String user, Object e)
 {
   Thread[] clientThreadList = getClientConnections();

   for (int i=0; i<clientThreadList.length; i++)
   {
	 ConnectionToClient currentClient =  ((ConnectionToClient)clientThreadList[i]);
	 
	 if(currentClient.getInfo("userName").toString().equals(user))
     
     {
		 tryToSendToClient(e, currentClient);
     }	
   }
 }
 
 public void sendToAClient(String user, String message, ConnectionToClient client)
 {
   Thread[] clientThreadList = getClientConnections();

   for (int i=0; i<clientThreadList.length; i++)
   {
	 ConnectionToClient currentClient =  ((ConnectionToClient)clientThreadList[i]);
	 
	 if(currentClient.getInfo("userName").toString().equals(user))
     try
     {
    	 String whisperer = client.getInfo("userName").toString();
    	 currentClient.sendToClient("\n" + whisperer + " whispered to you: " + message);
     }
     catch (Exception ex) {
    	 System.out.println("Failed to send to " + currentClient.getInfo("userName"));
    	 ex.printStackTrace();
     }
   }
 }
 
 
 public void sendToAllClients(Object msg, ConnectionToClient client)
 {
	 String message = (String)msg;
   Thread[] clientThreadList = getClientConnections();

   for (int i=0; i<clientThreadList.length; i++)
   {
	   ConnectionToClient currentClient = ((ConnectionToClient)clientThreadList[i]);
     try
     {
    	 System.out.println(client.getInfo("userName"));
       tryToSendToClient(client.getInfo("userName").toString() + "> " + message, currentClient);
     }
     catch (Exception ex) {ex.printStackTrace();}
   }
 }
 
 
	 public void sendToARoom(Object msg, ConnectionToClient client) //***IMPORTANT METHOD*** Don't touch. Copy and Paste in Echo Server.
	   {
	 	  String message = (String)msg;
	 	  String room = client.getInfo("room").toString();
	 	  
	 	  Thread[] clientThreadList = getClientConnections();

	 	  for (int i=0; i<clientThreadList.length; i++)
	 	  {
	 		  ConnectionToClient currentClient = ((ConnectionToClient)clientThreadList[i]);
	 	  if (currentClient.getInfo("room").toString().equals(room)) {
	 		  tryToSendToClient(client.getInfo("userName").toString() + "> " + message, currentClient);
	 	  }
	 		  
	     }
	   }

	  public void printPattern(int[][] f)
	  {
	    for (int i =0;i<f.length;i++)
	    {
	      for (int j=0;j<f[i].length;j++)
	      {
	        System.out.print(f[i][j]);
	      }
	      System.out.println();
	    }
	  }
 
	  
	  public void dropRedPattern(String[][] f)
	  {
	    System.out.println("Drop a red disk at column (0�6): ");
	    Scanner scan = new Scanner (System.in);
	     

	    int c = 2*scan.nextInt()+1;
	    

	    for (int i =5;i>=0;i--)
	    {
	      if (f[i][c] == " ")
	      {
	        f[i][c] = "R";
	        break;
	      }
	       
	    }
	  }
	  
	  
	  
  
  /**
   * This method overrides the one in the superclass.  Called
   * when the server starts listening for connections.
   */
  protected void serverStarted()
  {
    System.out.println
      ("Server listening for connections on port " + getPort());
  }
  
  /**
   * This method overrides the one in the superclass.  Called
   * when the server stops listening for connections.
   */
  protected void serverStopped()
  {
    System.out.println
      ("Server has stopped listening for connections.");
  }
  
  //Class methods ***************************************************
  
  /**
   * This method is responsible for the creation of 
   * the server instance (there is no UI in this phase).
   *
   * @param args[0] The port number to listen on.  Defaults to 5555 
   *          if no argument is entered.
   */
  public static void main(String[] args) 
  {
    int port = 0; //Port to listen on

    try
    {
      port = Integer.parseInt(args[0]); //Get port from command line
    }
    catch(Throwable t)
    {
      port = DEFAULT_PORT; //Set port to 5555
    }
	
    EchoServer sv = new EchoServer(port);
    
    try 
    {
      sv.listen(); //Start listening for connections
    } 
    catch (Exception ex) 
    {
      System.out.println("ERROR - Could not listen for clients!");
    }
  }
  
  protected void clientConnected(ConnectionToClient client) {	  
	  System.out.printf("Client connected: %s \n", client);
  }
  

	  private void tryToSendToClient (Object message, ConnectionToClient client) {
	  	  
	  	  try {
	  		  client.sendToClient(message);
	  	  }
	  	  catch (IOException ioe) {
	  		  
	  		  System.out.println("Failed to send to client: " + client.getInfo("userName"));
	  		  ioe.printStackTrace();
	  	  }
	    }

  
  synchronized protected void clientDisconnected(ConnectionToClient client) {
	  System.out.printf("\nClient disconnected: %s", client);
  }
  
  synchronized protected void clientException(ConnectionToClient client, Throwable exception) {
	  clientDisconnected(client);
  }
}
//End of EchoServer class
