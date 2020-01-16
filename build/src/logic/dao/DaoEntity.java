package logic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoEntity {
	private static DaoEntity instance = null; 
	protected static String USER = "root";
	protected static String PASS = "zurigo70";
	protected static String DB_URL = "jdbc:mysql://localhost:3306/TheFridgeDB";
	protected static String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	protected static String databaseName = "TheFridgeDB";
	protected Statement stmt;
	protected Connection conn;
	
	private DaoEntity() {
		// STEP 1: dichiarazioni
        this.stmt = null;
        this.conn = null;
		try {
			// STEP 2: loading dinamico del driver mysql
            Class.forName(DRIVER_CLASS_NAME);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	protected synchronized static DaoEntity getSingletonInstance() {
		if (DaoEntity.instance == null)
			DaoEntity.instance = new DaoEntity();		
		return instance;
	}
}
