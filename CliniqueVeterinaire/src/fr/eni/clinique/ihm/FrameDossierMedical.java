package fr.eni.clinique.ihm;

import java.awt.BorderLayout;
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
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class FrameDossierMedical extends JFrame {

	public FrameDossierMedical() {
		setTitle("Agenda");
		setBounds(100, 100, 800, 450);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setContentPane(getMainPanel());
		//setVisible(true);
	}

	private JPanel mainPanel;

	public JPanel getMainPanel() {
		if (mainPanel == null) {
			mainPanel = new JPanel(new BorderLayout());

			mainPanel.add(getBoutonsPanel(), BorderLayout.NORTH);
			mainPanel.add(getPanelInformations(), BorderLayout.LINE_START);
			mainPanel.add(getPanelAntecedents(), BorderLayout.CENTER);

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

	////////////////////////////////
	// INFORMATION PANEL
	////////////////////////////////

	private JPanel panelInformations;
	
	private JPanel panelNomClient;
	private JLabel lblNomPrenomClient;
	
	private JLabel lblAnimal;
	private JLabel lblCodeAnimal;
	private JLabel lblNomAnimal;
	private JLabel lblCouleurAnimal;
	private JLabel lblSexeAnimal;
	private JLabel lblRaceanimal;
	private JLabel lblTatouage;
	
	
	public JPanel getPanelInformations() {
		if(panelInformations == null){
			panelInformations = new JPanel(new GridBagLayout());
			
			addComponentTo(getPanelNomClient(), panelInformations, 0, 0, 3, 1, 1, true);
			addComponentTo(getLblAnimal(), panelInformations, 0, 1, 1, 1, 1, true);
			addComponentTo(getLblCodeAnimal(), panelInformations, 1, 1, 1, 1, 1, true);
			addComponentTo(getLblNomAnimal(), panelInformations, 1, 2, 1, 1, 1, true);
			addComponentTo(getLblCouleurAnimal(), panelInformations, 1, 3, 1, 1, 1, true);
			addComponentTo(getLblSexeAnimal(), panelInformations, 2, 3, 1, 1, 1, true);
			addComponentTo(getLblRaceanimal(), panelInformations, 1, 4, 1, 1, 1, true);
			addComponentTo(getLblTatouage(), panelInformations, 1, 5, 1, 1, 1, true);
		}
		return panelInformations;
	}

	public JPanel getPanelNomClient() {
		if(panelNomClient == null){
			panelNomClient = new JPanel();
			TitledBorder border = new TitledBorder("Client");
			panelNomClient.setBorder(border);
			
			panelNomClient.add(getLblNomPrenomClient());		
		}
		return panelNomClient;
	}

	public JLabel getLblNomPrenomClient() {
		if(lblNomPrenomClient == null){
			lblNomPrenomClient = new JLabel("NomPrenomClient");
		}
		return lblNomPrenomClient;
	}

	public JLabel getLblAnimal() {
		if(lblAnimal == null){
			lblAnimal = new JLabel("lblAnimal");
		}
		return lblAnimal;
	}

	public JLabel getLblCodeAnimal() {
		if(lblCodeAnimal == null){
			lblCodeAnimal = new JLabel("lblCodeAnimal");
		}
		return lblCodeAnimal;
	}

	public JLabel getLblNomAnimal() {
		if(lblNomAnimal == null){
			lblNomAnimal = new JLabel("lblNomAnimal");
		}
		return lblNomAnimal;
	}

	public JLabel getLblCouleurAnimal() {
		if(lblCouleurAnimal == null){
			lblCouleurAnimal = new JLabel("lblCouleurAnimal");
		}
		return lblCouleurAnimal;
	}

	public JLabel getLblSexeAnimal() {
		if(lblSexeAnimal == null){
			lblSexeAnimal = new JLabel("lblSexeAnimal");
		}
		return lblSexeAnimal;
	}

	public JLabel getLblRaceanimal() {
		if(lblRaceanimal == null){
			lblRaceanimal = new JLabel("lblRaceanimal");
		}
		return lblRaceanimal;
	}

	public JLabel getLblTatouage() {
		if(lblTatouage == null){
			lblTatouage = new JLabel("lblTatouage");
		}
		return lblTatouage;
	}

///////////////////////////////////
// ANTECEDENTS PANEL
///////////////////////////////////
	
	private JPanel panelAntecedents;
	private JTextArea txtAntecedents;
	
	public JPanel getPanelAntecedents() {
		if(panelAntecedents == null){
			panelAntecedents = new JPanel();
			TitledBorder border = new TitledBorder("Antécédents/consultations");
			panelAntecedents.setBorder(border);
			panelAntecedents.add(getTxtAntecedents());
		}
		return panelAntecedents;
	}

	public JTextArea getTxtAntecedents() {
		if(txtAntecedents == null){
			txtAntecedents = new JTextArea(17,42);
		}
		return txtAntecedents;
	}

	///////////////////////////////////
	// METHODES
	///////////////////////////////////

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
