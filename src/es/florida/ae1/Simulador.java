package es.florida.ae1;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa una aplicació Swing per simular proteïnes. Permet
 * especificar diferents tipus de simulacions mitjançant quadres de selecció
 * (spinners) i mostra els resultats a un quadre de text.
 */
public class Simulador extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Panell principal que conté tots els components de la interfície gràfica.
	 */
	private JPanel contentPane;

	/**
	 * Àrea de text on es mostren els resultats de les simulacions.
	 */
	JTextArea textAreaRes;

	/**
	 * Selector del nombre de simulacions del tipus 1.
	 */
	JSpinner spinnerTip1;

	/**
	 * Selector del nombre de simulacions del tipus 2.
	 */
	JSpinner spinnerTip2;

	/**
	 * Selector del nombre de simulacions del tipus 3.
	 */
	JSpinner spinnerTip3;

	/**
	 * Selector del nombre de simulacions del tipus 4.
	 */
	JSpinner spinnerTip4;

	/**
	 * Punt d'entrada principal de l'aplicació.
	 * 
	 * @param args Arguments de la línia de comandes (no utilitzats).
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Simulador frame = new Simulador();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor que inicialitza la finestra i els seus components.
	 */
	public Simulador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 771, 577);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Títol
		JLabel lblTitol = new JLabel("Simulació Proteïnes");
		lblTitol.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblTitol.setBounds(258, 9, 217, 53);
		contentPane.add(lblTitol);

		// Spinner tipus 1
		spinnerTip1 = new JSpinner();
		spinnerTip1.setModel(new SpinnerNumberModel(0, 0, null, 1));
		spinnerTip1.setBounds(130, 93, 87, 25);
		contentPane.add(spinnerTip1);

		JLabel lblProtTip1 = new JLabel("Tipus 1");
		lblProtTip1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblProtTip1.setBounds(227, 88, 115, 30);
		contentPane.add(lblProtTip1);

		// Spinner tipus 2
		spinnerTip2 = new JSpinner();
		spinnerTip2.setModel(new SpinnerNumberModel(0, 0, null, 1));
		spinnerTip2.setBounds(438, 93, 87, 25);
		contentPane.add(spinnerTip2);

		JLabel lblProtTip2 = new JLabel("Tipus 2");
		lblProtTip2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblProtTip2.setBounds(535, 88, 115, 30);
		contentPane.add(lblProtTip2);

		// Quadre de text per als resultats
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 302, 723, 227);
		contentPane.add(scrollPane);

		textAreaRes = new JTextArea();
		scrollPane.setViewportView(textAreaRes);

		// Spinner tipus 3
		spinnerTip3 = new JSpinner();
		spinnerTip3.setModel(new SpinnerNumberModel(0, 0, null, 1));
		spinnerTip3.setBounds(130, 155, 87, 25);
		contentPane.add(spinnerTip3);

		JLabel lblProtTip3 = new JLabel("Tipus 3");
		lblProtTip3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblProtTip3.setBounds(227, 150, 115, 30);
		contentPane.add(lblProtTip3);

		// Spinner tipus 4
		spinnerTip4 = new JSpinner();
		spinnerTip4.setModel(new SpinnerNumberModel(0, 0, null, 1));
		spinnerTip4.setBounds(438, 160, 87, 25);
		contentPane.add(spinnerTip4);

		JLabel lblProtTip4 = new JLabel("Tipus 4");
		lblProtTip4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblProtTip4.setBounds(535, 155, 115, 30);
		contentPane.add(lblProtTip4);

		// Botó per iniciar simulacions
		JButton btnSimular = new JButton("Simular");
		btnSimular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarSimulacionsMP();
				executarSimulacionsMT();
			}
		});
		btnSimular.setBounds(10, 268, 723, 23);
		contentPane.add(btnSimular);
	}

	/**
	 * Executa simulacions multiprocés de càlcul de proteïnes per a diferents tipus
	 * i genera arxius de resultat. Es mesura el temps emprat per a l'execució.
	 *
	 * @return {@code true} si totes les simulacions es completen amb èxit,
	 *         {@code false} si ocorre alguna interrupció durant l'execució.
	 */
	public boolean executarSimulacionsMP() {

		// Crear directori per a guardar resultats si no existeix
		File directoriRes = new File("./resultats/");
		if (!directoriRes.exists()) {
			directoriRes.mkdirs();
		}

		// Llista per a guardar els processos llançats
		List<Process> llistaProcessos = new ArrayList<>();
		int i = 0;

		// Mesurament del temps d'execució
		double startTime = System.currentTimeMillis();
		double endTime;

		// Simulacions per al tipus 1
		int cantTip1 = (int) spinnerTip1.getValue();
		for (i = 0; i < cantTip1; i++) {
			generarSimulacio(1, i, directoriRes, llistaProcessos);
		}

		// Simulacions per al tipus 2
		int cantTip2 = (int) spinnerTip2.getValue();
		for (i = 0; i < cantTip2; i++) {
			generarSimulacio(2, i, directoriRes, llistaProcessos);
		}

		// Simulacions per al tipus 3
		int cantTip3 = (int) spinnerTip3.getValue();
		for (i = 0; i < cantTip3; i++) {
			generarSimulacio(3, i, directoriRes, llistaProcessos);
		}

		// Simulacions per al tipus 4
		int cantTip4 = (int) spinnerTip4.getValue();
		for (i = 0; i < cantTip4; i++) {
			generarSimulacio(4, i, directoriRes, llistaProcessos);
		}

		// Espera fins que tots els processos acaben i calcula el temps emprat
		try {
			for (i = 0; i < llistaProcessos.size(); i++) {
				llistaProcessos.get(i).waitFor();
			}
			endTime = System.currentTimeMillis();
			textAreaRes.setText(
					textAreaRes.getText() + "Temps emprat per multiprocés: " + (endTime - startTime) / 1000 + "s\n");
		} catch (InterruptedException e1) {
			e1.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * Genera una simulació per a un tipus específic de procés i guarda els
	 * resultats.
	 *
	 * @param tipus           El tipus de simulació (1, 2, 3 o 4).
	 * @param index           L'índex de la simulació (comença en 0).
	 * @param directoriRes    El directori on es guardaran els arxius de resultat.
	 * @param llistaProcessos La llista on es registren els processos creats.
	 */
	private void generarSimulacio(int tipus, int index, File directoriRes, List<Process> llistaProcessos) {
		// Crear nom de l'arxiu basat en l'hora actual
		LocalDateTime ahora = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SS");
		String anyMesDiaHoraMinutSegonCentesimes = ahora.format(formatter);

		String nomArxiu = directoriRes.getAbsolutePath() + "/PROT_MP_" + tipus + "_n" + (index + 1) + "_"
				+ anyMesDiaHoraMinutSegonCentesimes + ".sim";
		File arxiu = new File(nomArxiu);

		// Crear l'arxiu
		try {
			arxiu.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// Llançar el procés de càlcul de proteïnes
		llistaProcessos.add(executarProcesCalculProteines(tipus, directoriRes, arxiu));
	}

	/**
	 * Executa simulacions multithreaded de diferents tipus de càlcul de proteïnes.
	 * 
	 * Aquest mètode crea i executa fils de forma paral·lela per a cada tipus de
	 * càlcul, generant fitxers de resultat en el directori especificat. Les
	 * simulacions s'executen en quatre grups diferents segons el tipus (tipus 1,
	 * tipus 2, tipus 3, tipus 4).
	 * 
	 * @return {@code true} si totes les simulacions han finalitzat correctament,
	 *         {@code false} si s'ha produït alguna interrupció.
	 */
	public boolean executarSimulacionsMT() {

		// Crear el directori per als resultats si no existeix
		File directoriRes = new File("./resultats/");
		if (!directoriRes.exists()) {
			directoriRes.mkdirs();
		}

		// Llista per a emmagatzemar els fils creats
		List<Thread> llistaThreads = new ArrayList<>();
		int i = 0;

		// Mesurar el temps d'execució
		double startTime = System.currentTimeMillis();
		double endTime;

		// Simulacions del tipus 1
		int cantTip1 = (int) spinnerTip1.getValue();
		for (i = 0; i < cantTip1; i++) {
			crearIExecutarFil(1, i, directoriRes, llistaThreads);
		}

		// Simulacions del tipus 2
		int cantTip2 = (int) spinnerTip2.getValue();
		for (i = 0; i < cantTip2; i++) {
			crearIExecutarFil(2, i, directoriRes, llistaThreads);
		}

		// Simulacions del tipus 3
		int cantTip3 = (int) spinnerTip3.getValue();
		for (i = 0; i < cantTip3; i++) {
			crearIExecutarFil(3, i, directoriRes, llistaThreads);
		}

		// Simulacions del tipus 4
		int cantTip4 = (int) spinnerTip4.getValue();
		for (i = 0; i < cantTip4; i++) {
			crearIExecutarFil(4, i, directoriRes, llistaThreads);
		}

		// Esperar que tots els fils finalitzen
		try {
			for (Thread t : llistaThreads) {
				t.join();
			}
			// Calcular el temps emprat
			endTime = System.currentTimeMillis();
			textAreaRes.setText(
					textAreaRes.getText() + "Temps emprat per multifil: " + (endTime - startTime) / 1000 + "s\n");
		} catch (InterruptedException e1) {
			e1.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * Crea i executa un fil per a una simulació de proteïnes.
	 * 
	 * Aquest mètode encapsula la lògica per a crear un fitxer de resultat amb un
	 * nom únic i llançar un fil amb la simulació corresponent.
	 * 
	 * @param tipus     El tipus de simulació (1, 2, 3 o 4).
	 * @param index     L'índex del fil dins del tipus.
	 * @param directori El directori on es desaran els fitxers de resultat.
	 * @param llista    Llista de fils per a afegir el fil creat. (Es passa per
	 *                  referència)
	 */
	private void crearIExecutarFil(int tipus, int index, File directori, List<Thread> llista) {
		// Obtenir el timestamp actual
		LocalDateTime ahora = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SS");
		String anyMesDiaHoraMinutSegonCentesimes = ahora.format(formatter);

		// Crear el nom del fitxer de resultat
		String nomArxiu = directori.getAbsolutePath() + "/PROT_MT_" + tipus + "_n" + (index + 1) + "_"
				+ anyMesDiaHoraMinutSegonCentesimes + ".sim";
		File arxiu = new File(nomArxiu);

		try {
			// Crear el fitxer
			arxiu.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Crear i iniciar el fil
		Thread t = new Thread(new SimulacioMT(tipus, arxiu));
		t.start();
		llista.add(t);
	}

	/**
	 * Executa un procés extern de càlcul de proteïnes amb els paràmetres
	 * especificats.
	 * 
	 * Aquest mètode llança un procés que invoca la classe
	 * {@code es.florida.ae1.SimulacioMP}, passant el tipus de simulació com a
	 * paràmetre. La sortida del procés es redirigeix a un arxiu de resultat dins
	 * del directori especificat.
	 *
	 * @param n1                El tipus de càlcul de proteïnes (1, 2, 3 o 4).
	 * @param directoriResultat El directori on s'executarà el procés.
	 * @param arxiuResultat     L'arxiu on es redirigirà la sortida del procés.
	 * @return El procés creat si s'ha pogut iniciar amb èxit, o {@code null} en cas
	 *         d'error.
	 */
	public Process executarProcesCalculProteines(int n1, File directoriResultat, File arxiuResultat) {
		// Classe principal que s'executarà
		String classe = "es.florida.ae1.SimulacioMP";

		// Ruta de l'executable de Java
		String javaHome = System.getProperty("java.home");
		String javaBin = javaHome + File.separator + "bin" + File.separator + "java";

		// Classpath de l'aplicació
		String classpath = System.getProperty("java.class.path");
		String className = classe;

		// Comanda per a executar el procés
		List<String> command = new ArrayList<>();
		command.add(javaBin);
		command.add("-cp");
		command.add(classpath);
		command.add(className);
		command.add(String.valueOf(n1));

		// Configurar el ProcessBuilder
		ProcessBuilder builder = new ProcessBuilder(command);
		builder.directory(directoriResultat);

		try {
			// Iniciar el procés i redirigir la sortida a l'arxiu de resultat
			Process p = builder.redirectOutput(arxiuResultat).start();
			return p;
		} catch (IOException e) {
			// En cas d'error, imprimir la traça de l'excepció
			e.printStackTrace();
		}

		// Retornar null si no s'ha pogut iniciar el procés
		return null;
	}

}
