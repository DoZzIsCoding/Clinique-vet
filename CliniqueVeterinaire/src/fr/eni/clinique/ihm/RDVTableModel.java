package fr.eni.clinique.ihm;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bo.RDV;

public class RDVTableModel extends AbstractTableModel {
	private String[] nomsColonne = { "Heure", "Nom du client", "Animal", "Race"};

	private List<RDV> rendezVous = new ArrayList<>();

	public RDVTableModel() {
			rendezVous.add(new RDV(Date.from(Instant.now()) , "Nom", "nomAnimal", "especeAnimal"));
	}


	/**
	 * Cette méthode permet de mettre à jour l'affichage de la JTable (en
	 * rechargeant les données)
	 * 
	 * @throws BLLException
	 */
	/*public void updateData() throws BLLException {
		articles = Catalogue.getInstance().getCatalogue();
		fireTableDataChanged();
	}
*/
	
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
				return rdvAAfficher.getDate();
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
	/*public RDV getValueAt(int rowIndex) throws ArticleNotFoundException {
		if (rowIndex >= 0 && rowIndex < 10) {
			return rdv.get(rowIndex);
		}
		throw new ArticleNotFoundException();
	}*/

}
