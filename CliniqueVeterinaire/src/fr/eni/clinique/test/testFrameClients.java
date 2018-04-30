package fr.eni.clinique.test;

import javax.swing.SwingUtilities;

import fr.eni.clinique.ihm.FrameAjouterClient;

public class testFrameClients {

	public static void main(String[] args) {
SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				FrameAjouterClient clientsFrame = new FrameAjouterClient();
				clientsFrame.setVisible(true);
			}
		});

	}

}
