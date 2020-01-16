package logic.entity;

import java.util.ArrayList;


public class Fridge {
	private int ID;
	private String name;
	private ArrayList<Food> listOfFood;
	
	public Fridge() {
		this.ID = 0;
		this.name = "fridge";
		this.listOfFood = null;
	}
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
	
	
	@Override
	public String toString() {
		return String.format("fridge: " + this.ID + "\n" + "name: " + this.name + "\n" + "content: " + this.listOfFood);
	}
}
