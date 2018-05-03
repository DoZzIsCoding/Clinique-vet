package fr.eni.clinique.ihm;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.Clinique;
import fr.eni.clinique.bll.RdvNotFoundException;
import fr.eni.clinique.bo.Observable;
import fr.eni.clinique.bo.RDV;
import fr.eni.clinique.bo.Observable.ObservableListener;
import fr.eni.clinique.dal.CreneauDejaPrisException;

@SuppressWarnings("serial")
public class FramePriseRDV extends JFrame {

	private JPanel mainPanel;

	private Observable<Integer> clientEnCours;
	private Observable<Boolean> listeMiseAJour;

	// CONSTRUCTEUR
	public FramePriseRDV() {
		setTitle("Prise de rendez-vous");
		setBounds(100, 100, 950, 660);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setContentPane(getMainPanel());
		setResizable(false);
		setVisible(true);

		try {
			listeMiseAJour = Clinique.getInstance().getListeMiseAJourObserve();
			listeMiseAJour.registerListener(new ObservableListener() {

				@Override
				public void onChanged() {
					
					try {
						
						getCbbClient().removeAllItems();
						for (int i = 0; i < Clinique.getInstance().getClients().size(); i++) {
							getCbbClient().addItem(Clinique.getInstance().getClients().get(i).getNomClient() + " "
									+ Clinique.getInstance().getClients().get(i).getPrenomClient());
						}
						getCbbClient().setSelectedIndex(Clinique.getInstance().getClients().size()-1);
						cbbAnimal.removeAllItems();
						for (int i = 0; i < Clinique.getInstance()
								.getAnimauxDeClient(getCbbClient().getSelectedIndex()).length; i++) {
							getCbbAnimal().addItem(
									Clinique.getInstance().getAnimauxDeClient(getCbbClient().getSelectedIndex())[i]);
						}
					} catch (BLLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		} catch (BLLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	///////////////////////////////////
	// MAIN PANEL
	///////////////////////////////////
	public JPanel getMainPanel() {
		if (mainPanel == null) {
			mainPanel = new JPanel(new BorderLayout());

			mainPanel.add(getPourPanel(), BorderLayout.WEST);
			mainPanel.add(getParPanel(), BorderLayout.CENTER);
			mainPanel.add(getQuandPanel(), BorderLayout.EAST);
			mainPanel.add(getPanelTableauEtBoutonsBas(), BorderLayout.SOUTH);
			// mainPanel.add(getPanelBoutonsduBasRDV(),BorderLayout.AFTER_LAST_LINE);

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
			// pourPanel.setPreferredSize(new Dimension(200, 200));
			pourPanel.setBorder(border);
			// pourPanel.setFont(new java.awt.Font("Verdana", 3, 18));

			addComponentTo(getLblClient(), pourPanel, 0, 0, 1, 1, 1, true);
			addComponentTo(getCbbClient(), pourPanel, 0, 1, 1, 1, 1, true);
			addComponentTo(getBtnAjouterClient(), pourPanel, 2, 1, 1, 1, 1, true);
			addComponentTo(getLblAnimal(), pourPanel, 0, 2, 1, 1, 1, true);
			addComponentTo(getCbbAnimal(), pourPanel, 0, 3, 1, 1, 1, true);
			addComponentTo(getBtnAjouterAnimal(), pourPanel, 2, 3, 1, 1, 1, true);
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
				// cbbClient.setPreferredSize(new Dimension(100,100 ));
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cbbClient.setAlignmentX(CENTER_ALIGNMENT);
			cbbClient.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						Clinique.getInstance().setClientEncours(getCbbClient().getSelectedIndex());
					} catch (BLLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
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

			btnAjouterClient.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						Clinique.getInstance().setIndexClientEnCours(-1);
						new FrameAjouterClient();
					} catch (BLLException e1) {
						e1.printStackTrace();
					}
				}
			});
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

			try {
				cbbAnimal = new JComboBox<String>(Clinique.getInstance().getAnimauxDeClient(0));
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			cbbAnimal.setAlignmentX(CENTER_ALIGNMENT);
		}
		return cbbAnimal;
	}

	public JButton getBtnAjouterAnimal() {
		if (btnAjouterAnimal == null) {
			btnAjouterAnimal = new JButton("+");
			btnAjouterAnimal.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						Clinique.getInstance().setIndexClientEnCours(getCbbClient().getSelectedIndex());
						Clinique.getInstance().setIndexAnimalEnCours(-1);
						new FrameAnimaux();
					} catch (BLLException e1) {
						e1.printStackTrace();
					}
				}
			});
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
			Component rigidArea = Box.createRigidArea(new Dimension(200, 150));
			parPanel.add(rigidArea);
		}
		return parPanel;
	}

	public JLabel getLblVeterinaire() {
		if (lblVeterinaire == null) {
			lblVeterinaire = new JLabel("Vétérinaire");
			// lblVeterinaire.setPreferredSize(new Dimension(100, 100));
		}
		return lblVeterinaire;
	}

	public JComboBox<String> getCbbVeterinaire() {
		if (cbbVeterinaire == null) {
			try {
				cbbVeterinaire = new JComboBox<String>(Clinique.getInstance().getTabNomsVeterinaires());
				cbbVeterinaire.setPreferredSize(new Dimension(100, 100));
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

			Component rigidArea = Box.createRigidArea(new Dimension(5, 5));

			quandPanel.setBorder(border);

			addComponentTo(getLblDate(), quandPanel, 0, 0, 1, 1, 1, true);
			addComponentTo(getDatePicker(), quandPanel, 0, 1, 6, 1, 1, true);
			addComponentTo(getLblHeure(), quandPanel, 0, 2, 1, 1, 1, true);
			addComponentTo(getCbbHeure(), quandPanel, 0, 3, 1, 1, 1, true);
			addComponentTo(getLblH(), quandPanel, 1, 3, 1, 1, 2, true);
			addComponentTo((JComponent) rigidArea, quandPanel, 2, 3, 1, 1, 1, true);
			addComponentTo(getCbbMinute(), quandPanel, 3, 3, 1, 1, 1, true);
			addComponentTo((JComponent) rigidArea, quandPanel, 4, 3, 1, 1, 1, true);
			addComponentTo((JComponent) rigidArea, quandPanel, 5, 3, 1, 1, 1, true);

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
			// cbbMinute.set
		}
		return cbbMinute;
	}

	/////////////////////////////////
	// PANEL TABLEAU + BOUTONS
	/////////////////////////////////
	private JPanel panelTableauEtBoutonsBas;

	public JPanel getPanelTableauEtBoutonsBas() {
		if (panelTableauEtBoutonsBas == null) {
			panelTableauEtBoutonsBas = new JPanel(new BorderLayout());

			panelTableauEtBoutonsBas.add(getTablePanel(), BorderLayout.NORTH);
			panelTableauEtBoutonsBas.add(getPanelBoutonsduBasRDV(), BorderLayout.SOUTH);
		}

		return panelTableauEtBoutonsBas;
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

			tableRDV.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				
				@Override
				public void valueChanged(ListSelectionEvent e) {
					getBtnSupprimerRDV().setEnabled(true);
					
				}
			});
		}
		return tableRDV;
	}

	/////////////////////////////////
	// BOUTONS BAS DE PAGE
	/////////////////////////////////

	private JPanel panelBoutonsduBasRDV;
	private JButton btnSupprimerRDV;
	private JButton btnValiderRDV;

	public JPanel getPanelBoutonsduBasRDV() {
		if (panelBoutonsduBasRDV == null) {
			panelBoutonsduBasRDV = new JPanel(new FlowLayout());

			panelBoutonsduBasRDV.add(getBtnValiderRDV());
			panelBoutonsduBasRDV.add(getBtnSupprimerRDV());
			// BOUTON ANNULER ??? panelBoutonsduBasRDV.add(get, constraints);
		}
		return panelBoutonsduBasRDV;
	}

	public JButton getBtnSupprimerRDV() {
		if (btnSupprimerRDV == null) {
			btnSupprimerRDV = new JButton("Supprimer");

			btnSupprimerRDV.setEnabled(false);
			
			btnSupprimerRDV.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						if (getTableRDV().getSelectedRow() >= 0) {
							int res = JOptionPane.showConfirmDialog(null,
									"Voulez-vous vraiment supprimer ce rendez-vous ?", "Supprimer un rendez-vous",
									JOptionPane.OK_CANCEL_OPTION);

							switch (res) {
							case JOptionPane.OK_OPTION:
								Clinique.getInstance().supprimerRdvCourant(getTableRDV().getSelectedRow());
								tableModel.fireTableDataChanged();
								JOptionPane.showMessageDialog(btnSupprimerRDV, "Suppression effectuée ");
							case JOptionPane.CANCEL_OPTION:
								break;
							case JOptionPane.CLOSED_OPTION:
								break;
							}
							;
						}
					} catch (BLLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
		return btnSupprimerRDV;
	}

	public JButton getBtnValiderRDV() {
		if (btnValiderRDV == null) {
			btnValiderRDV = new JButton("Valider");
			btnValiderRDV.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					int indexVeto;
					LocalDateTime dateRdv;
					int indexClient;
					int indexAnimal;

					indexVeto = getCbbVeterinaire().getSelectedIndex();
					dateRdv = LocalDateTime.of(getDatePicker().getModel().getYear(),
							getDatePicker().getModel().getMonth() + 1, getDatePicker().getModel().getDay(),
							(int) getCbbHeure().getSelectedItem(), (int) getCbbMinute().getSelectedItem());

					indexAnimal = getCbbAnimal().getSelectedIndex();
					if (indexAnimal == -1) {
						JOptionPane.showMessageDialog(btnValiderRDV, "Vous devez d'abord ajouter un animal au client ");
					}

					indexClient = getCbbClient().getSelectedIndex();
					if (indexClient == -1) {
						JOptionPane.showMessageDialog(btnValiderRDV, "Vous devez d'abord ajouter un client ");
					}

					try {
						int res = JOptionPane.showConfirmDialog(null, "Confirmez-vous ce rendez-vous ?",
								"Valider un rendez-vous", JOptionPane.OK_CANCEL_OPTION);

						switch (res) {
						case JOptionPane.OK_OPTION:
							Clinique.getInstance().ajouterRdvCourant(indexClient, indexAnimal, indexVeto, dateRdv);
							tableModel.fireTableDataChanged();
							JOptionPane.showMessageDialog(btnValiderRDV, "Rendez-vous enregistré ");
						case JOptionPane.CANCEL_OPTION:
							break;
						case JOptionPane.CLOSED_OPTION:
							break;
						}
					} catch (BLLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(btnValiderRDV, "Rendez-vous refusé ");
					} catch (CreneauDejaPrisException e1) {
						JOptionPane.showMessageDialog(btnValiderRDV,
								"Un rendez-vous existe deja à l'heure demandée pour cet animal ou ce vétérinaire ");
						e1.printStackTrace();
					}
				}
			});
		}

		return btnValiderRDV;
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

	////////////////////////////////
	// CLASSES
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
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH'h'mm");
					return rdvAAfficher.getDate().format(dtf);
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
