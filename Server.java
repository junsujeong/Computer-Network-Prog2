/**
Server class designed to serve clients. the server listen to client for 
connection and once it receives the request, it will handle the task
and communicate with the connecting client
@author jeongj
*/
import java.io.*;
import java.net.*;


public class Server 
{
   /**
   Main method of Server that allows to run the server.
   @param args is unused
   */
   public static void main(String [] args)
   {
      try
      {
         Server server = new Server();
         server.run();
      }
      catch( Exception ex )
      {
         System.out.println( "Error: " + ex ); 
      }
   }
   
   /**
   Run method that create server socket with port number '5764'
   then accept the socket and hand it the server thread to handle 
   the request. With PrintWriter which writes on logFile.
   */   
   public void run()
   {
      try
      {
         int port = 5764;
         PrintWriter writeLog = new PrintWriter
                                (new FileOutputStream(new File("prog2.log")), 
                                true);
         ServerSocket servSock = new ServerSocket( port );
         while(true)
         {
            Socket sock = servSock.accept();
            ServerThread servThread = new ServerThread( sock, writeLog );
            servThread.start();
         }
      }
      catch( Exception ex )
      {
         System.out.println( "Error: " + ex ); 
      }
   }   
}