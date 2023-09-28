package fr.eni.jee.dal.jdbcImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.jee.bo.Categorie;
import fr.eni.jee.dal.ConnectionProvider;
import fr.eni.jee.dal.DALException;
import fr.eni.jee.dal.dao.DAOCategorie;

public class CategorieJdbcImpl implements DAOCategorie {
	
	private static String ALL_CAT = "SELECT DISTINCT * FROM CATEGORIES;";
	
	private static String CAT ="SELECT DISTINCT no_categorie, libelle FROM CATEGORIES WHERE no_categorie = ?";

	@Override
	public void insert(Categorie var) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Categorie selectById(int no_Categorie) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pStmt = cnx.prepareStatement(CAT);) {
			// on change le ? de notre requ�te avec le numero utilisateur du utilisateur
			// pass� en argument
			pStmt.setInt(1, no_Categorie);
			// on r�cup�re dans un ResultSet le resultat de la requete SQL
			ResultSet rs = pStmt.executeQuery();

			// on renvoie le premier element de notre ResultSet (le seul normalement)
			if (rs.next()) {
				Categorie categorie = new Categorie(rs.getInt("no_Categorie") ,rs.getString("libelle"));
				return categorie;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Categorie> selectAll() throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection(); Statement stmt = cnx.createStatement();) {
			List<Categorie> categorie = new ArrayList<Categorie>();
			ResultSet rs = stmt.executeQuery(ALL_CAT);
			while (rs.next()) {
				categorie.add(new Categorie(rs.getInt("no_Categorie"), rs.getString("libelle")));
			}
			return categorie;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(Categorie var) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws DALException {
		// TODO Auto-generated method stub
		
	}
}
