package fr.eni.clinique.ihm;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public class FrameAjouterClient extends JFrame {

	public FrameAjouterClient() {
		setTitle("Ajouter clients");
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

			mainPanel.add(getBoutonsPanel(), BorderLayout.PAGE_START);
			mainPanel.add(getPanelDetailsClient(), BorderLayout.LINE_START);
		}
		return mainPanel;
	}

	////////////////////////////////
	// BOUTONS PANEL
	////////////////////////////////

	private JPanel boutonsPanel;
	private JButton btnValider;
	private JButton btnAnnuler;

	public JPanel getBoutonsPanel() {
		if (boutonsPanel == null) {
			boutonsPanel = new JPanel();
			boutonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

			boutonsPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

			boutonsPanel.setBorder(border);

			boutonsPanel.add(getBtnAnnuler());
			boutonsPanel.add(getBtnValider());
		}
		return boutonsPanel;
	}

	public JButton getBtnValider() {
		if (btnValider == null) {
			btnValider = new JButton("Valider", new ImageIcon(getClass().getResource("./resources/valider.png")));
			btnValider.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnValider.setHorizontalTextPosition(SwingConstants.CENTER);

			// btnValider.addActionListener(new ActionListener() {
			//
			// @Override
			// public void actionPerformed(ActionEvent e) {
			// Animal animal;
			//
			// try {
			// animal = new Animal(
			// (Clinique.getInstance().getIndexAnimalEnCours() == -1 ? -1
			// : Clinique.getInstance().getAnimalEnCours().getCodeAnimal()),
			// getTxtNomAnimal().getText(),
			// getCbbSexeAnimal().getSelectedItem().toString().charAt(0),
			// getTxtCouleurAnimal().getText(), (String)
			// getCbbRaceAnimal().getSelectedItem(),
			// (String) getCbbEspeceAnimal().getSelectedItem(),
			// (Integer)
			// Clinique.getInstance().getClientEnCours().getCodeClient(),
			// getTxtTatouage().getText(),
			// (Clinique.getInstance().getIndexAnimalEnCours() == -1
			// ? new String() :
			// Clinique.getInstance().getAnimalEnCours().getAntecedents()));
			// Clinique.getInstance().ajouterAnimal(animal);
			// } catch (BLLException e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// }
			//
			// }
			// });

		}
		return btnValider;
	}

	public JButton getBtnAnnuler() {
		if (btnAnnuler == null) {
			btnAnnuler = new JButton("Annuler", new ImageIcon(getClass().getResource("./resources/annuler.png")));
			btnAnnuler.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnAnnuler.setHorizontalTextPosition(SwingConstants.CENTER);
		}
		return btnAnnuler;
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
	private JTextField txtAdresseClient;
	private JTextField txtAdresseClient2;

	private JLabel lblCodePostalClient;
	private JTextField txtCodePostalClient;

	private JLabel lblVilleClient;
	private JTextField txtVilleClient;

	public JPanel getPanelDetailsClient() {
		if (panelDetailsClient == null) {
			panelDetailsClient = new JPanel(new GridBagLayout());

			addComponentTo(getLblCodeClient(), panelDetailsClient, 0, 0, 1, 1, 1, true);
			addComponentTo(getTxtCodeClient(), panelDetailsClient, 1, 0, 1, 1, 1, true);
			addComponentTo(getLblNomClient(), panelDetailsClient, 0, 1, 1, 1, 1, true);
			addComponentTo(getTxtNomClient(), panelDetailsClient, 1, 1, 1, 1, 1, true);
			addComponentTo(getLblPrenomClient(), panelDetailsClient, 0, 2, 1, 1, 1, true);
			addComponentTo(getTxtPrenomClient(), panelDetailsClient, 1, 2, 1, 1, 1, true);
			addComponentTo(getLblAdresseClient(), panelDetailsClient, 0, 3, 1, 1, 1, true);
			addComponentTo(getTxtAdresseClient(), panelDetailsClient, 1, 3, 1, 1, 1, true);
			addComponentTo(getTxtAdresseClient2(), panelDetailsClient, 1, 4, 1, 1, 1, true);
			addComponentTo(getLblCodePostalClient(), panelDetailsClient, 0, 5, 1, 1, 1, true);
			addComponentTo(getTxtCodePostalClient(), panelDetailsClient, 1, 5, 1, 1, 1, true);
			addComponentTo(getLblVilleClient(), panelDetailsClient, 0, 6, 1, 1, 1, true);
			addComponentTo(getTxtVilleClient(), panelDetailsClient, 1, 6, 1, 1, 1, true);

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
			txtCodeClient = new JTextField(20);
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
		if (txtNomClient == null) {
			txtNomClient = new JTextField(20);
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
			txtPrenomClient = new JTextField(20);
		}
		return txtPrenomClient;
	}

	public JLabel getLblAdresseClient() {
		if (lblAdresseClient == null) {
			lblAdresseClient = new JLabel("Adresse");

		}
		return lblAdresseClient;
	}

	public JTextField getTxtAdresseClient() {
		if (txtAdresseClient == null) {
			txtAdresseClient = new JTextField(20);
		}
		return txtAdresseClient;
	}

	public JTextField getTxtAdresseClient2() {
		if (txtAdresseClient2 == null) {
			txtAdresseClient2 = new JTextField(20);
		}
		return txtAdresseClient2;
	}

	public JLabel getLblCodePostalClient() {
		if (lblCodePostalClient == null) {
			lblCodePostalClient = new JLabel("Code postal");
		}
		return lblCodePostalClient;
	}

	public JTextField getTxtCodePostalClient() {
		if (txtCodePostalClient == null) {
			txtCodePostalClient = new JTextField(20);
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
			txtVilleClient = new JTextField(20);
		}
		return txtVilleClient;
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
