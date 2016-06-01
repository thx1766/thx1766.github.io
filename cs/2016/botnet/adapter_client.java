/* adapted from http://www.cise.ufl.edu/~amyles/tutorials/tcpchat/

"Java TCP Sockets and Swing tutorial" by Ashish Myles
*/
import java.lang.*;
import java.io.*;
import java.net.*;

class adapter_client {
	public static void main(String args[]) {
		try{
			//adapter.cs.rutgers.edu
			Socket mySocket = new Socket("128.6.13.206", 5555);
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
