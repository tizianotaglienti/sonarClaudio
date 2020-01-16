package logic.boundary;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import logic.bean.BeanRegistration;
import logic.controller.RegistrationController;
import logic.implementationClasses.Credentials;

public class RegistrationUI extends Credentials {

	public void clickedOnRegistration( String username, String emailAddress, String password ){
		BeanRegistration beanR = new BeanRegistration();
		beanR.setUsername(username);
		beanR.setEmailAddress(emailAddress);
		beanR.setPassword(password);
		RegistrationController registrationCTRL = new RegistrationController();
		registrationCTRL.registration( beanR );
	}
	
	
	public boolean notExist(String username, String emailAddress, String password) {
		if( super.notExist(username, emailAddress, password) ) return true;
		return false;
	}
	
	public boolean validSyntaxUsername( String username ) {
		if (username == null){
			return false;
		}
		if( username.length() < 4 || username.length() > 30 ) return false;
		return true;
	}
	
	public boolean validSyntaxPassword( String password ) {
		if (password == null){
			return false;
		}
		if( password.length() < 6 || password.length() > 30 ) return false;
		return true;
	}
	
	public boolean validSyntaxEmail( String mail ) {
		if (mail == null){
			return false;
		}

		Pattern p = Pattern.compile(".+@.+\\.[a-z]+", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(mail);
		boolean matchFound = m.matches();

		//Condizioni più restrittive rispetto alle precedenti
		String  expressionPlus="^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		Pattern pPlus = Pattern.compile(expressionPlus, Pattern.CASE_INSENSITIVE);
		Matcher mPlus = pPlus.matcher(mail);
		boolean matchFoundPlus = mPlus.matches();
		         
		return matchFound && matchFoundPlus;   
	}
}
