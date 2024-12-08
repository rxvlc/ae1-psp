package es.florida.ae1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe principal per a simular un procés i calcular el temps emprat.
 */
public class SimulacioMP {

	/**
	 * Mètode principal d'entrada del programa.
	 *
	 * @param args Arguments de la línia de comandes. El primer argument (args[0])
	 *             indica el tipus de simulació (un enter).
	 */
	public static void main(String[] args) {
		// Executa la simulació amb el tipus indicat com a argument
		simulation(Integer.parseInt(args[0]));
	}

	/**
	 * Simula un procés durant un temps determinat basat en el tipus de simulació.
	 * Es calcula un valor aleatori amb funcions matemàtiques mentre dura la
	 * simulació.
	 *
	 * @param type El tipus de simulació (determina la duració).
	 * @return El resultat del càlcul final de la simulació.
	 */
	public static double simulation(int type) {
		// Hora d'inici de la simulació
		LocalDateTime horaInici = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SS");
		String horaIniciFormatejada = horaInici.format(formatter);
		System.out.println(horaIniciFormatejada);

		// Càlcul del temps de simulació
		double calc = 0.0;
		double simulationTime = Math.pow(5, type); // Duració de la simulació en mil·lisegons

		double startTime = System.currentTimeMillis();
		double endTime = startTime + simulationTime;

		// Bucle de càlcul mentre dure la simulació
		while (System.currentTimeMillis() < endTime) {
			calc = Math.sin(Math.pow(Math.random(), 2));
		}

		// Hora de finalització de la simulació
		LocalDateTime horaFi = LocalDateTime.now();
		String horaFiFormatejada = horaFi.format(formatter);
		System.out.println(horaFiFormatejada);

		// Càlcul del temps emprat
		Duration duration = Duration.between(horaInici, horaFi);
		double tempsEmprat = duration.toMillis() / 1000.0;
		String tempsEmpratFormatejat = String.format("%.2f", tempsEmprat).replace(",", "_");

		// Resultats
		System.out.println(tempsEmpratFormatejat); // Temps emprat en segons_centèsimes
		System.out.println(calc); // Resultat del càlcul de la simulació

		return calc;
	}
}
