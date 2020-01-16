package logic.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import logic.entity.Food;
import logic.entity.Fridge;
import logic.entity.User;
import logic.implementationClasses.Queries;
import logic.implementationClasses.Exceptions.EmptyException;

public class DaoFridge {
	
	public void createFridge( Fridge fridge ) {
		DaoEntity daoSingleton = DaoEntity.getSingletonInstance();
		try {
			int result = Queries.insertFridge( daoSingleton.stmt , fridge);
			System.out.println( result );
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
	}
	
	public boolean checkFridgeID( Fridge fridge ) {
		DaoEntity daoSingleton = DaoEntity.getSingletonInstance();
		try{
			ResultSet rs = Queries.checkID(daoSingleton.stmt, fridge );
		
	        if (!rs.first()){ // rs empty
	        	// username non usato
	        	return true;
	        }
	    
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return false;
	}
	
	public Fridge getFridgeByName( String name ) {
		DaoEntity daoSingleton = DaoEntity.getSingletonInstance();
		Fridge fridge = new Fridge();
		try {
			ResultSet rs = Queries.selectFridgeByName( daoSingleton.stmt , name );
			
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
	
	public void createAdministration( Fridge fridge, User admin) {
		DaoEntity daoSingleton = DaoEntity.getSingletonInstance();
		try {
			int result = Queries.insertAdministration( daoSingleton.stmt , admin.getUsername(), fridge);
			System.out.println( result );
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
	}
	
	public ArrayList<Food>  getContentFridge( Fridge fridge ) throws EmptyException {
		DaoEntity daoSingleton = DaoEntity.getSingletonInstance();
		ArrayList<Food> listOfFood = new ArrayList<Food>();
		try {
			ResultSet rs = Queries.selectContentFridge( daoSingleton.stmt , fridge);
			
			if (!rs.first()){ // rs empty
            	EmptyException e = new EmptyException("Frigo vuoto!");
            	throw e;
            }
			// riposizionamento rs
			rs.first();
            do{
                // lettura delle colonne "by name"
                String foodName = rs.getString("name");
                int quantity = rs.getInt("quantity");
                String expirationDate = rs.getString("expirationDate");
                
                
                Food food = new Food();
                food.setName(foodName);
                food.setQuantity(quantity);
                food.setExpirationDate(expirationDate);
                
                listOfFood.add(food);

            }while(rs.next());
            
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return listOfFood;
	}
	
	public void updateFridgeNameInDB( Fridge fridge ) {
		DaoEntity daoSingleton = DaoEntity.getSingletonInstance();
		try {
			int result = Queries.updateFridgeName( daoSingleton.stmt , fridge.getID(), fridge.getName() );
			System.out.println( result );
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
	}
	
}
	
