package fr.eni.clinique.test;

import javax.swing.SwingUtilities;

import fr.eni.clinique.ihm.FrameClients;

public class testIHM {

	public static void main(String[] args) {


		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				FrameClients frame = new FrameClients();
				frame.setVisible(true);
				
				FramePriseRDV rdvFrame = new FramePriseRDV();
				rdvFrame.setVisible(true);
				
			}
		});

		
		
	}

}
