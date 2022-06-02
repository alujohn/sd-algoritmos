import java.util.ArrayList;

class Proces extends Thread {

	private final int idProces;
	private int contador;
	ArrayList<Event> proces = new ArrayList();

	public int getIdProces() {
		return idProces;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public Proces(int idProces, int contador) {
		this.idProces = idProces;
		this.contador = contador;
	}

	public void run() {
		while (true) {

			try {
				Event evento = new Event(idProces, contador, false);
				proces.add(evento);
				contador++;
				sleep(1000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}

}