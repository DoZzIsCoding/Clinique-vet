package fr.eni.clinique.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import fr.eni.clinique.bll.Clinique;
import fr.eni.clinique.exceptions.BLLException;

@SuppressWarnings("serial")
public class FrameAccueilClinique extends JFrame {

	public FrameAccueilClinique() {
		setTitle("Clinique V�t�rinaire");
		setBounds(100, 100, 500, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setJMenuBar(getMenuPrincipal());
		setVisible(true);

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
		if (menuPrincipal == null) {
			menuPrincipal = new JMenuBar();

			try {
				switch (Clinique.getInstance().getUtilisateurConnect�().getRole()) {
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
					menuPrincipal.add(getMenuGestionRdv());
					menuPrincipal.add(getMenuAgenda());
					menuPrincipal.add(menuBtnGestionPersonnel());
					break;
				}
			} catch (BLLException e) {
			}
		}
		return menuPrincipal;
	}

	public JMenu getMenuFichier() {
		if (menuFichier == null) {
			menuFichier = new JMenu("Fichiers");
			menuFichier.add(getItemDeconnexion());
			menuFichier.add(getItemFermer());
		}
		return menuFichier;
	}

	public JMenuItem getItemDeconnexion() {
		if (itemDeconnexion == null) {
			itemDeconnexion = new JMenuItem("D�connexion");

			itemDeconnexion.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					int res = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment vous d�connecter ?",
							"Quitter l'application", JOptionPane.OK_CANCEL_OPTION);
					switch (res) {
					case JOptionPane.OK_OPTION:
						fermerFenetre();
						new FrameConnexion();
					case JOptionPane.CANCEL_OPTION:
						break;
					case JOptionPane.CLOSED_OPTION:
						break;
					}

				}
			});
		}
		return itemDeconnexion;
	}

	public JMenuItem getItemFermer() {
		if (itemFermer == null) {
			itemFermer = new JMenuItem("Fermer");

			itemFermer.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int res = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter l'application ?",
							"Quitter l'application", JOptionPane.OK_CANCEL_OPTION);

					switch (res) {
					case JOptionPane.OK_OPTION:
						fermerFenetre();
					case JOptionPane.CANCEL_OPTION:
						break;
					case JOptionPane.CLOSED_OPTION:
						break;
					}

				}
			});
		}
		return itemFermer;
	}

	public JMenu getMenuGestionRdv() {
		if (menuGestionRdv == null) {
			menuGestionRdv = new JMenu("Gestion des rendez-vous");
			menuGestionRdv.add(getItemGestionClients());
			menuGestionRdv.add(getItemPriseDeRdv());

		}
		return menuGestionRdv;
	}

	public JMenuItem getItemPriseDeRdv() {
		if (itemPriseDeRdv == null) {
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
		if (itemGestionClients == null) {
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
		if (menuAgenda == null) {
			menuAgenda = new JMenu("Agenda");

			menuAgenda.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					new FrameAgenda();
					
				}
			});
		}
		return menuAgenda;
	}

	public JMenu menuBtnGestionPersonnel() {
		if (menuGestionPersonnel == null) {
			menuGestionPersonnel = new JMenu("Gestion du personnel");

			menuGestionPersonnel.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					new FrameGestionDuPersonnel();
					
				}
			});
		}
		return menuGestionPersonnel;
	}

	//////////////////////////////////
	// METHODES
	//////////////////////////////////

	protected void fermerFenetre() {
		this.dispose();
	}

}
