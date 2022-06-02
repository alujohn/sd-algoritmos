import java.util.ArrayList;

public class Main extends Thread {
	public static void main(String[] args) {

		Proces P1 = new Proces(1, 5);
		Proces P2 = new Proces(2, 0);
		Proces P3 = new Proces(3, 10);
		int i ;

		System.out.println(P1.getName() + "->" + " Message      " + P2.getName() + "->" + " Message      "
				+ P3.getName() + "->" + " Message");
		P1.start();
		P2.start();
		P3.start();

		for(int x=0;x<10;x++) {
			if(!P1.proces.isEmpty()&&!P2.proces.isEmpty()&&!P3.proces.isEmpty()) {
				i=(int)Math.floor(Math.random()*4+1);
				if(i==2) {
					sendMessage(P1,P2);
				}

				if(i==3) {
					sendMessage(P2,P3);
				}
				if(i==4) {
					sendMessage(P3,P1);
				}

				System.out.println("       "+P1.getContador()+"-> "+P1.proces.get(P1.proces.size()-1).getMessage()
						+"               "+P2.getContador()+"-> "+P2.proces.get(P2.proces.size()-1).getMessage()
						+"               "+P3.getContador()+"-> "+P3.proces.get(P3.proces.size()-1).getMessage());
			}

			try {
				sleep(1000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		}

		ArrayList<Event> list_event_true = new ArrayList<Event>();

		for (Event event : P1.proces) {
			if (event.getMessage()) {
				list_event_true.add(event);
			}
		}

		for (Event event : P2.proces) {
			if (event.getMessage()) {
				list_event_true.add(event);
			}
		}

		for (Event event : P3.proces) {
			if (event.getMessage()) {
				list_event_true.add(event);
			}
		}

		for (Event event : list_event_true) {
			System.out.println(event.getIdProces()+" "+event.getContador()+" "+event.getMessage());
		}

	}

	public static void sendMessage(Proces transmitter, Proces receiver) {

		Event e = new Event(transmitter.getIdProces(), transmitter.getContador(), true);
		transmitter.proces.set(transmitter.proces.size() - 1, e);

		int contador_max = max(transmitter.getContador(), receiver.getContador());
		e = new Event(receiver.getIdProces(), contador_max + 1, false);
		receiver.proces.set(receiver.proces.size() - 1, e);
	}

	public static int max(int a, int b) {
		return (a >= b) ? a : b;
	}
}