package fr.eni.clinique.ihm;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bo.RDV;

public class RDVTableModel extends AbstractTableModel {
	private String[] nomsColonne = { "Heure", "Nom du client", "Animal", "Race"};

	private List<RDV> rendezVous = new ArrayList<>();

	public RDVTableModel() throws BLLException {
			//updateData();
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
		return 10;
		//rendezVous.size();
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
		Object value = null;

		if (rowIndex >= 0 && rowIndex < rendezVous.size()) {
			RDV rdvAAfficher = rendezVous.get(rowIndex);
			switch (columnIndex) {
			case 0:
				value = "10h00";
			case 1:
				value = "Dupont";
				break;
			case 2:
				value = "Filou" ;
				break;
			case 3:
				value = "Border";
				break;
			}
		}

		return value;
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
