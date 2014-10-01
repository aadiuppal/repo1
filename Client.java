//package tcp_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
//import java.io.PrintWriter;
//import java.net.ServerSocket;
import java.net.Socket;
//import java.net.SocketException;

public class Client {

	Socket clientSocket;
	private boolean control = true;
	
	public Client(String ipaddr, String portNumber) {
		try {
			this.clientSocket = new Socket(ipaddr, Integer.parseInt(portNumber));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getChat() {
		try {
			while(control) {
				BufferedReader in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
				String fromServer;
				while ((fromServer = in.readLine()) != null) {
					System.out.println("Server: " + fromServer);
				}
			}
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
 	}
	
	public void sendChat() {
		try {
			mainloop:
			while(true) {
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
				PrintWriter out = new PrintWriter(this.clientSocket.getOutputStream(), true);
				String toServer;
				while ((toServer = stdIn.readLine()) != null) {
					out.println(toServer);
					if(toServer.equals("disconnect")) {
						System.out.println("exiting!!!");
						control = false;
						break mainloop;	
					}
				    System.out.println("Client: " + toServer);
				}
			}
		//clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
