import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ServidorC {

	public static void main(String[] args) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		int porta = 1502;

		try {
			HoraServidor horaServidor = new HoraServidorImpl(LocalTime.parse("12:25:00", formatter));
			Registry registry = LocateRegistry.createRegistry(porta);
			registry.rebind(HoraServidorImpl.class.getSimpleName(), horaServidor);
			System.out.println(String.format("Servidor C iniciado na porta %s", porta));

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}