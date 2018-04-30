package fr.eni.clinique.test;

import javax.swing.SwingUtilities;

import fr.eni.clinique.ihm.FrameAccueilClinique;

public class testFrameAccueil {

	public static void main(String[] args) {


		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				
				FrameAccueilClinique accueilCliniqueframe = new FrameAccueilClinique();
				accueilCliniqueframe.setVisible(true);
				
			}
		});

		
		
	}

}
