package logic.boundary;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.bean.BeanChooseRecipes;
import logic.controller.ChooseRecipesController;
import logic.entity.Recipe;
import logic.implementationClasses.Exceptions.EmptyException;

public class ChooseRecipesUI {
	private ArrayList<String> links;
	private ArrayList<String> ingredients;
	
	public ChooseRecipesUI() {
		this.links = new ArrayList<String>();
		this.ingredients = new ArrayList<String>();
	}
	
	public ObservableList<String> getListFood(){
		try {
			ChooseRecipesController chooseRecipesCTRL = new ChooseRecipesController();
			BeanChooseRecipes beanChooseRecipes = chooseRecipesCTRL.takeFood();
			ArrayList<String> list = beanChooseRecipes.getListFood();
			
			ObservableList<String> data = FXCollections.observableArrayList( list );				
			
			return data;
		}catch( EmptyException ee ) {
			System.out.println("empty");
		 }
			return null;
	}
	
	public void clickedOnStartSearch( ArrayList<String> listEliminatedIngredients , int numRecipes ) {
		BeanChooseRecipes beanChooseRecipes = new BeanChooseRecipes();
		beanChooseRecipes.setListFood(listEliminatedIngredients);
		beanChooseRecipes.setNumRecipes(numRecipes);
		ChooseRecipesController chooseRecipesCTRL = new ChooseRecipesController();		
		beanChooseRecipes = chooseRecipesCTRL.startSearch(beanChooseRecipes);
		//passare la lista di ricette al controllerFXML
		System.out.println(beanChooseRecipes.getListRecipe().get(0).toString());
		for(Recipe recipe : beanChooseRecipes.getListRecipe() ) {
			this.links.add(recipe.getLink());
			this.ingredients.add(recipe.getListFoodName().get(0));
			this.ingredients.add(recipe.getListFoodName().get(1));
			this.ingredients.add(recipe.getListFoodName().get(2));
		}
		return;
	}
	
	public ArrayList<String> getLinks(){
		return this.links;
	}
	
	public ArrayList<String> getIngredients(){
		return this.ingredients;
	}
}
