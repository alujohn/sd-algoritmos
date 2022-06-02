import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalTime;

public interface HoraServidor extends Remote {

	LocalTime getHora() throws RemoteException;

	void ajustarHora(LocalTime horaClient, long nanos) throws RemoteException;

}