package logic.boundary;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.bean.BeanViewFridge;
import logic.controller.ViewFridgeController;
import logic.entity.Food;
import logic.implementationClasses.Exceptions.EmptyException;

public class ViewFridgeUI {
	
	
	public ViewFridgeUI() {
		
	}
	
	public ObservableList<Food> showContent() {
		try {
			ViewFridgeController viewFridgeCTRL = new ViewFridgeController();
			BeanViewFridge beanViewFridge = viewFridgeCTRL.takeContent();
			ArrayList<Food> list = beanViewFridge.getListOfFood();
			//prendere i dati dalla bean
	
			//mandare i dati al controller fxml
			ObservableList<Food> data = FXCollections.observableArrayList( list );
			return data;
		}catch(EmptyException ee) {
			System.out.println("empty");
		}
			return null;
	}
	
	public void clickedOnChangeNameFridge( String name ) {
		ViewFridgeController viewFridgeCTRL = new ViewFridgeController();
		viewFridgeCTRL.changeFridgeName( name );
	}
	
	public void clickedOnRemoveFood(Food food) {
		BeanViewFridge beanViewFridge = new BeanViewFridge();
		beanViewFridge.setFoodName(food.getName());
		beanViewFridge.setFoodQuantity(food.getQuantity());
		ViewFridgeController viewFridgeCTRL = new ViewFridgeController();
		viewFridgeCTRL.removeFood(beanViewFridge);	
	}
	
	
}
