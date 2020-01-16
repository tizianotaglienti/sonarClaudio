package logic.bean;

import java.util.ArrayList;
import logic.entity.Invitation;

public class BeanChangeFridge {
	private String fridgeName;
	private Invitation invitation;
	private ArrayList<Invitation> invitationList;
	private ArrayList<String> listOfNameFridges;
	

	/**
	 * @return the listOfNameFridges
	 */
	public ArrayList<String> getListOfNameFridges() {
		return listOfNameFridges;
	}

	/**
	 * @param listOfNameFridges the listOfNameFridges to set
	 */
	public void setListOfNameFridges(ArrayList<String> listOfNameFridges) {
		this.listOfNameFridges = listOfNameFridges;
	}

	/**
	 * @return the invitation
	 */
	public Invitation getInvitation() {
		return invitation;
	}

	/**
	 * @param invitation the invitation to set
	 */
	public void setInvitation(Invitation invitation) {
		this.invitation = invitation;
	}

	/**
	 * @return the invitationList
	 */
	public ArrayList<Invitation> getInvitationList() {
		return invitationList;
	}

	/**
	 * @param invitationList the invitationList to set
	 */
	public void setInvitationList(ArrayList<Invitation> invitationList) {
		this.invitationList = invitationList;
	}

	/**
	 * @return the fridgeName
	 */
	public String getFridgeName() {
		return fridgeName;
	}

	/**
	 * @param fridgeName the fridgeName to set
	 */
	public void setFridgeName(String fridgeName) {
		this.fridgeName = fridgeName;
	}
}
