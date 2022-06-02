class Event implements Comparable<Event> {

	private int idProces, contador;
	private boolean message;

	public Event(int idProces, int contador, boolean message) {
		this.idProces = idProces;
		this.contador = contador;
		this.message = message;
	}

	public int getIdProces() {
		return idProces;
	}

	public void setIdProces(int idProces) {
		this.idProces = idProces;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public void setMessage(boolean message) {
		this.message = message;
	}

	public boolean getMessage() {
		return message;
	}

	@Override
	public int compareTo(Event event) {
		if (event.getContador() > contador) {
			return -1;
		} else if (event.getContador() < contador) {
			return 1;
		} else {
			return 0;
		}
	}
}