package fr.eni.clinique.test;

import javax.swing.SwingUtilities;

import fr.eni.clinique.ihm.FrameAnimaux;
import fr.eni.clinique.ihm.FrameClients;
import fr.eni.clinique.ihm.FrameConnexion;
import fr.eni.clinique.ihm.FramePriseRDV;

public class testIHM {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				FrameConnexion connexionFrame = new FrameConnexion();
				connexionFrame.setVisible(true);
				
				FrameAnimaux frame = new FrameAnimaux();
				frame.setVisible(false);

				FramePriseRDV rdvFrame = new FramePriseRDV();
				rdvFrame.setVisible(false);


				FrameAnimaux animauxFrame = new FrameAnimaux();
				animauxFrame.setVisible(false);
				FrameClients clientsFrame = new FrameClients();
				clientsFrame.setVisible(false);

			}
		});

	}

}
