package fr.eni.jee.dal.jdbcImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.eni.jee.bo.Utilisateur;
import fr.eni.jee.dal.ConnectionProvider;
import fr.eni.jee.dal.DALException;
import fr.eni.jee.dal.dao.DAOUtilisateur;

public class UtilisateurJdbcImpl implements DAOUtilisateur {

	private final String USER = "SELECT DISTINCT * FROM UTILISATEURS WHERE no_Utilisateur = ?";
	private static String ALL_USER = "SELECT DISTINCT * FROM UTILISATEURS;";
	private static String DELETE = "delete from UTILISATEURS where no_Utilisateur=?;";
	private static String UPDATE_USER = "UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=? WHERE no_Utilisateur = ?";
	private static String INSERT_USER = "INSERT INTO UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static String SELECT_USER_PSEUDO = "SELECT DISTINCT * FROM UTILISATEURS where pseudo=?;";
	private static String CHECK_PSEUDO = "SELECT DISTINCT pseudo FROM UTILISATEURS WHERE pseudo LIKE ?";
	private static String CHECK_MAIL = "SELECT DISTINCT email FROM UTILISATEURS WHERE email LIKE ?";
	private static String CHECK_PSEUDO_AND_MAIL ="SELECT DISTINCT pseudo, email FROM UTILISATEURS WHERE pseudo LIKE ? AND email LIKE ?"; 
	
	@Override
	public void insert(Utilisateur utilisateur) throws DALException {

		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pStmt = cnx.prepareStatement(INSERT_USER);) {
			// on change le ? de notre requ�te avec les informations saisie par user pass�
			// en argument
			pStmt.setString(1, utilisateur.getPseudo());
			pStmt.setString(2, utilisateur.getNom());
			pStmt.setString(3, utilisateur.getPrenom());
			pStmt.setString(4, utilisateur.getEmail());
			pStmt.setString(5, utilisateur.getTelephone());
			pStmt.setString(6, utilisateur.getRue());
			pStmt.setString(7, utilisateur.getCodePostal());
			pStmt.setString(8, utilisateur.getVille());
			pStmt.setString(9, utilisateur.getMotDePasse());
			pStmt.setInt(10, 130);

			int admin;
			if (utilisateur.isAdministrateur()) {
				admin = 1;
			} else
				admin = 0;

			pStmt.setInt(11, admin);
			// on envoie la requ�te
			pStmt.execute();
			
			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Utilisateur selectById(int noUtilisateur) throws DALException {
		// on utilise un bloc try-with-resources pour liberer automatiquement les
		// ressources connection / statements
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pStmt = cnx.prepareStatement(USER);) {
			// on change le ? de notre requ�te avec le numero utilisateur du utilisateur
			// pass� en argument
			pStmt.setInt(1, noUtilisateur);
			// on r�cup�re dans un ResultSet le resultat de la requete SQL
			ResultSet rs = pStmt.executeQuery();

			// on renvoie le premier element de notre ResultSet (le seul normalement)
			if (rs.next()) {
				Utilisateur utilisateur = new Utilisateur(
						rs.getInt("no_Utilisateur") ,
						rs.getString("pseudo"), 
						rs.getString("nom"),
						rs.getString("prenom"), 
						rs.getString("email"), 
						rs.getString("telephone"), 
						rs.getString("rue"),
						rs.getString("code_postal"), 
						rs.getString("ville"), 
						rs.getString("mot_de_passe"),
						rs.getInt("credit"), 
						rs.getBoolean("administrateur"));
				return utilisateur;
				
			}
			cnx.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
//A TESTER !!	
	@Override
	public Utilisateur selectUtilisateurByPseudo(String pseudo) throws DALException {
		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pStmt = cnx.prepareStatement(SELECT_USER_PSEUDO)){
			pStmt.setString(1, pseudo);
			Utilisateur util = null;
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				util = new Utilisateur(rs.getInt("no_Utilisateur"), rs.getString("pseudo"),
						rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"),
						rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"),
						rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur"));
			}
			return util;
		}catch(SQLException e) {e.printStackTrace();}
		return null;
	}
	
	@Override
	public List<Utilisateur> selectAll() throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection(); Statement stmt = cnx.createStatement();) {
			List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
			ResultSet rs = stmt.executeQuery(ALL_USER);
			while (rs.next()) {
				utilisateurs.add(new Utilisateur(rs.getInt("no_Utilisateur"), rs.getString("pseudo"),
						rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"),
						rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"),
						rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur")));
			}
			return utilisateurs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(Utilisateur user) throws DALException {
		// on utilise un bloc try-with-resources pour lib�rer automatiquement les
		// ressources connection / statements
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pStmt = cnx.prepareStatement(UPDATE_USER);) {
			// on change les ? de notre requ�te
			pStmt.setString(1, user.getPseudo());
			pStmt.setString(2, user.getNom());
			pStmt.setString(3, user.getPrenom());
			pStmt.setString(4, user.getEmail());
			pStmt.setString(5, user.getTelephone());
			pStmt.setString(6, user.getRue());
			pStmt.setString(7, user.getCodePostal());
			pStmt.setString(8, user.getVille());
			pStmt.setString(9, user.getMotDePasse());

			pStmt.setInt(10, user.getNoUtilisateur());
			pStmt.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void delete(int id) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pStmt = cnx.prepareStatement(DELETE)) {
			pStmt.setInt(1, id);
			pStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
//A COMPLETER ET TESTER !!!
	@Override
	public boolean checkForUniquePseudoAndMail(String pseudo, String mail) throws DALException, SQLException {
		
		Connection cnx = ConnectionProvider.getConnection();
		boolean isOK = true;
		
		PreparedStatement pStmt = cnx.prepareStatement(CHECK_PSEUDO_AND_MAIL);
		
		pStmt.setString(1, pseudo);
		pStmt.setString(2, mail);
		pStmt.execute();
		ResultSet rs = pStmt.executeQuery();
		if(rs.next()) {
			return isOK = false;
		}
			
		return isOK;
	}

	@Override
	public boolean checkForUniquePseudo(String pseudo) throws DALException, SQLException {
		
		Connection cnx = ConnectionProvider.getConnection();
		boolean isUnique=true;
		PreparedStatement pStmt = cnx.prepareStatement(CHECK_PSEUDO);
		
		pStmt.setString(1, pseudo);
		pStmt.execute();
		
		ResultSet rs = pStmt.executeQuery();
		if (rs.next()) {
			
			
				return isUnique=false;
			
		}
		
	
		
		
		return isUnique;
	}

	@Override
	public boolean checkForUniqueMail(String mail) throws DALException, SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		boolean unique=true;
		PreparedStatement pStmt = cnx.prepareStatement(CHECK_MAIL);
		
		pStmt.setString(1, mail);
		pStmt.execute();
		
		ResultSet rs = pStmt.executeQuery();
		if (rs.next()) {
			
			
				return unique=false;
			
		}
		
	
		
		
		return unique;
	
	}
		
	@Override
	public HashMap<Integer, String> selectUtilisateursWithEnchere() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCredit(int noUtilisateur, int newCredit) throws DALException {
		// TODO Auto-generated method stub

	}
}
