package logic.application;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	private static Stage window;
	private static Scene registrationScene, loginScene, viewFridgeScene, addFoodScene, chooseRecipesScene, shareFridgeScene, changeFridgeScene; 
	
	@Override
	public void start(Stage primaryStage) {
		try {
			window = primaryStage;
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("login.fxml"));
			loginScene = new Scene(root,1024,640);
			loginScene.getStylesheets().add(getClass().getResource("bootstrap3.css").toExternalForm());
			window.setScene(loginScene);
			window.show();	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void registrationSetScene() {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("registration.fxml"));
			registrationScene = new Scene(root,1024,640);
			registrationScene.getStylesheets().add(getClass().getResource("bootstrap3.css").toExternalForm());
			window.setScene(registrationScene);
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loginSetScene() {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("login.fxml"));
			loginScene = new Scene(root,1024,640);
			loginScene.getStylesheets().add(getClass().getResource("bootstrap3.css").toExternalForm());
			window.setScene(loginScene);
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void viewFridgeSetScene() {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("viewFridge.fxml"));
			viewFridgeScene = new Scene(root,1024,640);
			viewFridgeScene.getStylesheets().add(getClass().getResource("bootstrap3.css").toExternalForm());
			window.setScene(viewFridgeScene);
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void addFoodSetScene() {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("addFood.fxml"));
			addFoodScene = new Scene(root,1024,640);
			addFoodScene.getStylesheets().add(getClass().getResource("bootstrap3.css").toExternalForm());
			window.setScene(addFoodScene);
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void chooseRecipesSetScene() {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("chooseRecipes.fxml"));
			chooseRecipesScene = new Scene(root,1024,640);
			chooseRecipesScene.getStylesheets().add(getClass().getResource("bootstrap3.css").toExternalForm());
			window.setScene(chooseRecipesScene);
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void shareFridgeSetScene() {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("shareFridge.fxml"));
			shareFridgeScene = new Scene(root,1024,640);
			shareFridgeScene.getStylesheets().add(getClass().getResource("bootstrap3.css").toExternalForm());
			window.setScene(shareFridgeScene);
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void changeFridgeSetScene() {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("changeFridge.fxml"));
			changeFridgeScene = new Scene(root,1024,640);
			changeFridgeScene.getStylesheets().add(getClass().getResource("bootstrap3.css").toExternalForm());
			window.setScene(changeFridgeScene);
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}