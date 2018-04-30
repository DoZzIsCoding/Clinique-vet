package fr.eni.clinique.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.Clinique;

@SuppressWarnings("serial")
public class FrameConnexion extends JFrame {

	public FrameConnexion() {
		setTitle("Connexion");
		setBounds(100, 100, 300, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		

		setContentPane(getMainPanel());
	}

	////////////////////////////////
	// MAIN PANEL
	////////////////////////////////

	private JPanel mainPanel;
	private JLabel lblNomPersonnel;
	private JTextField txtNomPersonnel;
	private JLabel lblPasswordPersonnel;
	private JPasswordField txtPasswordPersonnel;
	private JButton btnValider;

	public JPanel getMainPanel() {
		if (mainPanel == null) {
			mainPanel = new JPanel(new GridBagLayout());

			addComponentTo(getLblNomPersonnel(), mainPanel, 0, 0, 1, 1, 1, true);
			addComponentTo(getTxtNomPersonnel(), mainPanel, 1, 0, 1, 1, 1, true);
			addComponentTo(getLblPasswordPersonnel(), mainPanel, 0, 1, 1, 1, 1, true);
			addComponentTo(getTxtPasswordPersonnel(), mainPanel, 1, 1, 1, 1, 1, true);
			addComponentTo(getBtnValider(), mainPanel, 1, 2, 1, 1, 1, true);

		}
		return mainPanel;
	}

	public JLabel getLblNomPersonnel() {
		if (lblNomPersonnel == null)
			lblNomPersonnel = new JLabel("Nom");
		return lblNomPersonnel;
	}

	public JTextField getTxtNomPersonnel() {
		if (txtNomPersonnel == null)
			txtNomPersonnel = new JTextField(10);
		return txtNomPersonnel;
	}

	public JLabel getLblPasswordPersonnel() {
		if (lblPasswordPersonnel == null)
			lblPasswordPersonnel = new JLabel("Mot de passe");
		return lblPasswordPersonnel;
	}

	public JPasswordField getTxtPasswordPersonnel() {
		if (txtPasswordPersonnel == null)
			txtPasswordPersonnel = new JPasswordField(10);
		return txtPasswordPersonnel;
	}

	public JButton getBtnValider() {
		if (btnValider == null)
			btnValider = new JButton("Valider");
		
		btnValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
					try {
						Clinique.getInstance().connectionUtilisateur(getTxtNomPersonnel().getText(), String.valueOf(getTxtPasswordPersonnel().getPassword()));
						if(Clinique.getInstance().getUtilisateurConnecté() != null){
//							SwingUtilities.invokeLater(new Runnable() {
//								
//								@Override
//								public void run() {
//									FrameAccueilClinique accueilFrame = new FrameAccueilClinique();
//									accueilFrame.setVisible(true);
//									
//								}
//							});
							new FrameAccueilClinique();
						}
					} catch (BLLException e1) {
						JOptionPane.showMessageDialog(btnValider, e1.getMessage());
						e1.printStackTrace();
					}
				}
		});
		return btnValider;
	}

	//////////////////////////////////
	// METHODES
	//////////////////////////////////

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
