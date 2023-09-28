package fr.eni.jee.bll.manager;

import java.util.List;

import fr.eni.jee.bo.Categorie;
import fr.eni.jee.dal.DALException;
import fr.eni.jee.dal.dao.DAOCategorie;
import fr.eni.jee.dal.dao.DAOFactory;


public class CategorieManager {
	private static DAOCategorie dao;

	static {
		dao = DAOFactory.getDAOCategorie();
	}
	/**
	 * Read part of CRUD
	 */
	public List<Categorie> getAllCategories() throws DALException {
		return dao.selectAll();
	}

	public Categorie getCategoriesById(int no_Categorie) throws DALException {
		return dao.selectById(no_Categorie);

	}
//ajouter les requetes manquantes, selectall, selectbyid, + CRUD + verif !!

}
