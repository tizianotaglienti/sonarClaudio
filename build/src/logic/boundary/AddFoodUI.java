package logic.boundary;

import java.time.LocalDate;

import logic.bean.BeanAddFood;
import logic.controller.AddFoodController;

public class AddFoodUI {
	
	public AddFoodUI() {
		
	}
	
	public void clickOnInsertFood( String name, int quantity, LocalDate expirationDate) {
		BeanAddFood beanAddFood = new BeanAddFood();
		beanAddFood.setName(name);
		beanAddFood.setQuantity(quantity);
		if( expirationDate != null ) {
			beanAddFood.setExpirationDate(expirationDate.toString());
		}
		AddFoodController addFoodCTRL = new AddFoodController();
		addFoodCTRL.insertFood(beanAddFood);
	}
	
	public boolean validSemanticName( String name ) {
		AddFoodController addFoodCTRL = new AddFoodController();
		if ( addFoodCTRL.validSemanticName(name) ) return true;
		return false;
	}
	
	public boolean validSyntaxQuantity( int quantity ) {
		if( quantity == 0 ) return false;
		return true;
	}
}
