package fr.eni.clinique.test;

import javax.swing.SwingUtilities;

import fr.eni.clinique.ihm.FrameGestionDuPersonnel;
import fr.eni.clinique.ihm.FrameRechercheClient;

public class TestFrameRechercheclient {

	public static void main(String[] args) {
SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				FrameRechercheClient rechercherFrame = new FrameRechercheClient();
				rechercherFrame.setVisible(true);
			}
		});

	}

}
