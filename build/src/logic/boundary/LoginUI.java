package logic.boundary;

import logic.implementationClasses.Credentials;

public class LoginUI extends Credentials {
	
	public boolean isValid(String username, String password) {
		if( super.isValid(username, password) ) return true;
		return false;
	}	
	
}
