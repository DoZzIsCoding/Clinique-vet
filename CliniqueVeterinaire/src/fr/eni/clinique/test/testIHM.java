package fr.eni.clinique.test;

import javax.swing.SwingUtilities;

import fr.eni.clinique.ihm.FramePriseRDV;

public class testIHM {

	public static void main(String[] args) {


		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				FramePriseRDV frame = new FramePriseRDV();
				frame.setVisible(true);
				
			}
		});

	}

}
