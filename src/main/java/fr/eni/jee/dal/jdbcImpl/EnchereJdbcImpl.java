package fr.eni.jee.dal.jdbcImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.eni.jee.bll.manager.UtilisateurManager;
import fr.eni.jee.bo.Article;
import fr.eni.jee.bo.Enchere;
import fr.eni.jee.bo.Utilisateur;
import fr.eni.jee.dal.ConnectionProvider;
import fr.eni.jee.dal.DALException;
import fr.eni.jee.dal.ErrorCodesDAL;
import fr.eni.jee.dal.dao.DAOEnchere;

public class EnchereJdbcImpl implements DAOEnchere{
	
	private static String SELECT_AT = "SELECT * FROM `ENCHERES` WHERE no_enchere=1;";
	private static String SELECT_ALL = "SELECT E.no_enchere,E.no_article, date_enchere, montant_enchere, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente from ENCHERES E INNER JOIN ARTICLES_VENDUS A on E.no_article=A.no_article;";
	private static String INSERT = "insert into ENCHERES (date_enchere, montant_enchere, no_article, no_utilisateur) values (?, ?, ?, ?);";
	private static String UPDATE = "UPDATE ENCHERES SET date_enchere=?, montant_enchere=?, no_article=?, no_utilisateur=? where no_enchere=?;";
	private static String DELETE = "delete from ENCHERES where no_enchere=?;";
	
	private static String SELECT_ALL_INFO = "SELECT E.no_enchere, date_enchere, montant_enchere, E.no_article,E.no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, A.no_utilisateur FROM UTILISATEURS U inner join ENCHERES E on U.no_utilisateur=E.no_utilisateur INNER JOIN ARTICLES_VENDUS A on E.no_article=A.no_article WHERE E.no_article=?;";
	
	private static String SELECT_LAST_INFO = "select Max(montant) as max, nom, idU "
			+ "from (SELECT DISTINCT E.montant_enchere as montant, U.pseudo as nom, U.no_utilisateur as idU "
			+ "FROM UTILISATEURS U inner join ENCHERES E on U.no_utilisateur=E.no_utilisateur "
			+ "where no_article=? order by montant_enchere DESC ) as requete";

	/**
	 * 
	 */
	@Override
	public void insert(Enchere enchere, boolean dejaencheri) throws DALException {
		
		Connection cnx = ConnectionProvider.getConnection();
		
		try {
			//Ajout nouvelle enchere
            String INSERT = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = cnx.prepareStatement(INSERT);
            stmt.setInt(1, enchere.getArticleConcerner().getUtilisateur().getNoUtilisateur());
            stmt.setInt(2, enchere.getArticleConcerner().getNoArticle());
            stmt.setObject(3, new Timestamp(enchere.getDateEnchere().getTime()));
            stmt.setInt(4, enchere.getMontantEnchere());
           
            
            //Mise a jour du crédit de l'encherisseur
            int nouveaucredit =  enchere.getUnParticipant().getCredit() - enchere.getMontantEnchere();
            String UPDATE_ENCHERISSEUR = "UPDATE UTILISATEURS set credit=? where no_Utilisateur=?";
            PreparedStatement pStmt2 = cnx.prepareStatement(UPDATE_ENCHERISSEUR);
            pStmt2.setInt(1, nouveaucredit);
            pStmt2.setInt(2, enchere.getUnParticipant().getNoUtilisateur());
            System.out.println(pStmt2.toString());
            
            System.out.println("nouveaucredit :" + nouveaucredit + " et id a enlever : " + enchere.getUnParticipant().getNoUtilisateur());
            //Rajout de la somme a l'encherisseur max precedent si il y en a un
            if(dejaencheri) {
            	//Recuperation de l'id et credit investi
            	List<String> infoAncienBestOf = getMontantEtPseudoEtIdBestOffer(enchere.getArticleConcerner());
            	int idancienEncherisseur =Integer.parseInt(infoAncienBestOf.get(2));
            	int montantPrecedentMax = Integer.parseInt(infoAncienBestOf.get(0));
            	//Utilisateur du mana de Utilisateur pour récup le crédit actuel de l'utilisateur
            	UtilisateurManager utilMana = new UtilisateurManager();
            	int nouveauCredit = utilMana.getUtilisateurById(idancienEncherisseur).getCredit() + montantPrecedentMax; 
            	String UPDATE_ANCIEN_ENCHERISSEUR = "UPDATE UTILISATEURS set credit = ? where no_Utilisateur=?";
            	PreparedStatement pStmt3 = cnx.prepareStatement(UPDATE_ANCIEN_ENCHERISSEUR);
            	pStmt3.setInt(1, nouveauCredit);
            	pStmt3.setInt(2, idancienEncherisseur);
            	System.out.println(pStmt3.toString());
            	
            	System.out.println("ancienCredit :" + nouveauCredit + " et id a rajouter : " + idancienEncherisseur);
            	pStmt3.executeUpdate();
           }
            pStmt2.execute();
            stmt.executeUpdate();
            
            cnx.close();
        } catch (SQLException e) {
            e.printStackTrace();
            DALException dalException = new DALException();
            dalException.addError(ErrorCodesDAL.ERROR_SQL_INSERT);
            throw dalException;
        }
		
	}
	
	@Override
	public Enchere selectById(int id) throws DALException {
		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pStmt = cnx.prepareStatement(SELECT_ALL_INFO)){
			Enchere uneE = null;
			pStmt.setInt(1, id);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				
				
				boolean admin = false;
				if (rs.getInt("administrateur")==1) {
					admin = true;
				}
				uneE = new Enchere(
						rs.getInt("no_enchere"),
						rs.getDate("date_enchere"), 
						rs.getInt("montant_enchere"), 
						new Utilisateur(
						rs.getInt("no_utilisateur"),
						rs.getString("pseudo"), 
						rs.getString("nom"), 
						rs.getString("prenom"), 
						rs.getString("email"), 
						rs.getString("telephone"), 
						rs.getString("rue"), 
						rs.getString("code_postal"), 
						rs.getString("ville"), 
						rs.getString("mot_de_passe"), 
						rs.getInt("credit"),admin), 
						new Article(
						rs.getInt("no_article"), 
						rs.getString("nom_article"), 
						rs.getString("description"), 
						rs.getString("date_debut_encheres"), 
						rs.getString("date_fin_encheres"), 
						rs.getInt("prix_initial"),
						rs.getInt("prix_vente"),
						null,
						null, ""));
			}
			return uneE;
		}catch(SQLException e) {e.printStackTrace();}
		return null;
	}
	
	@Override
	public List<Enchere> selectAll() throws DALException {
		try(Connection cnx = ConnectionProvider.getConnection();
				Statement stmt = cnx.createStatement()){
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			List<Enchere> lesencheres = new ArrayList<Enchere>();
			while(rs.next()) {
				
				lesencheres.add(
						new Enchere(
						rs.getDate("date_enchere"), 
						rs.getInt("montant_enchere"), 
						null, 
						new Article(
						rs.getInt("no_article"), 
						rs.getString("nom_article"), 
						rs.getString("description"), 
						rs.getString("date_debut_encheres"), 
						rs.getString("date_fin_encheres"), 
						rs.getInt("prix_initial"),
						rs.getInt("prix_vente"),
						null,
						null, "")));
			}
			return lesencheres;
		}catch(SQLException e) {e.printStackTrace();}
		return null;
	}

	@Override
	public List<Enchere> selectAll(int idA) throws DALException {
		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pStmt = cnx.prepareStatement(SELECT_ALL_INFO)){
			List<Enchere> listE = new ArrayList<Enchere>();
			pStmt.setInt(1, idA);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				
				
				boolean admin = false;
				if (rs.getInt("administrateur")==1) {
					admin = true;
				}
				listE.add(
						new Enchere(
						rs.getDate("dateEnchere"), 
						rs.getInt("montant_enchere"), 
						new Utilisateur(
						rs.getInt("no_utilisateur"),
						rs.getString("pseudo"), 
						rs.getString("nom"), 
						rs.getString("prenom"), 
						rs.getString("email"), 
						rs.getString("telephone"), 
						rs.getString("rue"), 
						rs.getString("code_postal"), 
						rs.getString("ville"), 
						rs.getString("mot_de_passe"), 
						rs.getInt("credit"),admin), 
						new Article(
						rs.getInt("no_article"), 
						rs.getString("nom_article"), 
						rs.getString("description"), 
						rs.getString("date_debut_encheres"), 
						rs.getString("date_fin_encheres"), 
						rs.getInt("prix_initial"),
						rs.getInt("prix_vente"),
						null,
						null, "")));
			}
			return listE;
		}catch(SQLException e) {e.printStackTrace();}
		return null;
	}
	
	@Override
	public List<String> getMontantEtPseudoEtIdBestOffer(Article article) throws DALException {
		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pStmt = cnx.prepareStatement(SELECT_LAST_INFO)){
			List<String> info = new ArrayList<String>();
			pStmt.setInt(1, article.getNoArticle());
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				System.out.println("resultat");
				info.add(rs.getString("max"));
				info.add(rs.getString("nom"));
				info.add(rs.getString("idU"));
			}
			return info;
		}catch(SQLException e) {e.printStackTrace();}
		return null;
	}

	@Override
	public void update(Enchere var) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws DALException {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	@Override
	public List<Integer> getNoArticlesByUtilisateurAndEtat(Utilisateur utilisateur, String state) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getNoArticlesGagneByUtilisateur(Utilisateur utilisateur) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Enchere var) throws DALException {
		// TODO Auto-generated method stub
		
	}


	



}
