package logic.dao;

import java.sql.SQLException;

import logic.entity.Food;
import logic.entity.Fridge;
import logic.implementationClasses.Queries;

public class DaoFood {
	
	public void saveFood( Food food, Fridge fridge) {
		DaoEntity daoSingleton = DaoEntity.getSingletonInstance();
		try {
			int result = Queries.insertFoodInTheFridge( daoSingleton.stmt , food, fridge);
			System.out.println( result );
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
	}
	
	public void removeFood (Food food, int ID) {
		DaoEntity daoSingleton = DaoEntity.getSingletonInstance();
		try {
			int result = Queries.removeFoodInTheFridge( daoSingleton.stmt , food, ID);
			System.out.println( result );
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
	}
}
