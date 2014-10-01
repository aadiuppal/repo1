//package tcp_server;


import java.net.Socket;


public class serverThread implements Runnable{
	
	private Server s;
	Thread t1;
	String what;
	Socket clientSocket;
	
	public serverThread(Server s, String w, Socket clientSocket) {
		this.t1 = new Thread(this);
		this.s = s;
		this.what = w;
		this.clientSocket = clientSocket;
		t1.start();
		
	}
	public serverThread(Server s, String w) {
		this.t1 = new Thread(this);
		this.s = s;
		this.what = w;
		t1.start();
		
	}
	
	public void run() {
		if(what.equals("sendchat"))
			s.sendchat();
		else if(what.equals("getchat"))
			s.getChat(clientSocket);
		else
			s.serverStart();
	}

}
