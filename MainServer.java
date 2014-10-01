//package tcp_server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import tcpsockets.Client;
//import tcpsockets.Server;
//import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MainServer {
	public static int index = 0;
	public static Socket [] clientSocketarray;
	
	public static void main(String[] args) {
		try {
			int port = Integer.parseInt(args[0]);
			Server s = new Server(port);
			int max_clients = Integer.parseInt(args[1]);
			System.out.printf("\nport no. entered = " + port + " max client = " + max_clients + "\n");
			serverThread [] t = new serverThread[max_clients];
			clientSocketarray = new Socket[max_clients]; 
			serverThread t_send_chat = new serverThread(s, "sendchat");
			while(index < max_clients) {
				clientSocketarray[index] = s.serverStart();
				t[index] = new serverThread(s, "getchat", clientSocketarray[index]);
				index++;
			}
			System.out.printf("\nYou have reached the limits of the no. of clients, no more clients will be accepted\n");
			
			for(int i = 0 ; i<index; i++) {
				t[i].t1.join();
			}
			s.serverSocket.close();
		
		}catch(Exception e) { System.out.println("Exception"); }
	}
}
