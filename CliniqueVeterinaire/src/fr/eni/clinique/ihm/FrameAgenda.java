package fr.eni.clinique.ihm;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class FrameAgenda extends JFrame {

	
	
	public FrameAgenda() {
		setTitle("Agenda");
		setBounds(100, 100, 500, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		
		//applyLookAndFeel();
	}
}
