package logic.implementationClasses;

import logic.entity.Fridge;
import logic.entity.User;

public class SaveInstances {
	
	public SaveInstances() {
		SingletonInstances.getSingletonInstance();
	}
	
	protected void saveUser( User user ) {
		SingletonInstances.setCurrentUser(user);
	}
	
	protected void saveFridge( Fridge fridge ) {
		SingletonInstances.setCurrentFridge(fridge);
	}
}
