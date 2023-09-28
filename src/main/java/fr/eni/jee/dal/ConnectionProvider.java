package fr.eni.jee.dal;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * S'occupe de garder une réference vers le dataSource et de renvoyer des connexions au besoin (pour la couche DAL)
 */
public class ConnectionProvider {
	private static DataSource dataSource;
	

	static {
		Context context;
		try {
			context = new InitialContext();		
			ConnectionProvider.dataSource = (DataSource)context.lookup("java:comp/env/jdbc/pool_cnx");
		} catch (NamingException e) {
		
			e.printStackTrace();
		}
	}

	
	public static Connection getConnection() {
		
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}