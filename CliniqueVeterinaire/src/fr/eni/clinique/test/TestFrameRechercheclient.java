package fr.eni.clinique.test;

import javax.swing.SwingUtilities;

import fr.eni.clinique.ihm.FrameGestionDuPersonnel;

public class TestFrameRechercheclient {

	public static void main(String[] args) {
SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				FrameGestionDuPersonnel rechercherFrame = new FrameGestionDuPersonnel();
				rechercherFrame.setVisible(true);
			}
		});

	}

}
