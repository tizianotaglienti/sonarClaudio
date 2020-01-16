package logic.implementationClasses.Exceptions;

public class DuplicatedUsernameException extends Exception{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicatedUsernameException(String username) {
		System.out.println("ERROR: username " + username + " already exists");
	}
}
