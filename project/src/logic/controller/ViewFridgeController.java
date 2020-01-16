package logic.controller;

import logic.bean.BeanViewFridge;
import logic.dao.DaoFood;
import logic.dao.DaoFridge;
import logic.dao.DaoUser;
import logic.entity.Food;
import logic.entity.Fridge;
import logic.entity.User;
import logic.implementationClasses.SingletonInstances;
import logic.implementationClasses.Exceptions.EmptyException;

public class ViewFridgeController {
	
	public ViewFridgeController() {
		SingletonInstances.getSingletonInstance();
	}
	
	public BeanViewFridge takeContent() throws EmptyException {
		DaoFridge daoFridge = new DaoFridge();
		Fridge fridge = SingletonInstances.getCurrentFridge();
		fridge.setListOfFood( daoFridge.getContentFridge(fridge) );
		//set fridge in SingletonInstances
		SingletonInstances.setCurrentFridge(fridge);
		//riempire la bean dei dati 
		BeanViewFridge beanViewFridge = new BeanViewFridge();
		beanViewFridge.setID(fridge.getID());
		beanViewFridge.setName(fridge.getName());
		beanViewFridge.setListOfFood(fridge.getListOfFood());
		return beanViewFridge; 
		//mandarli alla boundary
	}

	public void changeFridgeName( String name ) {
		DaoUser daoUser = new DaoUser();
		DaoFridge daoFridge = new DaoFridge();
		User user = new User();
		user.setUsername(SingletonInstances.getCurrentUser().getUsername());
		Fridge fridge = daoUser.getFridgeOfUser(user);
		fridge.setName(name);
		SingletonInstances.setCurrentFridge(fridge);
		daoFridge.updateFridgeNameInDB( fridge );
	}
	
	
	public void removeFood ( BeanViewFridge beanViewFridge ) {
		Food food = new Food();
		food.setName(beanViewFridge.getFoodName());
		food.setQuantity(beanViewFridge.getFoodQuantity());
		int ID = SingletonInstances.getCurrentFridge().getID();
		DaoFood daoFood = new DaoFood();
		daoFood.removeFood(food, ID);	
		
	}
}
