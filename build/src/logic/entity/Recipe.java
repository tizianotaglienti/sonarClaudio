package logic.entity;

import java.util.ArrayList;

public class Recipe {
	private ArrayList<String> listFoodName;
	private String link;
	/**
	 * @return the listFoodName
	 */
	public ArrayList<String> getListFoodName() {
		return listFoodName;
	}
	/**
	 * @param listFoodName the listFoodName to set
	 */
	public void setListFoodName(ArrayList<String> listFoodName) {
		this.listFoodName = listFoodName;
	}
	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}
	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}
	
	@Override
	public String toString() {
		return String.format("ricetta: " + this.link + "\n" + "ingredienti: " + this.listFoodName.get(0) + "\n" + this.listFoodName.get(1) + "\n" + this.listFoodName.get(2) ) ;
	}

}
