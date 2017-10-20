package autopilot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Communicatie {
	
	private static BufferedReader inputReader;
	
	public static void main(String[] args) throws IOException {
		ServerSocket autopilotSocket;
		autopilotSocket = new ServerSocket(6667);
		Socket testbedSocket = autopilotSocket.accept();
		inputReader = new BufferedReader(new InputStreamReader(testbedSocket.getInputStream()));
		String input = inputReader.readLine();
		System.out.println(input);
	}

}
