package logic.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import logic.entity.Fridge;
import logic.entity.Invitation;
import logic.implementationClasses.Queries;
import logic.implementationClasses.Exceptions.EmptyException;

public class DaoInvitation {
	
	public void sendInvitationDB( Invitation invitation ) {
		DaoEntity daoSingleton = DaoEntity.getSingletonInstance();
		try {
			int result = Queries.insertInvitation( daoSingleton.stmt , invitation.getInvitingUser(), invitation.getInvitedUser(), invitation.getMessage() );
			System.out.println( result );
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
	}
	
	public ArrayList<Invitation> takeInvitationsFromDB( String username ) throws EmptyException {
		DaoEntity daoSingleton = DaoEntity.getSingletonInstance();
		ArrayList<Invitation> invitationList = new ArrayList<Invitation>();
		try {
			ResultSet rs = Queries.selectInvitations( daoSingleton.stmt , username );
			
			if (!rs.first()){ // rs empty
            	EmptyException e = new EmptyException("Frigo vuoto!");
            	throw e;
            }
			// riposizionamento rs
			rs.first();
            do{
                // lettura delle colonne "by name"
                String invitingUser = rs.getString("invitingUser");
                String invitedUser = rs.getString("invitedUser");
                String message = rs.getString("message");
                
                Invitation invitation = new Invitation();
                invitation.setInvitingUser(invitingUser);
                invitation.setInvitedUser(invitedUser);
                invitation.setMessage(message);
                
                invitationList.add(invitation);

            }while(rs.next());
            
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return invitationList;
	}
	
	public void deleteInvitationFromDB( Invitation invitation ) {
		DaoEntity daoSingleton = DaoEntity.getSingletonInstance();
		try {
			
			int result = Queries.deleteInvitation( daoSingleton.stmt , invitation.getInvitingUser(), invitation.getInvitedUser() );
			System.out.println( result );

		}catch(SQLException se) {
			se.printStackTrace();
		}
	}
	
	public void startMembership( Invitation invitation, Fridge fridge ) {
		DaoEntity daoSingleton = DaoEntity.getSingletonInstance();
		try {
			int result = Queries.insertMembership( daoSingleton.stmt , invitation.getInvitedUser(), fridge.getID() );
			System.out.println( result );
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
	}
}
