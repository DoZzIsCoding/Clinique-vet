package fr.eni.clinique.ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import fr.eni.clinique.bll.Clinique;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.exceptions.BLLException;
import fr.eni.clinique.exceptions.ClientNonValideException;

@SuppressWarnings("serial")
public class FrameAjouterClient extends JFrame {

	public FrameAjouterClient() {
		setTitle("Ajouter clients");
		setBounds(100, 100, 400, 650);
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

			mainPanel.add(getBoutonsPanel(), BorderLayout.PAGE_START);
			mainPanel.add(getPanelDetailsClient(), BorderLayout.LINE_START);
		}
		return mainPanel;
	}

	////////////////////////////////
	// BOUTONS PANEL
	////////////////////////////////

	private JPanel boutonsPanel;
	private JButton btnValider;
	private JButton btnAnnuler;

	public JPanel getBoutonsPanel() {
		if (boutonsPanel == null) {
			boutonsPanel = new JPanel();
			boutonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

			boutonsPanel.setBorder(border);

			boutonsPanel.add(getBtnValider());
			boutonsPanel.add(getBtnAnnuler());
		}
		return boutonsPanel;
	}

	public JButton getBtnValider() {
		if (btnValider == null) {
			btnValider = new JButton("Valider", new ImageIcon(getClass().getResource("./resources/valider.png")));
			btnValider.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnValider.setHorizontalTextPosition(SwingConstants.CENTER);

			btnValider.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Client client;
					
					int res = JOptionPane.showConfirmDialog(btnValider, "Voulez-vous vraiment créer ce nouveau client?",
							"Création d'un nouveau client", JOptionPane.OK_CANCEL_OPTION);

					switch (res) {
					case JOptionPane.OK_OPTION:
						
						try {
							client = new Client(
									-1,
									getTxtNomClient().getText(), 
									getTxtPrenomClient().getText(), 
									getTxtAdresseClient().getText(), 
									getTxtAdresseClient2().getText(), 
									getTxtCodePostalClient().getText(),
									getTxtVilleClient().getText(),
									getTxtNumTel().getText(),
									getTxtAssurance().getText(),
									getTxtEmail().getText(),
									getTxtRemarque().getText());
							
							Clinique.getInstance().traiterClient(client);
							
							JOptionPane.showMessageDialog(btnValider, "Client créé avec succès");
							fermerFenetre();
						} catch (BLLException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(btnValider, "Erreur de saisie, client non créé \n " + e1.getMessage());
						} catch (ClientNonValideException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(btnValider, e1.getMessageGlobal());
						}
						
					case JOptionPane.CANCEL_OPTION:
						break;
					case JOptionPane.CLOSED_OPTION:
						break;
						}
				}
			});
		}
		return btnValider;
	}

	public JButton getBtnAnnuler() {
		if (btnAnnuler == null) {
			btnAnnuler = new JButton("Annuler", new ImageIcon(getClass().getResource("./resources/annuler.png")));
			btnAnnuler.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnAnnuler.setHorizontalTextPosition(SwingConstants.CENTER);
			
			btnAnnuler.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					getTxtCodeClient().setText("");
					getTxtNomClient().setText("");
					getTxtPrenomClient().setText("");
					getTxtAdresseClient().setText("");
					getTxtAdresseClient2().setText("");
					getTxtCodePostalClient().setText("");
					getTxtVilleClient().setText("");
					getTxtNumTel().setText("");
					getTxtAssurance().setText("");
					getTxtEmail().setText("");
					getTxtRemarque().setText("");
				}
			});
			
		}
		return btnAnnuler;
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
	// METHODES
	///////////////////////////////////

	private void fermerFenetre(){
		this.dispose();
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
