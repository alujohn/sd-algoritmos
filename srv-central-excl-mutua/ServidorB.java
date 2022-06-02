import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ServidorB {

	public static void main(String[] args) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		int porta = 1501;

		try {
			HoraServidor horaServidor = new HoraServidorImpl(LocalTime.parse("12:20:00", formatter));
			Registry registry = LocateRegistry.createRegistry(porta);
			registry.rebind(HoraServidorImpl.class.getSimpleName(), horaServidor);
			System.out.println(String.format("Servidor B iniciado na porta %s", porta));

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}