
package fr.eni.clinique.test;

import javax.swing.SwingUtilities;

import fr.eni.clinique.ihm.FrameClients;

public class testFrameClients {

	public static void main(String[] args) {
SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				FrameClients clientsFrame = new FrameClients();
				clientsFrame.setVisible(true);
			}
		});

	}

}
