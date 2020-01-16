package logic.bean;

import java.util.ArrayList;

import logic.entity.Food;

public class BeanViewFridge {
	private int ID;
	private String name;
	private ArrayList<Food> listOfFood;
	private String foodName;
	private int foodQuantity;	
	
	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}
	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the listOfFood
	 */
	public ArrayList<Food> getListOfFood() {
		return listOfFood;
	}
	/**
	 * @param listOfFood the listOfFood to set
	 */
	public void setListOfFood(ArrayList<Food> listOfFood) {
		this.listOfFood = listOfFood;
	}
	/**
	 * @return the foodName
	 */
	public String getFoodName() {
		return foodName;
	}
	/**
	 * @param foodName the foodName to set
	 */
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	/**
	 * @return the foodQuantity
	 */
	public int getFoodQuantity() {
		return foodQuantity;
	}
	/**
	 * @param foodQuantity the foodQuantity to set
	 */
	public void setFoodQuantity(int foodQuantity) {
		this.foodQuantity = foodQuantity;
	}
	
}
