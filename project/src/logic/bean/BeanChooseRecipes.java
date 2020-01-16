package logic.bean;

import java.util.ArrayList;
import logic.entity.Recipe;

public class BeanChooseRecipes {
	private ArrayList<Recipe> listRecipe;
	private ArrayList<String> listFood;
	private int numRecipes;

	/**
	 * @return the listFood
	 */
	public ArrayList<String> getListFood() {
		return listFood;
	}

	/**
	 * @param listFood the listFood to set
	 */
	public void setListFood(ArrayList<String> listFood) {
		this.listFood = listFood;
	}

	/**
	 * @return the numRecipes
	 */
	public int getNumRecipes() {
		return numRecipes;
	}

	/**
	 * @param numRecipes the numRecipes to set
	 */
	public void setNumRecipes(int numRecipes) {
		this.numRecipes = numRecipes;
	}

	/**
	 * @return the listRecipe
	 */
	public ArrayList<Recipe> getListRecipe() {
		return listRecipe;
	}

	/**
	 * @param listRecipe the listRecipe to set
	 */
	public void setListRecipe(ArrayList<Recipe> listRecipe) {
		this.listRecipe = listRecipe;
	}


}
