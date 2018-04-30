package fr.eni.clinique.ihm;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.Clinique;

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
			
			try {
				switch (Clinique.getInstance().getUtilisateurConnecté().getRole()) {
				case "vet":
					menuPrincipal.add(getMenuFichier());
					menuPrincipal.add(getMenuAgenda());
					break;
				case "sec":
					menuPrincipal.add(getMenuFichier());
					menuPrincipal.add(getMenuGestionRdv());
					break;
				case "adm":
					menuPrincipal.add(getMenuFichier());
					menuPrincipal.add(getMenuAgenda());
					menuPrincipal.add(getMenuGestionRdv());
					menuPrincipal.add(menuBtnGestionPersonnel());
					break;
				}
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			
			itemDeconnexion.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					fermerFenetre();
					new FrameConnexion();
					
				}
			});
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
			
			itemPriseDeRdv.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					new FramePriseRDV();
					
				}
			});
		}
		return itemPriseDeRdv;
	}
	public JMenuItem getItemGestionClients() {
		if(itemGestionClients == null){
			itemGestionClients = new JMenuItem("Gestion des clients");
			
			itemGestionClients.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					new FrameClients();
					
				}
			});
		}
		return itemGestionClients;
	}
	public JMenu getMenuAgenda() {
		if(menuAgenda == null){
			menuAgenda = new JMenu("Agenda");
			
			menuAgenda.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					new FrameAgenda();
					
				}
			});
		}
		return menuAgenda;
	}
	
	public JMenu menuBtnGestionPersonnel() {
		if(menuGestionPersonnel == null){
			menuGestionPersonnel = new JMenu("Gestion du personnel");
			
			menuGestionPersonnel.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					new FrameGestionDuPersonnel();
					
				}
			});
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
	
	protected void fermerFenetre() {
		this.dispose();
		
	}
	
	protected void quitterProgramme() {
		
		
	}
	
}
