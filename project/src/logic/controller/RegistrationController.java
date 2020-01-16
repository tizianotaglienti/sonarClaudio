package logic.controller;

import logic.bean.BeanCredentials;
import logic.bean.BeanRegistration;
import logic.dao.DaoFridge;
import logic.dao.DaoUser;
import logic.entity.Admin;
import logic.entity.Fridge;
import logic.entity.User;
import logic.implementationClasses.SingletonInstances;
import logic.implementationClasses.ValidateCredentialsInterface;
import logic.implementationClasses.GoF.FacadeCheckUsername;

public class RegistrationController implements ValidateCredentialsInterface{
	
	public RegistrationController() {
	
	}
	
	public void registration( BeanRegistration beanR ) {
		User admin = new Admin();
		admin.setUsername(beanR.getUsername());
		admin.setEmailAddress(beanR.getEmailAddress());
		admin.setPassword(beanR.getPassword());	
		System.out.println( admin.toString() );
		
		DaoUser daoUser = new DaoUser();
		daoUser.saveRegistrationToDB(admin);
		
		DaoFridge daoFridge = new DaoFridge();
		Fridge fridge = new Fridge();
		this.setDefaultFridge( fridge );
		daoFridge.createFridge( fridge );
		daoFridge.createAdministration( fridge, admin );
		System.out.println( "riuscito: con id " + fridge.getID() );
		SingletonInstances.getSingletonInstance();
		SingletonInstances.setCurrentUser(admin);
		SingletonInstances.getSingletonInstance().becomeAdmin();
		SingletonInstances.setCurrentFridge(fridge);
		SingletonInstances.setMyFridge(fridge);

	}
	
	@Override
	public boolean checkValid( BeanCredentials beanC ) {
		FacadeCheckUsername check = new FacadeCheckUsername();
		if( check.usernameExist(beanC.getUsername())) return false;
		return true;
	}
	
	private void setDefaultFridge( Fridge fridge ) {
		DaoFridge daoFridge = new DaoFridge();
		fridge.setName("fridge");
		fridge.setListOfFood(null);
		while(true) {
			fridge.setID( (int) (Math.random() * 10000 ));		
			
			if( daoFridge.checkFridgeID(fridge) == true ) {
				return;
			}
		}
		
	}
	
	
}
