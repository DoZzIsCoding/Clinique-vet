package fr.eni.clinique.ihm;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.Clinique;

@SuppressWarnings("serial")
public class FrameGestionDuPersonnel extends JFrame {

	public FrameGestionDuPersonnel() {
		setTitle("Gestion du personnel");
		setBounds(100, 100, 600, 480);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setContentPane(getMainPanel());

//		applyLookAndFeel();
	}

	private JPanel mainPanel;

	public JPanel getMainPanel() {
		if (mainPanel == null) {
			mainPanel = new JPanel(new BorderLayout());

			mainPanel.add(getPanelBoutonsDuHautGestionPerso(), BorderLayout.NORTH);
			mainPanel.add(getTxtListePersonnel(), BorderLayout.CENTER);

		}

		return mainPanel;
	}

	///////////////////////////////////
	// PANEL BOUTONS DU HAUT
	///////////////////////////////////

	private JPanel panelBoutonsDuHautGestionPerso;
	private JButton btnAjouterPersonnel;
	private JButton btnSupprimerPersonnel;
	private JButton btnReinitialiserPersonnel;

	public JPanel getPanelBoutonsDuHautGestionPerso() {
		if (panelBoutonsDuHautGestionPerso == null) {
			panelBoutonsDuHautGestionPerso = new JPanel(new FlowLayout());

			panelBoutonsDuHautGestionPerso.setLayout(new FlowLayout(FlowLayout.LEFT));
			Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

			panelBoutonsDuHautGestionPerso.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

			panelBoutonsDuHautGestionPerso.setBorder(border);

			panelBoutonsDuHautGestionPerso.add(getBtnAjouterPersonnel());
			panelBoutonsDuHautGestionPerso.add(getBtnSupprimerPersonnel());
			panelBoutonsDuHautGestionPerso.add(getBtnReinitialiserPersonnel());
		}

		return panelBoutonsDuHautGestionPerso;
	}

	////////// GROUPE AJOUTER/SUPPRIMER //////////////////

	public JButton getBtnAjouterPersonnel() {
		if (btnAjouterPersonnel == null) {
			btnAjouterPersonnel = new JButton("Ajouter",
					new ImageIcon(getClass().getResource("./resources/ajouter.png")));
			btnAjouterPersonnel.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnAjouterPersonnel.setHorizontalTextPosition(SwingConstants.CENTER);

		}
		return btnAjouterPersonnel;
	}

	public JButton getBtnSupprimerPersonnel() {
		if (btnSupprimerPersonnel == null) {
			btnSupprimerPersonnel = new JButton("Supprimer",
					new ImageIcon(getClass().getResource("./resources/supprimer.png")));
			btnSupprimerPersonnel.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnSupprimerPersonnel.setHorizontalTextPosition(SwingConstants.CENTER);
		}
		return btnSupprimerPersonnel;
	}

	public JButton getBtnReinitialiserPersonnel() {
		if (btnReinitialiserPersonnel == null) {
			btnReinitialiserPersonnel = new JButton("Réinitialiser",
					new ImageIcon(getClass().getResource("./resources/reinitialiser.png")));
			btnReinitialiserPersonnel.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnReinitialiserPersonnel.setHorizontalTextPosition(SwingConstants.CENTER);
		}
		return btnReinitialiserPersonnel;
	}

	////////////////////////////////
	// TEXTFIELD PANEL
	////////////////////////////////

	private JList<String> txtListePersonnel;
	private JScrollPane scrTxtAreaListePersonnel;

	public JList<String> getTxtListePersonnel() {
		if (txtListePersonnel == null) {
			try {
				txtListePersonnel = new JList<String>(Clinique.getInstance().getTabPersonnel());
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//txtListePersonnel.setEditable(false);
			scrTxtAreaListePersonnel = new JScrollPane(txtListePersonnel);
		}
		return txtListePersonnel;
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
