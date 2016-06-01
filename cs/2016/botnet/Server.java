/* adapted from 
http://www.cise.ufl.edu/~amyles/tutorials/tcpchat/

"Java TCP Sockets and Swing Tutorial" by Ashis Myles
*/
import java.lang.*;
import java.io.*;
import java.net.*;

class Server {
	public static void main(String args[]) {
		String data = "This is a test string of data";
		try {
			ServerSocket myServer = new ServerSocket(5555);
			Socket mySocket = myServer.accept();
			System.out.println("Server connection established");
			PrintWriter myOutput = new PrintWriter(mySocket.getOutputStream(), true);
			System.out.println("Sending string: '" + data );
			myOutput.print(data);
			myOutput.close();
			mySocket.close();
			myServer.close();
		}catch (Exception e) {
			System.out.print("Something went wrong");
		}
	}
}
		
