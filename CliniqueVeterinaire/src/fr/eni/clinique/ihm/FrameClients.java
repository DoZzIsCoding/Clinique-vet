package fr.eni.clinique.ihm;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.AbstractTableModel;

import fr.eni.clinique.bll.AnimalNotFoundException;
import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.Clinique;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;

@SuppressWarnings("serial")
public class FrameClients extends JFrame {

	public FrameClients() {
		setTitle("Clients");
		setBounds(100, 100, 850, 600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setContentPane(getMainPanel());
		setVisible(true);
		
		try {
			setClient(Clinique.getInstance().getClientEnCours());
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	////////////////////////////////
	// MAIN PANEL
	////////////////////////////////

	private JPanel mainPanel;

	public JPanel getMainPanel() {
		if (mainPanel == null) {
			mainPanel = new JPanel();
			mainPanel.setLayout(new BorderLayout());

			mainPanel.add(getPanelBoutonsDuHaut(), BorderLayout.PAGE_START);
			mainPanel.add(getPanelDetailsClient(), BorderLayout.LINE_START);
			mainPanel.add(getPanelAnimauxClient(), BorderLayout.LINE_END);

			
		}
		return mainPanel;
	}

	///////////////////////////////////
	// PANEL BOUTONS DU HAUT
	///////////////////////////////////

	private JPanel panelBoutonsDuHaut;
	private JButton btnRechercherClient;
	private JButton btnAjouterClient;
	private JButton btnSupprimerClient;
	private JButton btnValiderClient;
	private JButton btnAnnulerClient;

	public JPanel getPanelBoutonsDuHaut() {
		if (panelBoutonsDuHaut == null) {
			panelBoutonsDuHaut = new JPanel(new BorderLayout());
			
			Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
			panelBoutonsDuHaut.setBorder(border);
			
			Box box = Box.createHorizontalBox();

			box = Box.createHorizontalBox();
			box.add(getBtnRechercherClient());
			box.add(Box.createHorizontalGlue());
			box.add(getBtnAjouterClient());
			box.add(getBtnSupprimerClient());
			box.add(Box.createGlue());
			box.add(getBtnValiderClient());
			box.add(getBtnAnnulerClient());
			panelBoutonsDuHaut.add(box);
		}
		return panelBoutonsDuHaut;
	}

	////////// GROUPE RECHERCHER //////////////////
	public JButton getBtnRechercherClient() {
		if (btnRechercherClient == null) {
			btnRechercherClient = new JButton("Rechercher",
					new ImageIcon(getClass().getResource("./resources/rechercher.png")));
			btnRechercherClient.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnRechercherClient.setHorizontalTextPosition(SwingConstants.CENTER);
			btnRechercherClient.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					new FrameRechercheClient();
					
				}
			});
		}
		return btnRechercherClient;
	}

	////////// GROUPE AJOUTER/SUPPRIMER //////////////////

	public JButton getBtnAjouterClient() {
		if (btnAjouterClient == null) {
			btnAjouterClient = new JButton("Ajouter", new ImageIcon(getClass().getResource("./resources/ajouter.png")));
			btnAjouterClient.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnAjouterClient.setHorizontalTextPosition(SwingConstants.CENTER);
			
			btnAjouterClient.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					new FrameAjouterClient();
					
				}
			});

		}
		return btnAjouterClient;
	}

	public JButton getBtnSupprimerClient() {
		if (btnSupprimerClient == null) {
			btnSupprimerClient = new JButton("Supprimer",
					new ImageIcon(getClass().getResource("./resources/supprimer.png")));
			btnSupprimerClient.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnSupprimerClient.setHorizontalTextPosition(SwingConstants.CENTER);
			
			btnSupprimerClient.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						Clinique.getInstance().supprimerClientCourant();
					} catch (BLLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					
				}
			});
		}
		return btnSupprimerClient;
	}

	////////// GROUPE VALIDER/ ANNULER //////////////////

	public JButton getBtnValiderClient() {
		if (btnValiderClient == null) {
			btnValiderClient = new JButton("Valider", new ImageIcon(getClass().getResource("./resources/valider.png")));
			btnValiderClient.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnValiderClient.setHorizontalTextPosition(SwingConstants.CENTER);
			
			btnValiderClient.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Clinique.getInstance().validerClientCourant();
					
				}
			});
		}
		return btnValiderClient;
	}

	public JButton getBtnAnnulerClient() {
		if (btnAnnulerClient == null) {
			btnAnnulerClient = new JButton("Annuler", new ImageIcon(getClass().getResource("./resources/annuler.png")));
			btnAnnulerClient.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnAnnulerClient.setHorizontalTextPosition(SwingConstants.CENTER);
		}
		return btnAnnulerClient;
	}

	///////////////////////////////////
	// PANEL DETAILS CLIENTS
	///////////////////////////////////

	private JPanel panelDetailsClient;

	private JLabel lblCodeClient;
	private JTextField txtCodeClient;

	private JLabel lblNomClient;
	private JTextField txtNomClient;

	private JLabel lblPrenomClient;
	private JTextField txtPrenomClient;

	private JLabel lblAdresseClient;
	private JTextField txtAdresseClient;
	private JTextField txtAdresseClient2;

	private JLabel lblCodePostalClient;
	private JTextField txtCodePostalClient;

	private JLabel lblVilleClient;
	private JTextField txtVilleClient;
	
	private JLabel lblNumTel;
	private JTextField txtNumTel;
	
	private JLabel lblAssurance;
	private JTextField txtAssurance;
	
	private JLabel lblEmail;
	private JTextField txtEmail;
	
	private JLabel lblRemarque;
	private JTextArea txtRemarque;

	public JPanel getPanelDetailsClient() {
		if (panelDetailsClient == null) {
			panelDetailsClient = new JPanel(new GridBagLayout());
			
			

			addComponentTo(getLblCodeClient(), panelDetailsClient, 0, 0, 1, 1, 1, true);
			addComponentTo(getTxtCodeClient(), panelDetailsClient, 1, 0, 1, 1, 1, true);
			addComponentTo(getLblNomClient(), panelDetailsClient, 0, 1, 1, 1, 1, true);
			addComponentTo(getTxtNomClient(), panelDetailsClient, 1, 1, 1, 1, 1, true);
			addComponentTo(getLblPrenomClient(), panelDetailsClient, 0, 2, 1, 1, 1, true);
			addComponentTo(getTxtPrenomClient(), panelDetailsClient, 1, 2, 1, 1, 1, true);
			addComponentTo(getLblAdresseClient(), panelDetailsClient, 0, 3, 1, 1, 1, true);
			addComponentTo(getTxtAdresseClient(), panelDetailsClient, 1, 3, 1, 1, 1, true);
			addComponentTo(getTxtAdresseClient2(), panelDetailsClient, 1, 4, 1, 1, 1, true);
			addComponentTo(getLblCodePostalClient(), panelDetailsClient, 0, 5, 1, 1, 1, true);
			addComponentTo(getTxtCodePostalClient(), panelDetailsClient, 1, 5, 1, 1, 1, true);
			addComponentTo(getLblVilleClient(), panelDetailsClient, 0, 6, 1, 1, 1, true);
			addComponentTo(getTxtVilleClient(), panelDetailsClient, 1, 6, 1, 1, 1, true);
			addComponentTo(getLblNumTel(), panelDetailsClient, 0, 7, 1, 1, 1, true);
			addComponentTo(getTxtNumTel(), panelDetailsClient, 1, 7, 1, 1, 1, true);
			addComponentTo(getLblAssurance(), panelDetailsClient, 0, 8, 1, 1, 1, true);
			addComponentTo(getTxtAssurance(), panelDetailsClient, 1, 8, 1, 1, 1, true);
			addComponentTo(getLblEmail(), panelDetailsClient, 0, 9, 1, 1, 1, true);
			addComponentTo(getTxtEmail(), panelDetailsClient, 1, 9, 1, 1, 1, true);
			addComponentTo(getLblRemarque(), panelDetailsClient, 0, 10, 1, 1, 1, true);
			addComponentTo(getTxtRemarque(), panelDetailsClient, 1, 10, 1, 1, 1, true);
			

		}
		return panelDetailsClient;
	}

	public JLabel getLblCodeClient() {
		if (lblCodeClient == null) {
			lblCodeClient = new JLabel("Code");
		}
		return lblCodeClient;
	}

	public JTextField getTxtCodeClient() {
		if (txtCodeClient == null) {
			txtCodeClient = new JTextField(20);
			txtCodeClient.setEnabled(false);
		}
		return txtCodeClient;
	}

	public JLabel getLblNomClient() {
		if (lblNomClient == null) {
			lblNomClient = new JLabel("Nom");
		}
		return lblNomClient;
	}

	public JTextField getTxtNomClient() {
		if (txtNomClient == null) {
			txtNomClient = new JTextField(20);
		}
		return txtNomClient;
	}

	public JLabel getLblPrenomClient() {
		if (lblPrenomClient == null) {
			lblPrenomClient = new JLabel("Prénom");
		}
		return lblPrenomClient;
	}

	public JTextField getTxtPrenomClient() {
		if (txtPrenomClient == null) {
			txtPrenomClient = new JTextField(20);
		}
		return txtPrenomClient;
	}

	public JLabel getLblAdresseClient() {
		if (lblAdresseClient == null) {
			lblAdresseClient = new JLabel("Adresse");

		}
		return lblAdresseClient;
	}

	public JTextField getTxtAdresseClient() {
		if (txtAdresseClient == null) {
			txtAdresseClient = new JTextField(20);
		}
		return txtAdresseClient;
	}

	public JTextField getTxtAdresseClient2() {
		if (txtAdresseClient2 == null) {
			txtAdresseClient2 = new JTextField(20);
		}
		return txtAdresseClient2;
	}

	public JLabel getLblCodePostalClient() {
		if (lblCodePostalClient == null) {
			lblCodePostalClient = new JLabel("Code postal");
		}
		return lblCodePostalClient;
	}

	public JTextField getTxtCodePostalClient() {
		if (txtCodePostalClient == null) {
			txtCodePostalClient = new JTextField(20);
		}
		return txtCodePostalClient;
	}

	public JLabel getLblVilleClient() {
		if (lblVilleClient == null) {
			lblVilleClient = new JLabel("Ville");
		}
		return lblVilleClient;
	}

	public JTextField getTxtVilleClient() {
		if (txtVilleClient == null) {
			txtVilleClient = new JTextField(20);
		}
		return txtVilleClient;
	}
	
	public JLabel getLblNumTel() {
		if (lblNumTel == null) {
			lblNumTel = new JLabel("Téléphone");
		}
		return lblNumTel;
	}

	public JTextField getTxtNumTel() {
		if (txtNumTel == null) {
			txtNumTel = new JTextField(20);
		}
		return txtNumTel;
	}

	public JLabel getLblAssurance() {
		if (lblAssurance == null) {
			lblAssurance = new JLabel("Assurance");
		}
		return lblAssurance;
	}

	public JTextField getTxtAssurance() {
		if (txtAssurance == null) {
			txtAssurance = new JTextField(20);
		}
		return txtAssurance;
	}

	public JLabel getLblEmail() {
		if (lblEmail == null) {
			lblEmail = new JLabel("Email");
		}
		return lblEmail;
	}

	public JTextField getTxtEmail() {
		if (txtEmail == null) {
			txtEmail = new JTextField(20);
		}
		return txtEmail;
	}

	public JLabel getLblRemarque() {
		if (lblRemarque == null) {
			lblRemarque = new JLabel("Remarques");
		}
		return lblRemarque;
	}

	public JTextArea getTxtRemarque() {
		if (txtRemarque == null) {
			txtRemarque = new JTextArea(5,20);
		}
		return txtRemarque;
	}

	///////////////////////////////////
	// PANEL ANIMAUX CLIENTS
	///////////////////////////////////
	private JPanel panelAnimauxClient;

	public JPanel getPanelAnimauxClient() {
		if(panelAnimauxClient == null){
			panelAnimauxClient = new JPanel(new BorderLayout());
			
			panelAnimauxClient.add(getPanelTableAnimauxClient(), BorderLayout.PAGE_START);
			panelAnimauxClient.add(getPanelBoutons(), BorderLayout.AFTER_LAST_LINE);
		}
		return panelAnimauxClient;
	}

	////////// TABLEAU //////////////////
	private JScrollPane panelTableAnimauxClient;
	private JTable tableAnimauxClient;
	private AnimalTableModel tableModel;
	
	public JScrollPane getPanelTableAnimauxClient() {
		if (panelTableAnimauxClient == null){
			panelTableAnimauxClient = new JScrollPane();
			panelTableAnimauxClient.setViewportView(getTableAnimauxClient());
		}
		return panelTableAnimauxClient;
	}

	public JTable getTableAnimauxClient() {
		if(tableAnimauxClient == null){
			tableModel = new AnimalTableModel();
			tableAnimauxClient = new JTable(tableModel);
			
			
		}
		return tableAnimauxClient;
	}

	////////// BOUTONS //////////////////
	private JPanel panelBoutons;
	private JButton btnAjouterAnimal;
	private JButton btnSupprimerAnimal;
	private JButton btnModifieranimal;
	
	public JPanel getPanelBoutons() {
		if(panelBoutons == null){
			panelBoutons = new JPanel();
			panelBoutons.setLayout(new FlowLayout(FlowLayout.RIGHT));
			panelBoutons.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			panelBoutons.add(getBtnAjouterAnimal());
			panelBoutons.add(getBtnSupprimerAnimal());
			panelBoutons.add(getBtnModifieranimal());
			
		}
		return panelBoutons;
	}

	public JButton getBtnAjouterAnimal() {
		if(btnAjouterAnimal == null){
			btnAjouterAnimal = new JButton("Ajouter", new ImageIcon(getClass().getResource("./resources/ajouter.png")));
		}
		return btnAjouterAnimal;
	}

	public JButton getBtnSupprimerAnimal() {
		if(btnSupprimerAnimal == null){
			btnSupprimerAnimal = new JButton("Supprimer", new ImageIcon(getClass().getResource("./resources/supprimer.png")));
		}
		return btnSupprimerAnimal;
	}

	public JButton getBtnModifieranimal() {
		if(btnModifieranimal == null){
			btnModifieranimal = new JButton("Modifier", new ImageIcon(getClass().getResource("./resources/modifier.png")));
		}
		return btnModifieranimal;
	}

	///////////////////////////////////
	// METHODES
	///////////////////////////////////
	
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
	
	private void setClient(Client client){
		getTxtCodeClient().setText(String.valueOf(client.getCodeClient()));
		getTxtNomClient().setText(client.getNomClient());
		getTxtPrenomClient().setText(client.getPrenomClient());
		getTxtAdresseClient().setText(client.getAdresse1());
		getTxtAdresseClient2().setText(client.getAdresse2());
		getTxtCodePostalClient().setText(client.getCodePostal());
		getTxtVilleClient().setText(client.getVille());
	}
	
	
	////////////////////////////////
	// CLASSES
	////////////////////////////////

	public class AnimalTableModel extends AbstractTableModel {
		private String[] nomsColonne = { "Numéro", "Nom", "Sexe", "Couleur", "Race", "Espèce", "Tatouage" };

		private List<Animal> animaux = new ArrayList<>();

		public AnimalTableModel() {
			try {
				animaux = Clinique.getInstance().getClientEnCours().getAnimaux();
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/**
		 * Cette méthode permet de mettre à jour l'affichage de la JTable (en
		 * rechargeant les données)
		 * 
		 * @throws BLLException
		 */
		public void updateData() throws BLLException {
			animaux = Clinique.getInstance().getClientEnCours().getAnimaux();
			fireTableDataChanged();
		}

		@Override
		public int getRowCount() {
			return animaux.size();
		}

		@Override
		public int getColumnCount() {
			return nomsColonne.length;
		}

		@Override
		public String getColumnName(int column) {
			return nomsColonne[column];
		}

//		@Override
//		public Class<?> getColumnClass(int columnIndex) {
//			return getValueAt(0, columnIndex).getClass();
//		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			if (rowIndex >= 0 && rowIndex < animaux.size()) {
				Animal animalAAfficher = animaux.get(rowIndex);
				switch (columnIndex) {
				case 0:
					return animalAAfficher.getCodeAnimal();
				case 1:
					return animalAAfficher.getNomAnimal();
				case 2:
					return animalAAfficher.getSexe();
				case 3:
					return animalAAfficher.getCouleur();
				case 4:
					return animalAAfficher.getRace();
				case 5:
					return animalAAfficher.getEspece();
				case 6:
					return animalAAfficher.getTatouage();
				
				}
			}
			return null;
		}

		/**
		 * Retourne l'article pour une ligne donnée
		 * 
		 * @param rowIndex
		 *            La ligne où se situe l'article
		 * @return
		 * @throws ArticleNotFoundException
		 */
		public Animal getValueAt(int rowIndex) throws AnimalNotFoundException {
			if (rowIndex >= 0 && rowIndex < 10) {
				return animaux.get(rowIndex);
			}
			throw new AnimalNotFoundException();
		}

	}

}
