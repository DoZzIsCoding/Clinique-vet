package fr.eni.clinique.test;

import javax.swing.SwingUtilities;

import fr.eni.clinique.ihm.FrameAnimaux;
import fr.eni.clinique.ihm.FrameClients;
import fr.eni.clinique.ihm.FrameConnexion;
import fr.eni.clinique.ihm.FramePriseRDV;

public class testFrameAnimaux {

	public static void main(String[] args) {


		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				
				FrameAnimaux animauxFrame = new FrameAnimaux();
				animauxFrame.setVisible(true);
				
				
				
				
			}
		});

		
		
	}

}
