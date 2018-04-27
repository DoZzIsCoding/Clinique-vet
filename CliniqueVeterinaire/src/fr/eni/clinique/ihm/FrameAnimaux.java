package fr.eni.clinique.ihm;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class FrameAnimaux extends JFrame {

	public FrameAnimaux() {
		setTitle("Animaux");
		setBounds(100, 100, 400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setContentPane(getMainPanel());
	}

	////////////////////////////////
	// MAIN PANEL
	////////////////////////////////

	private JPanel mainPanel;
	
	public JPanel getMainPanel() {
		if (mainPanel == null) {
		mainPanel = new JPanel();
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(getBoutonsPanel());
//		mainPanel.add(getNomClientPanel());
//		mainPanel.add(getInfosAnimalPanel());
		
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
		if (boutonsPanel == null){
			boutonsPanel = new JPanel();
			boutonsPanel.setLayout(new FlowLayout());
			Border border = BorderFactory.createLoweredBevelBorder();

			boutonsPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			
			boutonsPanel.setBorder(border);
			boutonsPanel.add(getBtnValider());
			boutonsPanel.add(getBtnAnnuler());
		}
		return boutonsPanel;
	}
	public JButton getBtnValider() {
		if(btnValider == null){
			btnValider = new JButton("Valider");
					//new ImageIcon(getClass().getResource("./resources/valider.png")));
		}
		return btnValider;
	}
	public JButton getBtnAnnuler() {
		if(btnAnnuler == null){
			btnAnnuler = new JButton("Annuler");
					//new ImageIcon(getClass().getResource("./resources/annuler.png")));
		}
		return btnAnnuler;
	}
	
	
	
	

	////////////////////////////////
	// NOM CLIENT PANEL
	////////////////////////////////

	

	////////////////////////////////
	// INFOS ANIMAL PANEL
	////////////////////////////////

	

}
