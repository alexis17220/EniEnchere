package fr.eni.jee.bll.manager;

import fr.eni.jee.bll.BLLException;
import fr.eni.jee.bll.ErrorCodesBLL;
import fr.eni.jee.bo.Utilisateur;
import fr.eni.jee.dal.DALException;
import fr.eni.jee.dal.dao.DAOFactory;
import fr.eni.jee.dal.dao.DAOUtilisateur;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class UtilisateurManager {
	private static DAOUtilisateur dao;

	static {
		dao = DAOFactory.getDAOUtilisateur();
	}

	/**
	 * Create part of CRUD
	 * @throws SQLException 
	 */
	public void createUtilisateur(Utilisateur utilisateur) throws BLLException, DALException, SQLException {
		BLLException bllException = validateUtilisateur(utilisateur);
		if (!dao.checkForUniquePseudoAndMail(utilisateur.getPseudo(), utilisateur.getEmail())) {
			bllException.addError(ErrorCodesBLL.ERROR_PSEUDO_OR_MAIL_ALREADY_TAKEN);
		}
		if (bllException.hasErrors()) {
			throw bllException;
		} else {
			dao.insert(utilisateur);
		}
	}

	/**
	 * Read part of CRUD
	 */
	public Utilisateur getUtilisateurById(int noUtilisateur) throws DALException {
		return dao.selectById(noUtilisateur);
	}

	/**
	 * Read part of CRUD
	 */
	public Utilisateur getUtilisateurByPseudo(String pseudo) throws DALException {
		return dao.selectUtilisateurByPseudo(pseudo);
	}

	public HashMap<Integer, String> getPseudosUtilisateursWithEnchere() throws DALException {
		return dao.selectUtilisateursWithEnchere();
	}

	/**
	 * Read part of CRUD
	 */
	public List<Utilisateur> getAllUtilisateurs() throws DALException {
		return dao.selectAll();
	}

	/**
	 * Update part of CRUD
	 * @throws SQLException 
	 */
	public void updateUtilisateur(Utilisateur utilisateur, boolean checkForMail, boolean checkForUser)
			throws BLLException, DALException, SQLException {
		BLLException bllException = validateUtilisateur(utilisateur);
		if (checkForMail) {
			if (!dao.checkForUniqueMail(utilisateur.getEmail())) {
				bllException.addError(ErrorCodesBLL.ERROR_PSEUDO_OR_MAIL_ALREADY_TAKEN);
			}
		}
		if (checkForUser) {
			if (!dao.checkForUniquePseudo(utilisateur.getPseudo())) {
				bllException.addError(ErrorCodesBLL.ERROR_PSEUDO_OR_MAIL_ALREADY_TAKEN);
			}
		}

	/*	if (bllException.hasErrors()) {
			throw bllException;
		}*/ else {
			dao.update(utilisateur);
		}
	}

	public void updateCredit(int noUtilisateur, int newCredit) throws DALException {
		dao.updateCredit(noUtilisateur, newCredit);
	}

	/**
	 * Delete part of CRUD
	 */
	public void deleteUtilisateur(int id) throws DALException {
		dao.delete(id);
	}

	/**
	 * Validate the format of different fields from an instance of Utilisateurs
	 * before any actions Use regexp for email and phone number Check length of the
	 * different fields
	 */
	private BLLException validateUtilisateur(Utilisateur utilisateur) {
		String pseudoValidationRegEx = "[A-Za-z0-9]+";

		// This regexp is made from the RFC 5322 : http://www.ietf.org/rfc/rfc5322.txt
		// and has been taken from here : https://emailregex.com/
		String emailValidationRegEx = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])";

		// This regexp is made for matching only french telephone numbers.
		// format : 0101010101, no space and no special characters
		String telephoneNumberValidationRegEx = "^0[1-9][0-9]{8}$";
		BLLException bllException = new BLLException();

		if (utilisateur.getPseudo().length() > 30) {
			bllException.addError(ErrorCodesBLL.ERROR_LENGTH_PSEUDO_UTILISATEUR);
		}
		if (!Pattern.matches(pseudoValidationRegEx, utilisateur.getPseudo())) {
			bllException.addError(ErrorCodesBLL.ERROR_PSEUDO_NOT_ALPHANUMERIC);
		}
		if (utilisateur.getNom().length() > 30) {
			bllException.addError(ErrorCodesBLL.ERROR_LENGTH_NOM_UTILISATEUR);
		}
		if (utilisateur.getPrenom().length() > 30) {
			bllException.addError(ErrorCodesBLL.ERROR_LENGTH_PRENOM_UTILISATEUR);
		}
		if (utilisateur.getEmail().length() > 40) {
			bllException.addError(ErrorCodesBLL.ERROR_LENGTH_EMAIL_UTILISATEUR);
		}
		if (!Pattern.matches(emailValidationRegEx, utilisateur.getEmail())) {
			bllException.addError(ErrorCodesBLL.ERROR_FORMAT_EMAIL_UTILISATEUR);
		}
		if (utilisateur.getTelephone().length() > 15) {
			bllException.addError(ErrorCodesBLL.ERROR_LENGTH_TELEPHONE_UTILISATEUR);
		}
		if (!Pattern.matches(telephoneNumberValidationRegEx, utilisateur.getTelephone())) {
			bllException.addError(ErrorCodesBLL.ERROR_FORMAT_TELEPHONE_UTILISATEUR);
		}
		if (utilisateur.getRue().length() > 30) {
			bllException.addError(ErrorCodesBLL.ERROR_LENGTH_RUE_UTILISATEUR);
		}
		if (utilisateur.getCodePostal().length() > 30) {
			bllException.addError(ErrorCodesBLL.ERROR_LENGTH_CODE_POSTAL_UTILISATEUR);
		}
		if (utilisateur.getVille().length() > 30) {
			bllException.addError(ErrorCodesBLL.ERROR_LENGTH_VILLE_UTILISATEUR);
		}

		return bllException;
	}




}
