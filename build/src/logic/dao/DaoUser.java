package logic.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import logic.entity.Fridge;
import logic.entity.User;
import logic.implementationClasses.Queries;
import logic.implementationClasses.Exceptions.EmptyException;

public class DaoUser{
	
	
	public void saveRegistrationToDB(User user) {
		DaoEntity daoSingleton = DaoEntity.getSingletonInstance();
		try {
			int result = Queries.insertUser( daoSingleton.stmt , user);
			System.out.println( result );
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
		
	}
	
	public boolean checkValidUsername( User user ) {
		DaoEntity daoSingleton = DaoEntity.getSingletonInstance();
		try{
			ResultSet rs = Queries.checkUsernameExistence(daoSingleton.stmt, user.getUsername() );
		
	        if (!rs.first()){ // rs empty
	        	// username non usato
	        	return false;
	        }
	    
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return true;
	}
	
	public boolean checkValidEmail( User user ) {
		DaoEntity daoSingleton = DaoEntity.getSingletonInstance();
		try{
			ResultSet rs = Queries.checkEmailExistence(daoSingleton.stmt, user.getEmailAddress() );
		
	        if (!rs.first()){ // rs empty
	        	// username non usato
	        	return false;
	        }
	    
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return true;
	}
	
	public String takePassword ( User user ) {
		DaoEntity daoSingleton = DaoEntity.getSingletonInstance();
		try {
			ResultSet rs = Queries.selectPassword( daoSingleton.stmt, user.getUsername() );
			
			rs.first();
			return rs.getString("password");
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return null;
	}
	
	public String takeEmailFromDB( User user ) {
		DaoEntity daoSingleton = DaoEntity.getSingletonInstance();
		try {
			ResultSet rs = Queries.selectEmailAddress( daoSingleton.stmt, user.getUsername() );
			rs.first();
			
			return rs.getString("emailAddress");
			
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return null;
	}
	
	public Fridge getFridgeOfUser( User user ){
		DaoEntity daoSingleton = DaoEntity.getSingletonInstance();
		Fridge fridge = new Fridge();
		try {
			ResultSet rs = Queries.selectFridge( daoSingleton.stmt , user.getUsername() );
			
			// riposizionamento rs
			rs.first();
            System.out.println(rs.getInt("ID"));
           
            fridge.setID(rs.getInt("ID") );
            fridge.setName(rs.getString("name") );
            
            return fridge;
            
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return fridge;
	}
	
	public void deleteUser( User user ) {
		DaoEntity daoSingleton = DaoEntity.getSingletonInstance();
		try {
			int result = Queries.deleteUserForUsername( daoSingleton.stmt , user.getUsername() );
			System.out.println( result );
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
	}
	
	public ArrayList<Fridge> takeFridgesFromDB( User user ) throws EmptyException{
		DaoEntity daoSingleton = DaoEntity.getSingletonInstance();
		ArrayList<Fridge> listOfFridges = new ArrayList<Fridge>();
		try {
			ResultSet rs = Queries.selectMyFridges( daoSingleton.stmt , user.getUsername());
			
			if (!rs.first()){ // rs empty
            	EmptyException e = new EmptyException("Frigo vuoto!");
            	throw e;
            }
			
			// riposizionamento rs
			rs.first();
            do{
                // lettura delle colonne "by name"
                int ID = rs.getInt("ID");
                String name = rs.getString("name");
                
                
                Fridge fridge = new Fridge();
                fridge.setID(ID);
                fridge.setName(name);
                
                listOfFridges.add(fridge);

            }while(rs.next());
            return listOfFridges;
            
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return listOfFridges;
	}
	
	public int countMembership( User user ) {
		DaoEntity daoSingleton = DaoEntity.getSingletonInstance();
		try {
			int count = 0;
			ResultSet rs = Queries.countMembershipOfUser( daoSingleton.stmt , user.getUsername() );
			rs.first();
			do{
				count++;
			}while( rs.next() );
			return count;
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
			return 0;
	}
	
	
	
	
	
	
	
}
