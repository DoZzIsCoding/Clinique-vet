package fr.eni.clinique.ihm;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class FramePriseRDV extends JFrame {

	private JPanel mainPanel;

	// PANEL POUR
	private JPanel pourPanel;
	private JLabel lblClient;
	private JComboBox<String> cbbClient;
	private JButton btnAjouterClient;
	private JLabel lblAnimal;
	private JComboBox<String> cbbAnimal;
	private JButton btnAjouterAnimal;

	// PANEL PAR
	private JPanel parPanel;
	private JLabel lblVeterinaire;
	private JComboBox<String> cbbVeterinaire;

	// PANEL QUAND
	private JPanel quandPanel;
	private JLabel lblDate;
	private UtilDateModel model;
	private JLabel lblHeure;
	private JComboBox<Integer> cbbHeure;
	private JLabel lblH;
	private JComboBox<Integer> cbbMinute;

	// PANEL LISTE RDV
	private JScrollPane tablePanel;
	private JTable tableArticle;

	// BOUTONS
	private JButton btnSupprimer;
	private JButton btnValider;

	// CONSTRUCTEUR
	public FramePriseRDV() {
		setTitle("Prise de rendez-vous");
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setContentPane(getMainPanel());
	}

	///////////////////////////////////
	// Interface graphique
	//////////////////////////////////
	public JPanel getMainPanel() {
		if (mainPanel == null) {
			mainPanel = new JPanel(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			mainPanel.add(getPourPanel(),gbc);
			gbc.gridx = 1;
			mainPanel.add(getParPanel(),gbc);
			gbc.gridx = 2;
			mainPanel.add(getQuandPanel(),gbc);
		}

		return mainPanel;
	}

	////////////////////////////////////
	// GETTERS AND SETTERS
	////////////////////////////////////

	public JPanel getPourPanel() {
		// ajouter le titledborder
		if (pourPanel == null) {
			pourPanel = new JPanel();
			pourPanel.setLayout(new BoxLayout(pourPanel, BoxLayout.X_AXIS));
			pourPanel.add(getLblClient());
			pourPanel.add(getCbbClient());
			pourPanel.add(getLblAnimal());
			pourPanel.add(getCbbAnimal());
		}
		
		
		return pourPanel;
	}

	public JLabel getLblClient() {
		if (lblClient == null) {
			lblClient = new JLabel("Client");
			lblClient.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return lblClient;
	}

	public JComboBox<String> getCbbClient() {
		if (cbbClient == null) {
			String[] clients = { "guiton", "menerville" };
			// TODO: connecter au Manager
			cbbClient = new JComboBox<String>(clients);
		}
		return cbbClient;
	}

	public JButton getBtnAjouterClient() {
		if (btnAjouterClient == null) {
			btnAjouterClient = new JButton("+");
		}
		return btnAjouterClient;
	}

	public JLabel getLblAnimal() {
		if (lblAnimal == null) {
			lblAnimal = new JLabel("Animal");
		}
		return lblAnimal;
	}

	public JComboBox<String> getCbbAnimal() {
		if (cbbAnimal == null) {
			String[] animaux = { "CHIEN", "CHAT" };
			// TODO: connecter au Manager
			cbbAnimal = new JComboBox<String>(animaux);
		}
		return cbbAnimal;
	}

	public JButton getBtnAjouterAnimal() {
		if (btnAjouterAnimal == null) {
			btnAjouterAnimal = new JButton("+");
		}
		return btnAjouterAnimal;
	}

	public JPanel getParPanel() {
		// ajouter le titledborder
		if (parPanel == null) {
			parPanel = new JPanel();
			parPanel.setLayout(new BoxLayout(pourPanel, BoxLayout.X_AXIS));
			
			
			
		}
		return parPanel;
	}

	public JLabel getLblVeterinaire() {
		if (lblVeterinaire == null) {
			lblVeterinaire = new JLabel("Vétérinaire");
		}
		return lblVeterinaire;
	}

	public JComboBox<String> getCbbVeterinaire() {
		if (cbbVeterinaire == null) {
			String[] veterinaires = { "Dupont", "Glandu" };
			// TODO: connecter au Mangaer
			cbbVeterinaire = new JComboBox<String>(veterinaires);
		}
		return cbbVeterinaire;
	}

	public JPanel getQuandPanel() {
		// ajouter le titledborder
		if (quandPanel == null) {
			quandPanel = new JPanel();
		}
		return quandPanel;
	}

	public JLabel getLblDate() {
		if (lblDate == null) {
			lblDate = new JLabel("Date");
			// TODO: à formater?
		}
		return lblDate;
	}

	public UtilDateModel getModel() {
		if (model == null) {
			model = new UtilDateModel();
			JDatePanelImpl datePanel = new JDatePanelImpl(model, null);
			JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, null);
			// TODO: revoir les parametres de dates null
			model.setDate(2018, 3, 24);
			// TODO: format de la date auto en date du jour
		}
		return model;
	}

	public JLabel getLblHeure() {
		if (lblHeure == null) {
			lblHeure = new JLabel("Heure");
		}
		return lblHeure;
	}

	public JComboBox<Integer> getCbbHeure() {
		if (cbbHeure == null) {
			Integer[] heures = { 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 };
			// TODO: connecter au Mangaer
			cbbHeure = new JComboBox<Integer>(heures);
		}
		return cbbHeure;
	}

	public JLabel getLblH() {
		if (lblH == null) {
			lblH = new JLabel("h");
		}
		return lblH;
	}

	public JComboBox<Integer> getCbbMinute() {
		if (cbbMinute == null) {
			Integer[] minutes = { 0, 15, 30, 45 };
			cbbMinute = new JComboBox<Integer>(minutes);
		}
		return cbbMinute;
	}

	public JScrollPane getTablePanel() {
		if (tablePanel == null) {
			tablePanel = new JScrollPane();
		}
		return tablePanel;
	}

	public JTable getTableArticle() {
		if (tableArticle == null) {
			tableArticle = new JTable();
		}
		return tableArticle;
	}

	public JButton getBtnSupprimer() {
		if(btnSupprimer == null){
			btnSupprimer = new JButton("Supprimer");
					
		}
		return btnSupprimer;
	}

	public JButton getBtnValider() {
		if(btnValider == null){
			btnValider = new JButton("Valider");
					
		}
		return btnValider;
	}

}
