package logic.implementationClasses.GoF;

import logic.controller.LoginController;
import logic.controller.RegistrationController;
import logic.implementationClasses.ValidateCredentialsInterface;

public class FactorySign {
	
	public ValidateCredentialsInterface createSignCTRL( int type ) {
		switch(type) {
			case 1: return new LoginController();
			case 2: return new RegistrationController();
		}
		return null;
	}
	
}
