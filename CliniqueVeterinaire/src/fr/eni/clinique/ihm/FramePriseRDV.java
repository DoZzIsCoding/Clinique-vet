package fr.eni.clinique.ihm;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import fr.eni.clinique.bll.ArticleNotFoundException;
import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.Clinique;
import fr.eni.clinique.bll.RdvNotFoundException;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.RDV;

public class FramePriseRDV extends JFrame {

	private JPanel mainPanel;

	// CONSTRUCTEUR
	public FramePriseRDV() {
		setTitle("Prise de rendez-vous");
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setContentPane(getMainPanel());
	}

	///////////////////////////////////
	// Interface graphique
	///////////////////////////////////
	public JPanel getMainPanel() {
		if (mainPanel == null) {
			mainPanel = new JPanel(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();

			gbc.gridx = 0;
			gbc.gridy = 0;
			mainPanel.add(getPourPanel(), gbc);

			gbc.gridx = 1;
			mainPanel.add(getParPanel(), gbc);

			gbc.gridx = 2;
			mainPanel.add(getQuandPanel(), gbc);

			gbc.gridy = 1;
			gbc.gridx = 0;
			gbc.gridwidth = 3;
			mainPanel.add(getTablePanel(), gbc);

			gbc.gridy = 2;
			gbc.gridx = 1;
			mainPanel.add(getBtnSupprimer(), gbc);

			gbc.gridx = 2;
			mainPanel.add(getBtnValider(), gbc);

		}

		return mainPanel;
	}

	////////////////////////////////////
	// PANEL POUR
	////////////////////////////////////

	private JPanel pourPanel;
	private JLabel lblClient;
	private JComboBox<String> cbbClient;
	private JButton btnAjouterClient;
	private JLabel lblAnimal;
	private JComboBox<String> cbbAnimal;
	private JButton btnAjouterAnimal;

	public JPanel getPourPanel() {
		if (pourPanel == null) {
			TitledBorder border = new TitledBorder("Pour");
			border.setTitleJustification(TitledBorder.LEFT);
			border.setTitlePosition(TitledBorder.TOP);

			pourPanel = new JPanel(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();

			// Options graphiques
			pourPanel.setBorder(border);
			pourPanel.setFont(new java.awt.Font("Verdana", 3, 18));
			pourPanel.setSize(300, 300);
			pourPanel.setBackground(Color.WHITE);
			// pourPanel.s
			// encours

			// Oragnisation des éléments
			gbc.gridx = 0;
			gbc.gridy = 0;
			pourPanel.add(getLblClient(), gbc);
			gbc.gridy = 1;
			gbc.weightx = 0.7;
			pourPanel.add(getCbbClient(), gbc);
			gbc.gridx = 1;
			gbc.weightx = 0.3;
			pourPanel.add(getBtnAjouterClient(), gbc);
			gbc.gridx = 0;
			gbc.gridy = 2;
			pourPanel.add(getLblAnimal(), gbc);
			gbc.gridy = 3;
			gbc.weightx = 0.7;
			pourPanel.add(getCbbAnimal(), gbc);
			gbc.gridx = 1;
			gbc.weightx = 0.3;
			pourPanel.add(getBtnAjouterAnimal(), gbc);
		}

		return pourPanel;
	}

	public JLabel getLblClient() {
		if (lblClient == null) {
			lblClient = new JLabel("Client");
			lblClient.setHorizontalAlignment(JLabel.LEFT);
		}
		return lblClient;
	}

	public JComboBox<String> getCbbClient() {
		if (cbbClient == null) {

			try {
				cbbClient = new JComboBox<String>(Clinique.getInstance().getTabNomsClients());
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cbbClient.setAlignmentX(CENTER_ALIGNMENT);

			cbbClient.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					getCbbAnimal().removeAllItems();
					try {
						for (int i = 0; i < Clinique.getInstance()
								.getAnimauxDeClient(getCbbClient().getSelectedIndex()).length; i++) {
							getCbbAnimal().addItem(
									Clinique.getInstance().getAnimauxDeClient(getCbbClient().getSelectedIndex())[i]);
						}
					} catch (BLLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			});
			;
		}
		return cbbClient;
	}

	public JButton getBtnAjouterClient() {
		if (btnAjouterClient == null) {
			btnAjouterClient = new JButton("+");
		}
		return btnAjouterClient;
	}

	public JLabel getLblAnimal() {
		if (lblAnimal == null) {
			lblAnimal = new JLabel("Animal");
		}
		return lblAnimal;
	}

	public JComboBox<String> getCbbAnimal() {
		if (cbbAnimal == null) {

			cbbAnimal = new JComboBox<String>();

			cbbAnimal.setAlignmentX(CENTER_ALIGNMENT);
		}
		return cbbAnimal;
	}

	public JButton getBtnAjouterAnimal() {
		if (btnAjouterAnimal == null) {
			btnAjouterAnimal = new JButton("+");
		}
		return btnAjouterAnimal;
	}

	////////////////////////////////////
	// PANEL PAR
	////////////////////////////////////

	private JPanel parPanel;
	private JLabel lblVeterinaire;
	private JComboBox<String> cbbVeterinaire;

	public JPanel getParPanel() {
		// ajouter le titledborder
		if (parPanel == null) {
			TitledBorder border = new TitledBorder("Par");
			border.setTitleJustification(TitledBorder.LEFT);
			border.setTitlePosition(TitledBorder.TOP);
			parPanel = new JPanel();

			// options graphiques
			parPanel.setBorder(border);

			// ajout modules
			parPanel.setLayout(new BoxLayout(parPanel, BoxLayout.Y_AXIS));
			parPanel.add(getLblVeterinaire());
			parPanel.add(getCbbVeterinaire());
		}
		return parPanel;
	}

	public JLabel getLblVeterinaire() {
		if (lblVeterinaire == null) {
			lblVeterinaire = new JLabel("Vétérinaire");
		}
		return lblVeterinaire;
	}

	public JComboBox<String> getCbbVeterinaire() {
		if (cbbVeterinaire == null) {
			try {
				cbbVeterinaire = new JComboBox<String>(Clinique.getInstance().getTabNomsVeterinaires());
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			cbbVeterinaire.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						tableModel.updateData();
					} catch (BLLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			});
		}
		return cbbVeterinaire;
	}

	////////////////////////////////////
	// PANEL QUAND
	////////////////////////////////////

	private JPanel quandPanel;
	private JLabel lblDate;
	private UtilDateModel model;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;

	private JLabel lblHeure;
	private JComboBox<Integer> cbbHeure;
	private JLabel lblH;
	private JComboBox<Integer> cbbMinute;

	public JPanel getQuandPanel() {
		// ajouter le titledborder
		if (quandPanel == null) {
			TitledBorder border = new TitledBorder("Quand");
			border.setTitleJustification(TitledBorder.LEFT);
			border.setTitlePosition(TitledBorder.TOP);
			quandPanel = new JPanel(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();

			quandPanel.setBorder(border);
			// Groupe date
			gbc.gridx = 0;
			gbc.gridy = 0;
			quandPanel.add(getLblDate(), gbc);
			gbc.gridy = 1;
			gbc.weightx = 0.8;
			quandPanel.add(getDatePicker(), gbc);

			// Groupe Heure
			gbc.weightx = 1;
			gbc.gridy = 2;
			quandPanel.add(getLblHeure(), gbc);
			gbc.gridy = 3;
			gbc.weightx = 0.2;
			quandPanel.add(getCbbHeure(), gbc);
			gbc.gridx = 1;
			gbc.weightx = 0.2;
			quandPanel.add(getLblH(), gbc);
			gbc.gridx = 2;
			gbc.weightx = 0.2;
			quandPanel.add(getCbbMinute(), gbc);
		}
		return quandPanel;
	}

	public JLabel getLblDate() {
		if (lblDate == null) {
			lblDate = new JLabel("Date");
		}
		return lblDate;
	}

	public JDatePanelImpl getDatePanel() {
		if (datePanel == null) {
			Properties p = new Properties();
			p.put("text.day", "Today");
			p.put("text.month", "Month");
			p.put("text.year", "Year");
			datePanel = new JDatePanelImpl(getModel(), p);

		}
		return datePanel;
	}

	public JDatePickerImpl getDatePicker() {
		if (datePicker == null) {
			datePicker = new JDatePickerImpl(getDatePanel(), new DateComponentFormatter());

			datePicker.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						tableModel.updateData();
					} catch (BLLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			});
		}
		return datePicker;
	}

	public UtilDateModel getModel() {
		if (model == null) {
			model = new UtilDateModel();
			model.setDate(LocalDate.now().getYear(), LocalDate.now().getMonthValue() - 1,
					LocalDate.now().getDayOfMonth());
			model.setSelected(true);
			// TODO: revoir les parametres de dates null
		}
		return model;
	}

	public JLabel getLblHeure() {
		if (lblHeure == null) {
			lblHeure = new JLabel("Heure");
		}
		return lblHeure;
	}

	public JComboBox<Integer> getCbbHeure() {
		if (cbbHeure == null) {
			Integer[] heures = { 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 };
			// TODO: connecter au Mangaer
			cbbHeure = new JComboBox<Integer>(heures);
		}
		return cbbHeure;
	}

	public JLabel getLblH() {
		if (lblH == null) {
			lblH = new JLabel("h");
		}
		return lblH;
	}

	public JComboBox<Integer> getCbbMinute() {
		if (cbbMinute == null) {
			Integer[] minutes = { 0, 15, 30, 45 };
			cbbMinute = new JComboBox<Integer>(minutes);
		}
		return cbbMinute;
	}

	/////////////////////////////////
	// LISTE DE RENDEZ VOUS
	/////////////////////////////////

	private JScrollPane tablePanel;
	private JTable tableRDV;
	private RDVTableModel tableModel;

	public JScrollPane getTablePanel() {
		if (tablePanel == null) {
			tablePanel = new JScrollPane();
			tablePanel.setViewportView(getTableRDV());
		}
		return tablePanel;
	}

	public JTable getTableRDV() {
		if (tableRDV == null) {
			tableModel = new RDVTableModel();
			tableRDV = new JTable(tableModel);
		}
		return tableRDV;
	}

	/////////////////////////////////
	// BOUTONS BAS DE PAGE
	/////////////////////////////////

	private JButton btnSupprimer;
	private JButton btnValider;

	public JButton getBtnSupprimer() {
		if (btnSupprimer == null) {
			btnSupprimer = new JButton("Supprimer");

			btnSupprimer.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						Clinique.getInstance().supprimerRdvCourant(getTableRDV().getSelectedRow());
					} catch (BLLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
		return btnSupprimer;
	}

	public JButton getBtnValider() {
		if (btnValider == null) {
			btnValider = new JButton("Valider");

		}
		return btnValider;
	}

	////////////////////////////////
	// CLASS RDVTableModel
	////////////////////////////////

	public class RDVTableModel extends AbstractTableModel {
		private String[] nomsColonne = { "Heure", "Nom du client", "Animal", "Race" };

		private List<RDV> rendezVous = new ArrayList<>();

		public RDVTableModel() {
			try {
				rendezVous = Clinique.getInstance().getRDVJour(Date.from(Instant.now()),
						getCbbVeterinaire().getSelectedIndex());
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
			rendezVous = Clinique.getInstance().getRDVJour((Date) getDatePicker().getModel().getValue(),
					getCbbVeterinaire().getSelectedIndex());
			fireTableDataChanged();
		}

		@Override
		public int getRowCount() {
			return rendezVous.size();
		}

		@Override
		public int getColumnCount() {
			return nomsColonne.length;
		}

		@Override
		public String getColumnName(int column) {
			return nomsColonne[column];
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return getValueAt(0, columnIndex).getClass();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			if (rowIndex >= 0 && rowIndex < rendezVous.size()) {
				RDV rdvAAfficher = rendezVous.get(rowIndex);
				switch (columnIndex) {
				case 0:
					SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
					return sdf.format(rdvAAfficher.getDate());
				case 1:
					return rdvAAfficher.getNomClient();
				case 2:
					return rdvAAfficher.getNomAnimal();
				case 3:
					return rdvAAfficher.getEspeceAnimal();
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
		public RDV getValueAt(int rowIndex) throws RdvNotFoundException {
			if (rowIndex >= 0 && rowIndex < 10) {
				return rendezVous.get(rowIndex);
			}
			throw new RdvNotFoundException();
		}

	}

}
