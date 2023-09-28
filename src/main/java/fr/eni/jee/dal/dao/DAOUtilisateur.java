package fr.eni.jee.dal.dao;

import java.sql.SQLException;
import java.util.HashMap;

import fr.eni.jee.bo.Utilisateur;
import fr.eni.jee.dal.DALException;

public interface DAOUtilisateur extends DAO<Utilisateur> {
    boolean checkForUniquePseudoAndMail(String pseudo, String mail) throws DALException, SQLException;
    boolean checkForUniquePseudo(String pseudo) throws DALException, SQLException;
    boolean checkForUniqueMail(String mail) throws DALException, SQLException;
	Utilisateur selectUtilisateurByPseudo(String pseudo) throws DALException;
    Utilisateur selectById(int noUtilisateur) throws DALException;
    HashMap<Integer, String> selectUtilisateursWithEnchere() throws DALException;
    void updateCredit(int noUtilisateur, int newCredit) throws DALException;
	
	
}