package fr.eni.clinique.dal;

import java.util.List;

import fr.eni.clinique.exceptions.CreneauDejaPrisException;
import fr.eni.clinique.exceptions.DalException;


public interface DAO<T> {

	/**
	 * 
	 * @param id
	 * @return l'objet correspondant à l'id ou null si n'existe pas ou si
	 *         problème à l'éxécution.
	 * @throws DalException
	 */
	public abstract T selectionnerUn(int id) throws DalException;

	/**
	 * 
	 * @return une liste d'objet jamais null
	 */
	public abstract List<T> selectionnerTout() throws DalException;

	/**
	 * 
	 * @param objet
	 *            obligatoirement non null
	 * @throws CreneauDejaPrisException 
	 * @exception NullPointerException
	 *                si l'article est null
	 */
	public abstract void ajouter(T value) throws DalException, CreneauDejaPrisException;

	/**
	 * 
	 * @param objet
	 *            obligatoirement non null
	 * @exception NullPointerException
	 *                si l'article est null
	 */
	public abstract void modifier(T value) throws DalException;

	/**
	 * 
	 * @param objet
	 *            obligatoirement non null
	 * @exception NullPointerException
	 *                si l'objet est null
	 */
	public abstract boolean supprimer(T value) throws DalException;
}
