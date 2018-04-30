package fr.eni.clinique.ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public class FrameRechercheClient extends JFrame {

	public FrameRechercheClient() {
		setTitle("Résultat de la recherche client");
		setBounds(100, 100, 400, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		setContentPane(getMainPanel());
		applyLookAndFeel();

	}

	////////////////////////////////
	// MAIN PANEL
	////////////////////////////////

	private JPanel mainPanel;

	public JPanel getMainPanel() {
		if (mainPanel == null) {
			mainPanel = new JPanel();
			mainPanel.setLayout(new BorderLayout());

			mainPanel.add(getPanelZoneDeRecherche(), BorderLayout.PAGE_START);
			mainPanel.add(getTxtResultatsRecherche(), BorderLayout.LINE_START);
		}
		return mainPanel;
	}

	////////////////////////////////
	// ZONE DE RECHERCHE PANEL
	////////////////////////////////

	private JPanel panelZoneDeRecherche;
	private JTextField txtZoneDeRecherche;
	private JButton btnRechercherClient;
	
	
	
	public JPanel getPanelZoneDeRecherche() {
		if(panelZoneDeRecherche == null){
			panelZoneDeRecherche = new JPanel(new FlowLayout());
			
			Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
			panelZoneDeRecherche.setBorder(border);
			panelZoneDeRecherche.add(getTxtZoneDeRecherche());
			panelZoneDeRecherche.add(getBtnRechercherClient());
		}
		return panelZoneDeRecherche;
	}

	public JTextField getTxtZoneDeRecherche() {
		if(txtZoneDeRecherche == null){
			txtZoneDeRecherche = new JTextField(20);
		}
		return txtZoneDeRecherche;
	}

	public JButton getBtnRechercherClient() {
		if (btnRechercherClient == null) {
			btnRechercherClient = new JButton("Rechercher",
					new ImageIcon(getClass().getResource("./resources/rechercher.png")));
			btnRechercherClient.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnRechercherClient.setHorizontalTextPosition(SwingConstants.CENTER);
		}
		return btnRechercherClient;
	}
	
	
	
	
	////////////////////////////////
	// TEXTFIELD PANEL
	////////////////////////////////

	private JTextArea txtResultatsRecherche;
	
	
	
	
	public JTextArea getTxtResultatsRecherche() {
		if(txtResultatsRecherche == null){
			txtResultatsRecherche = new JTextArea();
		}
		return txtResultatsRecherche;
	}

	///////////////////////////////////
	// METHODES
	///////////////////////////////////
	private void applyLookAndFeel() {
		String look = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
		// look = "javax.swing.plaf.metal.MetalLookAndFeel";

		try {
			UIManager.setLookAndFeel(look);
			SwingUtilities.updateComponentTreeUI(this.getContentPane());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
