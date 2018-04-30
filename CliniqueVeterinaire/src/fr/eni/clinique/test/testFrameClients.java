package fr.eni.clinique.test;

import javax.swing.SwingUtilities;

import fr.eni.clinique.ihm.FrameRechercheClient;

public class testFrameClients {

	public static void main(String[] args) {
SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				FrameRechercheClient clientsFrame = new FrameRechercheClient();
				clientsFrame.setVisible(true);
			}
		});

	}

}
