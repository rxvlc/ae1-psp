package es.florida.ae1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe que representa una simulació amb múltiples fils (Multithreading).
 * Aquesta classe implementa l'interfície {@link Runnable} per permetre
 * l'execució concurrent de simulacions.
 */
public class SimulacioMT implements Runnable {

	/**
	 * Tipus de simulació. Defineix la complexitat i duració de la simulació.
	 */
	int tipus;

	/**
	 * Arxiu de destinació on s'emmagatzemen els resultats de la simulació.
	 */
	File arxiuDesti;

	/**
	 * Constructor que inicialitza els paràmetres de la simulació.
	 * 
	 * @param tipus      Tipus de simulació (determina la duració i complexitat).
	 * @param arxiuDesti Arxiu on es desarà la informació de la simulació.
	 */
	public SimulacioMT(int tipus, File arxiuDesti) {
		this.tipus = tipus;
		this.arxiuDesti = arxiuDesti;
	}

	/**
	 * Executa la simulació i desa els resultats en un arxiu de destinació.
	 * 
	 * @param type Tipus de simulació. Afecta el temps de càlcul.
	 * @return El resultat final del càlcul realitzat durant la simulació.
	 */
	public double simulation(int type) {
		// Hora d'inici
		LocalDateTime horaInici = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SS");
		String horaIniciFormatejada = horaInici.format(formatter);

		// Simulació
		double calc = 0.0;
		double simulationTime = Math.pow(5, type); // Duració de la simulació en mil·lisegons
		LocalDateTime endSimulacion = horaInici.plusNanos((long) (simulationTime * 1_000_000)); // Convertir a
																								// nanosegons

		while (LocalDateTime.now().isBefore(endSimulacion)) {
			calc = Math.sin(Math.pow(Math.random(), 2)); // Càlcul fictici
		}

		// Hora de finalització
		LocalDateTime horaFi = LocalDateTime.now();
		String horaFiFormatejada = horaFi.format(formatter);

		// Càlcul del temps emprat
		Duration duration = Duration.between(horaInici, horaFi);
		double tempsEmprat = duration.toMillis() / 1000.0;
		String tempsEmpratFormatejat = String.format("%.2f", tempsEmprat).replace(",", "_");

		// Escriure resultats a l'arxiu
		try {
			FileWriter fw = new FileWriter(arxiuDesti);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(horaIniciFormatejada);
			bw.newLine();
			bw.write(horaFiFormatejada);
			bw.newLine();
			bw.write(tempsEmpratFormatejat);
			bw.newLine();
			bw.write(String.valueOf(calc));
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return calc;
	}

	/**
	 * Mètode que s'executa quan el fil s'inicia. Aquest mètode crida a la simulació
	 * amb el tipus especificat.
	 */
	public void run() {
		simulation(tipus);
	}
}
