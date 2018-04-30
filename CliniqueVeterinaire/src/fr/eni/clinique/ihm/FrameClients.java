package fr.eni.clinique.ihm;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class FrameClients extends JFrame {

	public FrameClients() {
		setTitle("CLients");
		setBounds(100, 100, 500, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		
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

			addComponentTo(getPanelBoutonsDuHaut(), mainPanel, 0, 0, 2, 1, 1, true);

		}
		return mainPanel;
	}

	///////////////////////////////////
	// PANEL BOUTONS DU HAUT
	///////////////////////////////////

	private JPanel panelBoutonsDuHaut;
	private JButton btnRechercherClient;
	private JButton btnAjouterClient;
	private JButton btnSupprimerClient;
	private JButton btnValiderClient;
	private JButton btnAnnulerClient;

	public JPanel getPanelBoutonsDuHaut() {
		if (panelBoutonsDuHaut == null) {
			panelBoutonsDuHaut = new JPanel(new BorderLayout());
			Box box = Box.createHorizontalBox();

			box = Box.createHorizontalBox();
			box.add(getBtnRechercherClient());
			box.add(Box.createHorizontalGlue());
			box.add(getBtnAjouterClient());
			box.add(getBtnSupprimerClient());
			box.add(Box.createGlue());
			box.add(getBtnValiderClient());
			box.add(getBtnAnnulerClient());
			panelBoutonsDuHaut.add(box);
		}
		return panelBoutonsDuHaut;
	}

	////////// GROUPE RECHERCHER //////////////////
	public JButton getBtnRechercherClient() {
		if (btnRechercherClient == null) {
			btnRechercherClient = new JButton("Rechercher",
					new ImageIcon(getClass().getResource("./resources/rechercher.png")));
			btnRechercherClient.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnRechercherClient.setHorizontalTextPosition(SwingConstants.CENTER);
		}
		return btnRechercherClient;
	}

	////////// GROUPE AJOUTER/SUPPRIMER //////////////////

	public JButton getBtnAjouterClient() {
		if (btnAjouterClient == null) {
			btnAjouterClient = new JButton("Ajouter", new ImageIcon(getClass().getResource("./resources/ajouter.png")));
			btnAjouterClient.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnAjouterClient.setHorizontalTextPosition(SwingConstants.CENTER);
		}
		return btnAjouterClient;
	}

	public JButton getBtnSupprimerClient() {
		if (btnSupprimerClient == null) {
			btnSupprimerClient = new JButton("Supprimer",
					new ImageIcon(getClass().getResource("./resources/supprimer.png")));
			btnSupprimerClient.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnSupprimerClient.setHorizontalTextPosition(SwingConstants.CENTER);
		}
		return btnSupprimerClient;
	}

	////////// GROUPE VALIDER/ ANNULER //////////////////

	public JButton getBtnValiderClient() {
		if (btnValiderClient == null) {
			btnValiderClient = new JButton("Valider", new ImageIcon(getClass().getResource("./resources/valider.png")));
			btnValiderClient.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnValiderClient.setHorizontalTextPosition(SwingConstants.CENTER);
		}
		return btnValiderClient;
	}

	public JButton getBtnAnnulerClient() {
		if (btnAnnulerClient == null) {
			btnAnnulerClient = new JButton("Annuler", new ImageIcon(getClass().getResource("./resources/annuler.png")));
			btnAnnulerClient.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnAnnulerClient.setHorizontalTextPosition(SwingConstants.CENTER);
		}
		return btnAnnulerClient;
	}

	///////////////////////////////////
	// PANEL DETAILS CLIENTS
	///////////////////////////////////

	private JPanel panelDetailsClient;

	private JLabel lblCodeClient;
	private JTextField txtCodeClient;

	private JLabel lblNomClient;
	private JTextField txtNomClient;

	private JLabel lblPrenomClient;
	private JTextField txtPrenomClient;

	private JLabel lblAdresseClient;
	private JTextArea txtAdresseClient;

	private JLabel lblCodePostalClient;
	private JTextField txtCodePostalClient;

	private JLabel lblVilleClient;
	private JTextField txtVilleClient;

	public JPanel getPanelDetailsClient() {
		if (panelBoutonsDuHaut == null) {
			panelBoutonsDuHaut = new JPanel();
			GridBagConstraints gbc = new GridBagConstraints();
			
			//addComponentTo(component, panel, x, y, width, height, weightX, fillHorizontal);
		}
		return panelDetailsClient;
	}

	public JLabel getLblCodeClient() {
		if (lblCodeClient == null) {
			lblCodeClient = new JLabel("Code");
		}
		return lblCodeClient;
	}

	public JTextField getTxtCodeClient() {
		if (txtCodeClient == null) {
			txtCodeClient = new JTextField(30);
		}
		return txtCodeClient;
	}

	public JLabel getLblNomClient() {
		if (lblNomClient == null) {
			lblNomClient = new JLabel("Nom");
		}
		return lblNomClient;
	}

	public JTextField getTxtNomClient() {
		if (txtCodeClient == null) {
			txtCodeClient = new JTextField(30);
		}
		return txtNomClient;
	}

	public JLabel getLblPrenomClient() {
		if (lblPrenomClient == null) {
			lblPrenomClient = new JLabel("Prénom");
		}
		return lblPrenomClient;
	}

	public JTextField getTxtPrenomClient() {
		if (txtPrenomClient == null) {
			txtPrenomClient = new JTextField(30);
		}
		return txtPrenomClient;
	}

	public JLabel getLblAdresseClient() {
		if (lblAdresseClient == null) {
			lblAdresseClient = new JLabel("Adresse");
		}
		return lblAdresseClient;
	}

	public JTextArea getTxtAdresseClient() {
		if (txtAdresseClient == null) {
			txtAdresseClient = new JTextArea();
		}
		return txtAdresseClient;
	}

	public JLabel getLblCodePostalClient() {
		if (lblCodePostalClient == null) {
			lblCodePostalClient = new JLabel("Code postal");
		}
		return lblCodePostalClient;
	}

	public JTextField getTxtCodePostalClient() {
		if (txtCodePostalClient == null) {
			txtCodePostalClient = new JTextField(30);
		}
		return txtCodePostalClient;
	}

	public JLabel getLblVilleClient() {
		if (lblVilleClient == null) {
			lblVilleClient = new JLabel("Ville");
		}
		return lblVilleClient;
	}

	public JTextField getTxtVilleClient() {
		if (txtVilleClient == null) {
			txtVilleClient = new JTextField(30);
		}
		return txtVilleClient;
	}

	///////////////////////////////////
	// PANEL LISTE ANIMAUX CLIENTS
	///////////////////////////////////

	////////// TABLEAU //////////////////

	////////// BOUTONS //////////////////

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

	private void addComponentTo(JComponent component, JPanel panel, int x, int y, int width, int height, double weightX,
			boolean fillHorizontal) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		gbc.weightx = weightX;
		if (fillHorizontal) {
			gbc.fill = GridBagConstraints.HORIZONTAL;
		}
		gbc.insets = new Insets(7, 10, 5, 10);
		panel.add(component, gbc);
	}

}
