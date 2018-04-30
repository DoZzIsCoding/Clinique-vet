package fr.eni.clinique.test;

import javax.swing.SwingUtilities;

import fr.eni.clinique.ihm.FrameAccueilClinique;
import fr.eni.clinique.ihm.FrameAnimaux;
import fr.eni.clinique.ihm.FrameConnexion;
import fr.eni.clinique.ihm.FramePriseRDV;

public class testIHM {

	public static void main(String[] args) {


		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				FrameAnimaux frame = new FrameAnimaux();
				frame.setVisible(false);
				
				FramePriseRDV rdvFrame = new FramePriseRDV();
				rdvFrame.setVisible(false);
				
				FrameConnexion connexionFrame = new FrameConnexion();
				connexionFrame.setVisible(true);
				
				
				
				
			}
		});

		
		
	}

}
