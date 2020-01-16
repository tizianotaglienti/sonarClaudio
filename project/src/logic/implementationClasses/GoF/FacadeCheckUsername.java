package logic.implementationClasses.GoF;

import logic.dao.DaoUser;
import logic.entity.User;

public class FacadeCheckUsername {
	
	public boolean usernameExist( String username ) {
		User user = new User();
		user.setUsername(username);
		DaoUser daoUser = new DaoUser();
		if( daoUser.checkValidUsername(user) ) return true;
		return false;
	}
	
	public boolean matchUserPass( String username, String password ) {
		User user = new User();
		user.setUsername(username);
		DaoUser daoUser = new DaoUser();
		if( password.equals( daoUser.takePassword(user)) ) {
			return true;
		}
		return false;
	}
	
}
