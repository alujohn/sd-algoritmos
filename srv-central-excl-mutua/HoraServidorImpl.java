import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("serial")
public class HoraServidorImpl extends UnicastRemoteObject implements HoraServidor {

	private LocalTime hora;

	public HoraServidorImpl(LocalTime horario) throws RemoteException {
		this.hora = horario;
	}

	@Override
	public void ajustarHora(LocalTime horaClient, long diffNanos) throws RemoteException {
		long newNanos = this.getHora().toNanoOfDay() - horaClient.toNanoOfDay();
		newNanos = newNanos * -1 + diffNanos + this.getHora().toNanoOfDay();
		this.hora = LocalTime.ofNanoOfDay(newNanos);
		System.out.println("Hora atualizada: " + DateTimeFormatter.ofPattern("HH:mm:ss").format(this.hora));
	}

	@Override
	public LocalTime getHora() throws RemoteException {
		return hora;
	}

}