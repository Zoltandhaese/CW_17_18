package testbed;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Communicatie {
	
	static DataOutputStream output;

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket testbedSocket;
		
		testbedSocket = new Socket("localhost", 6667);
		output = new DataOutputStream(testbedSocket.getOutputStream());
		output.write(55555);
		output.close();
		testbedSocket.close();
		
		

	}

}
