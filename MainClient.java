//package tcp_client;

public class MainClient {
	
	public static void main(String[] args) {
		try {
			String ip = args[0];
			String port = args[1];
			System.out.printf("\nIP entered = " + ip + " port = " + port + "\n");
			Client c = new Client(ip, port);
			clientThread t1 = new clientThread(c, "send");
			clientThread t2 = new clientThread(c, "get");		
			try {
				t1.t2.join();
				t2.t2.join();
			} catch (InterruptedException e) {
				System.out.println("Main thread interrupted");
			}
		} catch (Exception e) { System.out.println("Exception"); }
	}
}
