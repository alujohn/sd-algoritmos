import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {

	public static void main(String[] args) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalTime horarioLocal = LocalTime.parse("12:00:00", formatter);
		System.out.println("Hora Local: " + formatter.format(horarioLocal));

		try {
			// Servidor A
			Registry rA = LocateRegistry.getRegistry("localhost", 1500);
			HoraServidor horarioA = (HoraServidor) rA.lookup(HoraServidorImpl.class.getSimpleName());
			LocalTime horarioServidor1 = horarioA.getHora();
			System.out.println("Hora Servidor A: " + formatter.format(horarioServidor1));

			// Servidor B
			Registry rB = LocateRegistry.getRegistry("localhost", 1501);
			HoraServidor horarioB = (HoraServidor) rB.lookup(HoraServidorImpl.class.getSimpleName());
			LocalTime horarioServidor2 = horarioB.getHora();
			System.out.println("Hora Servidor B: " + formatter.format(horarioServidor2));

			// Servidor C
			Registry rC = LocateRegistry.getRegistry("localhost", 1502);
			HoraServidor horarioC = (HoraServidor) rC.lookup(HoraServidorImpl.class.getSimpleName());
			LocalTime horarioServidor3 = horarioC.getHora();
			System.out.println("Hora Servidor C: " + formatter.format(horarioServidor3));

			long horaCalculada = calculaHora(horarioLocal, horarioServidor1, horarioServidor2, horarioServidor3);

			horarioA.ajustarHora(horarioLocal, horaCalculada);
			horarioB.ajustarHora(horarioLocal, horaCalculada);
			horarioC.ajustarHora(horarioLocal, horaCalculada);
			horarioLocal = horarioLocal.plusNanos(horaCalculada);

			System.out.println("\nOs horários foram atualizados:");
			System.out.println(" - Hora Local: " + formatter.format(horarioLocal));
			System.out.println(" - Servidor A: " + formatter.format(horarioA.getHora()));
			System.out.println(" - Servidor B: " + formatter.format(horarioB.getHora()));
			System.out.println(" - Servidor C: " + formatter.format(horarioC.getHora()));

		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

	private static long calculaHora(LocalTime horarioLocal, LocalTime horarioServidor1, LocalTime horarioServidor2,
			LocalTime horarioServidor3) {
		long horaLocal = horarioLocal.toNanoOfDay();
		long horaCalculada = (horarioServidor1.toNanoOfDay() - horaLocal //
				+ horarioServidor2.toNanoOfDay() - horaLocal //
				+ horarioServidor3.toNanoOfDay() - horaLocal) / 3;

		return horaCalculada;
	}

}