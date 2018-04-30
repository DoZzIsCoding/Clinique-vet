package fr.eni.clinique.ihm;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class FrameGestionDuPersonnel extends JFrame {

	
	public FrameGestionDuPersonnel() {
		setTitle("Gestion du personnel");
		setBounds(100, 100, 500, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		
		//applyLookAndFeel();
	}
}
