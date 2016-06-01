/* adapted from http://www.cise.ufl.edu/~amyles/tutorials/tcpchat/

"Java TCP Sockets and Swing tutorial" by Ashish Myles
*/
import java.lang.*;
import java.io.*;
import java.net.*;

class Client {
	public static void main(String args[]) {
		try{
			//Socket mySocket = new Socket("localhost", 5555);
			Socket mySocket = new Socket("128.6.13.172", 5555);
			//Socket mySocket = new Socket("ls.cs.rutgers.edu", 5555);
			BufferedReader input = new BufferedReader (new InputStreamReader(mySocket.getInputStream()));
			System.out.println("Recieved String: '");
			while (!input.ready()) {}
			System.out.println(input.readLine()); // Read one line and output it

			System.out.println("'");
			input.close();
		}catch(Exception e){
			System.out.println("Something seems to have gone wrong.");
			System.out.println(e);
		}
	}
}
