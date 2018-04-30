package fr.eni.clinique.ihm;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.Clinique;

@SuppressWarnings("serial")
public class FrameAnimaux extends JFrame {

	
	public FrameAnimaux() {
		setTitle("Animaux");
		setBounds(100, 100, 500, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setContentPane(getMainPanel());
		applyLookAndFeel();
	}

	////////////////////////////////
	// MAIN PANEL
	////////////////////////////////

	private JPanel mainPanel;

	public JPanel getMainPanel() {
		if (mainPanel == null) {
			mainPanel = new JPanel();

			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
			
			mainPanel.add(getBoutonsPanel());
			mainPanel.add(getNomClientPanel());
			mainPanel.add(getInfosAnimalPanel());

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

			boutonsPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

			boutonsPanel.setBorder(border);
			
			boutonsPanel.add(getBtnAnnuler());
			boutonsPanel.add(getBtnValider());
		}
		return boutonsPanel;
	}

	public JButton getBtnValider() {
		if (btnValider == null) {
			btnValider = new JButton("Valider", new ImageIcon(getClass().getResource("./resources/valider.png")));
			btnValider.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnValider.setHorizontalTextPosition(SwingConstants.CENTER);

			
			}
		return btnValider;
	}

	public JButton getBtnAnnuler() {
		if (btnAnnuler == null) {
			btnAnnuler = new JButton("Annuler", new ImageIcon(getClass().getResource("./resources/annuler.png")));
			btnAnnuler.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnAnnuler.setHorizontalTextPosition(SwingConstants.CENTER);
		}
		return btnAnnuler;
	}

	////////////////////////////////
	// NOM CLIENT PANEL
	////////////////////////////////

	
	private JPanel nomClientPanel;
	private JTextField txtNomClient;

	public JPanel getNomClientPanel(){
		if (nomClientPanel == null) {
			nomClientPanel = new JPanel();
			TitledBorder border = BorderFactory.createTitledBorder(
					BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Client :");
			
			border.setTitleJustification(TitledBorder.LEFT);
			border.setTitlePosition(TitledBorder.TOP);
			
			nomClientPanel.setBorder(border);
			nomClientPanel.add(getTxtNomClient());
			
		}
		return nomClientPanel;
	}

	public JTextField getTxtNomClient(){
		if (txtNomClient == null) {
			txtNomClient = new JTextField(30);
			
			try {
				String nomComplet = new String(Clinique.getInstance().getClientEnCours().getNomClient()
												+ " " + Clinique.getInstance().getClientEnCours().getPrenomClient());
				txtNomClient.setText(nomComplet);
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			txtNomClient.setEditable(false);
		}
		return txtNomClient;
	}

	////////////////////////////////
	// INFOS ANIMAL PANEL
	////////////////////////////////

	// VARIABLES

	private JPanel infosAnimalPanel;

	private JLabel lblCode;
	private JTextField txtCode;

	private JLabel lblNomAnimal;
	private JTextField txtNomAnimal;

	private JComboBox<String> cbbSexeAnimal;

	private JLabel lblCouleur;
	private JTextField txtCouleurAnimal;

	private JLabel lblEspeceAnimal;
	private JComboBox<String> cbbEspeceAnimal;
	private JLabel lblRaceAnimal;
	private JComboBox<String> cbbRaceAnimal;

	private JLabel lblTatouage;
	private JTextField txtTatouage;

	// GETTERS
	public JPanel getInfosAnimalPanel() {
		if(infosAnimalPanel == null){
			infosAnimalPanel = new JPanel(new GridBagLayout());			
			
			addComponentTo(getLblCode(), infosAnimalPanel, 0, 0, 1, 1, 1, true);
			addComponentTo(getTxtCode(), infosAnimalPanel, 1, 0, 2, 1, 2, true);
			addComponentTo(getLblNomAnimal(), infosAnimalPanel, 0, 1, 1, 1, 1, true);
			addComponentTo(getTxtNomAnimal(), infosAnimalPanel, 1, 1, 3, 1, 1, true);
			addComponentTo(getCbbSexeAnimal(), infosAnimalPanel, 4, 1, 1, 1, 1, true);
			addComponentTo(getLblCouleur(), infosAnimalPanel, 0, 2, 1, 1, 1, true);
			addComponentTo(getTxtCouleurAnimal(), infosAnimalPanel, 1, 2, 3, 1, 1, true);
			addComponentTo(getLblEspeceAnimal(), infosAnimalPanel, 0, 3, 1, 1, 1, true);
			addComponentTo(getCbbEspeceAnimal(), infosAnimalPanel, 1, 3, 1, 1, 1, true);
			addComponentTo(getLblRaceAnimal(), infosAnimalPanel, 2, 3, 1, 1, 1, true );
			addComponentTo(getCbbRaceAnimal(), infosAnimalPanel, 3, 3, 1, 1, 1, true);
			addComponentTo(getLblTatouage(), infosAnimalPanel, 0, 4, 1, 1, 1, true);
			addComponentTo(getTxtTatouage(), infosAnimalPanel, 1, 4, 3, 1, 1, true);
					
			
		}
		return infosAnimalPanel;
	}

	public JLabel getLblCode() {
		if (lblCode == null) {
			lblCode = new JLabel("Code");
			
		}
		return lblCode;
	}

	public JTextField getTxtCode() {
		if (txtCode == null) {
			txtCode = new JTextField(30);
			try {
				if(Clinique.getInstance().getIndexAnimalEnCours() == -1){
					chargerNouvelAnimal();
				}else{
					chargerAnimal(Clinique.getInstance().getIndexAnimalEnCours());
				}
					
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return txtCode;
	}



	public JLabel getLblNomAnimal() {
		if (lblNomAnimal == null) {
			lblNomAnimal = new JLabel("Nom");
		}
		return lblNomAnimal;
	}

	public JTextField getTxtNomAnimal() {
		if (txtNomAnimal == null) {
			txtNomAnimal = new JTextField(30);
		}
		return txtNomAnimal;
	}
	
	
	public JComboBox<String> getCbbSexeAnimal() {
		if (cbbSexeAnimal == null) {
			cbbSexeAnimal = new JComboBox<>(new String[]{"Male","Femelle","Hermaphrodite"});
		}
		return cbbSexeAnimal;
	}

	public JLabel getLblCouleur() {
		if (lblCouleur == null) {
			lblCouleur = new JLabel("Couleur");
		}
		return lblCouleur;

	}

	public JTextField getTxtCouleurAnimal() {
		if (txtCouleurAnimal == null) {
			txtCouleurAnimal = new JTextField(30);
		}
		return txtCouleurAnimal;
	}

	public JLabel getLblEspeceAnimal() {
		if (lblEspeceAnimal == null) {
			lblEspeceAnimal = new JLabel("Espèce");
		}
		return lblEspeceAnimal;
	}

	public JComboBox<String> getCbbEspeceAnimal() {
		if (cbbEspeceAnimal == null) {
			try {
				cbbEspeceAnimal = new JComboBox<>(Clinique.getInstance().getTabEspeces());
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cbbEspeceAnimal;
	}

	public JLabel getLblRaceAnimal() {
		if (lblRaceAnimal == null) {
			lblRaceAnimal = new JLabel("Race");
			lblRaceAnimal.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblRaceAnimal;
	}

	public JComboBox<String> getCbbRaceAnimal() {
		if (cbbRaceAnimal == null) {
			cbbRaceAnimal = new JComboBox<>();
		}
		return cbbRaceAnimal;
	}

	public JLabel getLblTatouage() {
		if (lblTatouage == null) {
			lblTatouage = new JLabel("Tatouage");
		}
		return lblTatouage;
	}

	public JTextField getTxtTatouage() {
		if (txtTatouage == null) {
			txtTatouage = new JTextField(30);
		}
		return txtTatouage;
	}

	///////////////////////////////////
	// METHODES
	///////////////////////////////////
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
	
	private void chargerAnimal(int indexAnimalEnCours) {
		// TODO Auto-generated method stub
	}

	private void chargerNouvelAnimal() {
		getTxtCode().setText("Nouvel Animal");
		getTxtCode().setEditable(false);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
