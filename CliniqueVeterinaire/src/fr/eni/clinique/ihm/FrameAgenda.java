package fr.eni.clinique.ihm;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.Clinique;
import fr.eni.clinique.bll.RdvNotFoundException;
import fr.eni.clinique.bo.RDV;

@SuppressWarnings("serial")
public class FrameAgenda extends JFrame {

	public FrameAgenda() {
		setTitle("Agenda");
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setContentPane(getMainPanel());
		setVisible(true);
	}

	private JPanel mainPanel;

	public JPanel getMainPanel() {
		if (mainPanel == null) {
			mainPanel = new JPanel(new BorderLayout());

			mainPanel.add(getDePanel(), BorderLayout.NORTH);
			mainPanel.add(getPanelTableauAgenda(), BorderLayout.CENTER);
			mainPanel.add(getDossierMedicalPanel(), BorderLayout.SOUTH);

		}

		return mainPanel;
	}

	////////////////////////////////////
	// PANEL PAR
	////////////////////////////////////

	private JPanel dePanel;
	private JLabel lblVeterinaire;
	private JComboBox<String> cbbVeterinaire;
	private JLabel lblDate;
	private UtilDateModel model;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;

	public JPanel getDePanel() {
		// ajouter le titledborder
		if (dePanel == null) {
			TitledBorder border = new TitledBorder("Par");
			border.setTitleJustification(TitledBorder.LEFT);
			border.setTitlePosition(TitledBorder.TOP);
			dePanel = new JPanel();

			// options graphiques
			dePanel.setBorder(border);

			// ajout modules
			dePanel.setLayout(new BoxLayout(dePanel, BoxLayout.X_AXIS));
			dePanel.add(getLblVeterinaire());
			dePanel.add(getCbbVeterinaire());
			dePanel.add(getLblVeterinaire());
			dePanel.add(getCbbVeterinaire());
			dePanel.add(getDatePicker());

		}
		return dePanel;
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

			// cbbVeterinaire.addActionListener(new ActionListener() {
			//
			// @Override
			// public void actionPerformed(ActionEvent e) {
			// try {
			// tableModel.updateData();
			// } catch (BLLException e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// }
			//
			// }
			// });
		}
		return cbbVeterinaire;
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

			// datePicker.addActionListener(new ActionListener() {
			//
			// @Override
			// public void actionPerformed(ActionEvent e) {
			// try {
			// tableModel.updateData();
			// } catch (BLLException e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// }
			//
			// }
			// });
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

	/////////////////////////////////
	// LISTE DE RENDEZ VOUS
	/////////////////////////////////

	private JScrollPane panelTableauAgenda;
	private JTable tableRDV;
	private RDVTableModel tableModel;

	public JScrollPane getPanelTableauAgenda() {
		if (panelTableauAgenda == null) {
			panelTableauAgenda = new JScrollPane();
			panelTableauAgenda.setViewportView(getTableRDV());
		}
		return panelTableauAgenda;
	}

	public JTable getTableRDV() {
		if (tableRDV == null) {
			tableModel = new RDVTableModel();
			tableRDV = new JTable(tableModel);
		}
		return tableRDV;
	}

	///////////////////////////////////
	// DOSSIER MEDICAL PANEL
	///////////////////////////////////
	private JPanel dossierMedicalPanel;
	private JButton btndossierMedical;

	public JPanel getDossierMedicalPanel() {
		if (dossierMedicalPanel == null) {
			dossierMedicalPanel = new JPanel(new FlowLayout());
			dossierMedicalPanel.setAlignmentX(FlowLayout.RIGHT);
			dossierMedicalPanel.add(getBtndossierMedical());

		}
		return dossierMedicalPanel;
	}

	public JButton getBtndossierMedical() {
		if (btndossierMedical == null) {
			btndossierMedical = new JButton("Dossier médical",
					new ImageIcon(getClass().getResource("./resources/dossierMedical.png")));
			
			btndossierMedical.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			
			btndossierMedical.setVerticalTextPosition(SwingConstants.BOTTOM);
			btndossierMedical.setHorizontalTextPosition(SwingConstants.CENTER);
			
			btndossierMedical.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					//TODO: lier a la frame parent
				}
			});
		}
		return btndossierMedical;
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
