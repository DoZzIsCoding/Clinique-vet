package fr.eni.clinique.ihm;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class FrameAccueilClinique extends JFrame {

	public FrameAccueilClinique() {
		setTitle("Clinique Vétérinaire");
		setBounds(100, 100, 500, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setJMenuBar(getMenuPrincipal());
		
		applyLookAndFeel();
	}

	
	///////////////////////////////////
	// MENUS
	///////////////////////////////////
	
	private JMenuBar menuPrincipal;
	private JMenu menuFichier;
	private JMenuItem itemDeconnexion;
	private JMenuItem itemFermer;
	private JMenu menuGestionRdv;
	private JMenuItem itemPriseDeRdv;
	private JMenuItem itemGestionClients;
	private JMenu menuAgenda;
	private JMenu menuGestionPersonnel;
	
	
	public JMenuBar getMenuPrincipal() {
		if (menuPrincipal == null){
			menuPrincipal = new JMenuBar();
			menuPrincipal.add(getMenuFichier());
			menuPrincipal.add(getMenuGestionRdv());
			menuPrincipal.add(getMenuAgenda());
			menuPrincipal.add(menuBtnGestionPersonnel());
			//menuPrincipal.set
			
		}
		return menuPrincipal;
	}
	public JMenu getMenuFichier() {
		if(menuFichier == null){
			menuFichier = new JMenu("Fichiers");
			menuFichier.add(getItemDeconnexion());
			menuFichier.add(getItemFermer());
		}
		return menuFichier;
	}
	
	public JMenuItem getItemDeconnexion() {
		if(itemDeconnexion == null){
			itemDeconnexion = new JMenuItem("Déconnexion");
		}
		return itemDeconnexion;
	}
	public JMenuItem getItemFermer() {
		if(itemFermer == null){
			itemFermer = new JMenuItem("Fermer");
		}
		return itemFermer;
	}
	public JMenu getMenuGestionRdv() {
		if(menuGestionRdv == null){
			menuGestionRdv = new JMenu("Gestion des rendez-vous");
			menuGestionRdv.add(getItemGestionClients());
			menuGestionRdv.add(getItemPriseDeRdv());
		}
		return menuGestionRdv;
	}
	
	public JMenuItem getItemPriseDeRdv() {
		if(itemPriseDeRdv == null){
			itemPriseDeRdv = new JMenuItem("Prise de rendez-vous");
		}
		return itemPriseDeRdv;
	}
	public JMenuItem getItemGestionClients() {
		if(itemGestionClients == null){
			itemGestionClients = new JMenuItem("Gestion des clients");
		}
		return itemGestionClients;
	}
	public JMenu getMenuAgenda() {
		if(menuAgenda == null){
			menuAgenda = new JMenu("Agenda");
		}
		return menuAgenda;
	}
	
	public JMenu menuBtnGestionPersonnel() {
		if(menuGestionPersonnel == null){
			menuGestionPersonnel = new JMenu("Gestion du personnel");
		}
		return menuGestionPersonnel;
	}

	//////////////////////////////////
	// METHODES
	//////////////////////////////////

	private void applyLookAndFeel() {
		String look = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
		//look = "javax.swing.plaf.metal.MetalLookAndFeel";
		
		try {
			UIManager.setLookAndFeel(look);
			SwingUtilities.updateComponentTreeUI(this.getContentPane());
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}

	private void addComponentTo(JComponent component, JPanel panel, int x, int y, int width, int height, double weightX,
			boolean fillHorizontal) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		gbc.weightx = weightX;
		if (fillHorizontal) {
			gbc.fill = GridBagConstraints.HORIZONTAL;
		}
		gbc.insets = new Insets(7, 10, 5, 10);
		panel.add(component, gbc);
	}
}
