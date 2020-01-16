package logic.implementationClasses;

import logic.dao.DaoFridge;
import logic.dao.DaoUser;
import logic.entity.Admin;
import logic.entity.Fridge;
import logic.entity.User;

public class SingletonInstances {
	private static SingletonInstances instance = null;
	private static Fridge myFridge;
	private static Fridge currentFridge;
	private static User currentUser;
	
	private SingletonInstances() {		
		myFridge = new Fridge();
		currentFridge = new Fridge();
		currentUser = new User();
	}
	
	public static void takeFridgeInstance() {
		DaoUser daoUser = new DaoUser();
		System.out.println( currentUser.toString());
		myFridge = daoUser.getFridgeOfUser( currentUser );
		currentFridge = myFridge;
		System.out.println( "current" + currentFridge.getID());
	}
	
	public static void takeFridgeByName( String fridgeName ) {
		DaoFridge daoFridge = new DaoFridge();
		currentFridge = daoFridge.getFridgeByName( fridgeName );
		System.out.println( currentFridge.getID());
	}
	
	public void becomeUser() {
		//become user
		User user = new User();
		user.setUsername( currentUser.getUsername() );
		user.setPassword( currentUser.getPassword() );
		user.setEmailAddress( currentUser.getEmailAddress() );
		currentUser = user;
		System.out.println( currentUser.isAdmin());		
	}
	
	public void becomeAdmin() {
		//become user
		User admin = new Admin();
		admin.setUsername( currentUser.getUsername() );
		admin.setPassword( currentUser.getPassword() );
		admin.setEmailAddress( currentUser.getEmailAddress() );
		currentUser = admin;
		System.out.println( currentUser.isAdmin());		
	}

	/**
	 * @return the currentUser
	 */
	public static User getCurrentUser() {
		return currentUser;
	}

	/**
	 * @param currentUser the currentUser to set
	 */
	public static void setCurrentUser(User currentUser) {
		SingletonInstances.currentUser = currentUser;
	}

	/**
	 * @return the currentFridge
	 */
	public static Fridge getCurrentFridge() {
		
		return currentFridge;
	}
	
	/**
	 * @return the myFridge
	 */
	public static Fridge getMyFridge() {
		
		return myFridge;
	}
	
	/**
	 * @param currentFridge the currentFridge to set
	 */
	public static void setMyFridge(Fridge myFridge) {
		SingletonInstances.myFridge = myFridge;
	}


	/**
	 * @param currentFridge the currentFridge to set
	 */
	public static void setCurrentFridge(Fridge currentFridge) {
		SingletonInstances.currentFridge = currentFridge;
	}

	
	
	public static synchronized SingletonInstances getSingletonInstance() {
		if( SingletonInstances.instance == null ) {
			SingletonInstances.instance = new  SingletonInstances();
		}
		return instance;
	}
}
