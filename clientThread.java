//package tcp_client;

public class clientThread implements Runnable {
	private Client c;
	Thread t2;
	String what;
	
	public clientThread(Client c, String w) {
		this.t2 = new Thread(this);
		this.c = c;
		this.what = w;
		t2.start();
	}
	
	//public void start() {
		
	//}
	
	public void run() {
		if(what.equals("send"))
			c.sendChat();
		else
			c.getChat();
	}
}
