package fr.eni.clinique.ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.Clinique;

@SuppressWarnings("serial")
public class FrameRechercheClient extends JFrame {

	public FrameRechercheClient() {
		setTitle("Résultat de la recherche client");
		setBounds(100, 100, 600, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setContentPane(getMainPanel());
		setVisible(true);

	}

	////////////////////////////////
	// MAIN PANEL
	////////////////////////////////

	private JPanel mainPanel;

	public JPanel getMainPanel() {
		if (mainPanel == null) {
			mainPanel = new JPanel();
			mainPanel.setLayout(new BorderLayout());

			mainPanel.add(getPanelZoneDeRecherche(), BorderLayout.PAGE_START);
			mainPanel.add(getTxtResultatsRecherche(), BorderLayout.LINE_START);
		}
		return mainPanel;
	}

	////////////////////////////////
	// ZONE DE RECHERCHE PANEL
	////////////////////////////////

	private JPanel panelZoneDeRecherche;
	private JTextField txtZoneDeRecherche;
	private JButton btnRechercherClient;
	
	
	
	public JPanel getPanelZoneDeRecherche() {
		if(panelZoneDeRecherche == null){
			panelZoneDeRecherche = new JPanel(new FlowLayout());
			
			Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
			panelZoneDeRecherche.setBorder(border);
			panelZoneDeRecherche.add(getTxtZoneDeRecherche());
			panelZoneDeRecherche.add(getBtnRechercherClient());
		}
		return panelZoneDeRecherche;
	}

	public JTextField getTxtZoneDeRecherche() {
		if(txtZoneDeRecherche == null){
			txtZoneDeRecherche = new JTextField(40);
			txtZoneDeRecherche.setText("nom du client");
			
		}
		return txtZoneDeRecherche;
	}

	public JButton getBtnRechercherClient() {
		if (btnRechercherClient == null) {
			btnRechercherClient = new JButton("Rechercher",
					new ImageIcon(getClass().getResource("./resources/rechercher.png")));
			btnRechercherClient.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnRechercherClient.setHorizontalTextPosition(SwingConstants.CENTER);
			btnRechercherClient.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						listModel.clear();
						for (Client c : Clinique.getInstance().rechercherClients(getTxtZoneDeRecherche().getText())) {
							listModel.addElement(c.getNomClient()
									+ " - " + c.getPrenomClient()
									+ " - " + c.getCodePostal()
									+ " - " + c.getVille());
						}
						
					} catch (BLLException e1) {
						e1.printStackTrace();
					}
					
				}
			});
		}
		return btnRechercherClient;
	}
	
	
	
	
	////////////////////////////////
	// TEXTFIELD PANEL
	////////////////////////////////

	private JList<String> txtResultatsRecherche;
	private JScrollPane scrTxtAreaResulatsRecherche;
	private DefaultListModel listModel;
	
	public JList<String> getTxtResultatsRecherche() {
		DefaultListModel listModel = new DefaultListModel();
		
		if(txtResultatsRecherche == null){
			txtResultatsRecherche = new JList(listModel);
			scrTxtAreaResulatsRecherche = new JScrollPane(txtResultatsRecherche);
		}
		return txtResultatsRecherche;

	}

	///////////////////////////////////
	// METHODES
	///////////////////////////////////

}
