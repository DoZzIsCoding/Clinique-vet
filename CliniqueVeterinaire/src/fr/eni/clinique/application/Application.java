package fr.eni.clinique.application;

import javax.swing.SwingUtilities;

import fr.eni.clinique.ihm.FrameConnexion;

public class Application {

	public static void main(String[] args) {


		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				
				FrameConnexion connexionframe = new FrameConnexion();
				connexionframe.setVisible(true);
				
			}
		});

		
		
	}

}
