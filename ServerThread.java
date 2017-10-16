/**
Server Thread class which serves the request of clients.
Basically, in this program server thread brings the input text
from the socket and encrypt of it and 
write the text back to socket.
*/

import java.io.*;
import java.net.*;
import java.util.Date;


class ServerThread extends Thread
{
   private Socket sock;	         
   private PrintWriter ioPrint;
   private PrintWriter logFilePrint;
   private BufferedReader inFromClient;  
   
   /**
   Constructor of serverThread class that initialize sock,
   printWriter for socket IO and log file and bufferedReader
   @param socket socket that was created in the server class
   */
   public ServerThread (Socket clientSock, PrintWriter logfile)
   {
      try
      {
         logFilePrint = logfile;
         sock = clientSock;
         inFromClient = new BufferedReader(new InputStreamReader
                        (sock.getInputStream())); 
         ioPrint = new PrintWriter(sock.getOutputStream(), true);
      }
      catch( Exception ex )
      {
         logFilePrint.println( ex ); 
      }
   }
   
   
   /**
   run method which reads a text from client and 
   calls encrypt method to process the request from client.
   Once it done with encrypt, write the text back to socket.
   And close when the text is "quit".
   */   
   public void run()
   {
      try
      {
         boolean quitTime = false;
         PolyAlphabetic polyAlpha = new PolyAlphabetic();
         PrintConnection();
         while(!quitTime)
         {
            String plainText = inFromClient.readLine();
            if (plainText.compareTo("quit") == 0)
            {
               quitTime = true;
               ioPrint.println("Good Bye!");
            }
            else
            {
               String cipherText = polyAlpha.encrypt(plainText);
               ioPrint.println(cipherText);
            }
         }
         logFilePrint.println("Connection closed, Port: " + sock.getPort());
         Close();
      }
      catch( Exception ex )
      {
         logFilePrint.println( ex ); 
      }
   }
   
   /*
   This method are prits the date, inet address, and port number
   once it got connection from client side
   */
   private void PrintConnection()
   {
      try
      {
         InetAddress inetAddress = sock.getInetAddress();
         int port = sock.getPort();
         String date = (new Date()).toString();
         logFilePrint.println( "Got a connection: " + date + "  /" 
                               + inetAddress + "  Port: " + port );
      }
      catch( Exception ex )
      {
         logFilePrint.println( ex ); 
      }
   }
  
   /*
   This method is to close socket io PrintWriter, BufferReader from 
   client, and the socket.
   */
   private void Close()
   {
      try
      {
         ioPrint.close();
         inFromClient.close();
         sock.close();
      }
      catch( Exception ex )
      {
         logFilePrint.println( ex ); 
      }
   }
}
