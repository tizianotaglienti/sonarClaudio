package logic.implementationClasses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import logic.entity.Food;
import logic.entity.Fridge;
import logic.entity.User;

public class Queries {

	public static int insertUser(Statement stmt, User user ) throws SQLException  {
        String insertStatement = String.format("INSERT INTO User (username, emailAddress, password) VALUES ('%s', '%s', '%s')", user.getUsername(), user.getEmailAddress(), user.getPassword( ));
        System.out.println(insertStatement);
        return stmt.executeUpdate(insertStatement);
    }
	
	public static ResultSet checkUsernameOrEmailExistence(Statement stmt, String name, String email) throws SQLException  {
        String sql = "SELECT * FROM User where username = '" + name + "' or emailAddress = '" + email + "';";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }
	
	public static ResultSet selectPassword(Statement stmt, String name ) throws SQLException  {
        String sql = "SELECT password FROM User where username = '" + name + "';";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }
	
	public static ResultSet selectEmailAddress(Statement stmt, String username ) throws SQLException  {
        String sql = String.format("SELECT emailAddress FROM User WHERE username = '%s' ;", username );
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }
	
	public static int insertFridge(Statement stmt, Fridge fridge) throws SQLException {
		String insertStatement = String.format("INSERT INTO Fridge (ID, name) VALUES ('%d', '%s')", fridge.getID(), fridge.getName() );
        System.out.println(insertStatement);
        return stmt.executeUpdate(insertStatement);
	}
	
	public static int insertAdministration(Statement stmt, String name, Fridge fridge) throws SQLException {
		String insertStatement = String.format("INSERT INTO Administration (user, fridge) VALUES ('%s', '%d')",  name , fridge.getID()  );
        System.out.println(insertStatement);
        return stmt.executeUpdate(insertStatement);
	}
	
	public static ResultSet checkID(Statement stmt, Fridge fridge ) throws SQLException  {
        String sql = "SELECT * FROM Fridge where ID = '" + fridge.getID() + "';";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }
	
	public static int insertFoodInTheFridge(Statement stmt, Food food, Fridge fridge) throws SQLException {
		String insertStatement = String.format("INSERT INTO Food (name, fridge, quantity, expirationDate) VALUES ('%s', '%d', '%d', '%s')", food.getName(), fridge.getID(), food.getQuantity(), food.getExpirationDate() );
        System.out.println(insertStatement);
        return stmt.executeUpdate(insertStatement);
	}
	
	public static int removeFoodInTheFridge(Statement stmt, Food food, int ID) throws SQLException {
		String removeStatement = String.format("DELETE FROM Food WHERE name = '%s' and fridge = '%d' and quantity = '%d' ;", food.getName(), ID, food.getQuantity() );
        System.out.println(removeStatement);
        return stmt.executeUpdate(removeStatement);
	}
	
	
	public static ResultSet selectContentFridge(Statement stmt, Fridge fridge) throws SQLException  {
        String sql = String.format("SELECT name, quantity, expirationDate FROM Food WHERE fridge = '%d' ;", fridge.getID() );
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }
	
	public static ResultSet selectFridge(Statement stmt, String username ) throws SQLException  {
        String sql = String.format("SELECT ID, name FROM Fridge INNER JOIN Administration ON Administration.fridge = Fridge.ID WHERE Administration.user = '%s' ;", username );
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }
	
	public static ResultSet selectFridgeByName(Statement stmt, String name ) throws SQLException  {
        String sql = String.format("SELECT ID, name FROM Fridge WHERE name = '%s' ;", name );
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }
	
	public static ResultSet checkUsernameExistence(Statement stmt, String name ) throws SQLException  {
        String sql = String.format("SELECT username FROM User WHERE username = '" + name + "' ;" );
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }
	
	public static ResultSet checkEmailExistence(Statement stmt, String email ) throws SQLException {
        String sql = String.format("SELECT emailAddress FROM User WHERE emailAddress = '" + email + "' ;" );
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }
	
	public static int insertInvitation( Statement stmt, String invitingUsername , String invitedUsername, String message ) throws SQLException {
		String insertStatement = String.format("INSERT INTO Invitation ( invitingUser , invitedUser, message ) VALUES ('%s', '%s', '%s')",  invitingUsername , invitedUsername , message );
        System.out.println(insertStatement);
        return stmt.executeUpdate(insertStatement);
	}
	
	public static int deleteUserForUsername(Statement stmt, String name) throws SQLException  {
        String deleteStatement = String.format("DELETE FROM  User  WHERE username = '%s'", name );
        System.out.println(deleteStatement);
        return stmt.executeUpdate(deleteStatement);
    }
	
	public static ResultSet selectInvitations(Statement stmt, String username ) throws SQLException  {
        String sql = String.format("SELECT invitingUser, invitedUser, message FROM Invitation WHERE invitedUser = '%s' ;", username );
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }
	
	public static int deleteInvitation(Statement stmt, String invitingUser, String invitedUser) throws SQLException  {
        String deleteStatement = String.format("DELETE FROM  Invitation  WHERE invitingUser = '%s' and invitedUser = '%s' ;", invitingUser, invitedUser );
        System.out.println(deleteStatement);
        return stmt.executeUpdate(deleteStatement);
    }
	
	public static int insertMembership(Statement stmt, String username, int ID ) throws SQLException {
		String insertStatement = String.format("INSERT INTO Membership (user, fridge) VALUES ('%s', '%d') ;", username, ID );
        System.out.println(insertStatement);
        return stmt.executeUpdate(insertStatement);
	}
	
	public static int updateFridgeName(Statement stmt, int ID, String name ) throws SQLException  {
        String updateStatement = String.format("UPDATE  Fridge set name = '%s' WHERE ID = '%d' ;",  name, ID );
        System.out.println(updateStatement);
        return stmt.executeUpdate(updateStatement);
    }
	
	public static ResultSet selectMyFridges(Statement stmt, String username ) throws SQLException  {
        String sql = String.format("SELECT ID, name FROM Fridge INNER JOIN Membership ON Fridge.ID = Membership.fridge WHERE Membership.user = '%s' ;", username );
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }
	
	public static ResultSet countMembershipOfUser(Statement stmt, String name ) throws SQLException  {
        String sql = String.format("SELECT * FROM Membership WHERE user = '%s' ;", name );
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }
	
	
}