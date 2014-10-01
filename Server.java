//package tcp_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	ServerSocket serverSocket;
	Socket clientSocket;
	private boolean control2 = true;
	//String toClient;
	
	public Server(int portNumber) {
		try {
			this.serverSocket = new ServerSocket(portNumber);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Socket serverStart() {
		try {
			clientSocket = serverSocket.accept();
			System.out.println("\nNew client connected with ip : " + clientSocket.getRemoteSocketAddress() + "\n");
		}catch (IOException e) {
			e.printStackTrace();
		}
		return clientSocket;
	}
	
	public void sendchat() {
		try {
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			while(control2) {
				String toClient;
				toClient = stdIn.readLine();
				if (control2){
					for (int itr = 0 ;itr<MainServer.index; itr++) {
						PrintWriter out = new PrintWriter(MainServer.clientSocketarray[itr].getOutputStream(), true);
						out.println(toClient);
						System.out.println("Server to " + MainServer.clientSocketarray[itr].getRemoteSocketAddress() + " : " + toClient);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	public void getChat(Socket clientSocket) {
		try {
			mainloop:
			while(true) {				
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				String fromClient;
				while ((fromClient = in.readLine()) != null) {
					if(fromClient.equals("disconnect")) {
						System.out.println(clientSocket.getRemoteSocketAddress()+" has exitted");
						control2 = false;
						clientSocket.close();
						break mainloop;
					}
					System.out.println(" " + clientSocket.getRemoteSocketAddress() + " : " + fromClient);
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
