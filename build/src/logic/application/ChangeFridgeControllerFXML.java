package logic.application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.boundary.ChangeFridgeUI;
import logic.entity.Invitation;
import logic.implementationClasses.SingletonInstances;
import logic.implementationClasses.Exceptions.TooManyFridgesException;



public class ChangeFridgeControllerFXML implements Initializable {
	private static Stage deletionWindow;
	private static ChangeFridgeUI changeFridgeUI;
	private ArrayList<String> listName;
	
	@FXML
	private Label alertLabel;
	
	@FXML
	private BorderPane borderpane;
	
    @FXML
    private JFXHamburger hamburger;
    
    @FXML
    private JFXDrawer leftDrawer;
    
    @FXML
    private VBox toolbar;
    
    @FXML
    private Label usernameLabel;
    
    @FXML
    private Label emailLabel;
    
    @FXML
    private TableView<Invitation> invitationTable;
    
    @FXML
    private Button acceptButton;
    
    @FXML
    private Button declineButton;
    
    @FXML
    private HBox layout;
    
    @FXML
    private ImageView yourFridgeImage;
    
    @FXML
    private ImageView fridge1Image;
    
    @FXML
    private ImageView fridge2Image;
    
    @FXML
    private ImageView fridge3Image;
    
    @FXML
    private ImageView fridge4Image;
    
    @FXML
    private Label yourFridgeLabel;
    
    @FXML
    private Label fridge1Label;

    @FXML
    private Label fridge2Label;

    @FXML
    private Label fridge3Label;

    @FXML
    private Label fridge4Label;
    
    public ChangeFridgeControllerFXML() {
		changeFridgeUI = new ChangeFridgeUI();
    	
    }

    
    @Override
    public void initialize (URL url, ResourceBundle rb ) {
    	
    	if(SingletonInstances.getCurrentUser().isAdmin()) {
	    	try {
		    	toolbar = FXMLLoader.load(getClass().getResource("toolbar.fxml"));
		    	leftDrawer.setSidePane(toolbar);
	    	}catch(IOException ex) {
	    		Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
	    	}
    	}else {
    		//if user session
    		try {
		    	toolbar = FXMLLoader.load(getClass().getResource("toolbarUser.fxml"));
		    	leftDrawer.setSidePane(toolbar);
	    	}catch(IOException ex) {
	    		Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
	    	}
    		
    	}
    	HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
    	transition.setRate(-1);
    	hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
    		transition.setRate(transition.getRate() * -1 );
    		transition.play();
    		
    		if( leftDrawer.isOpened() ) {
    			leftDrawer.close();
    		}
    		else {
    			leftDrawer.open();
    		}
    	});
    	TableColumn<Invitation, String> nameColumn = new TableColumn<>("Invitation by");
    	nameColumn.setMinWidth(200);
    	nameColumn.setCellValueFactory(new PropertyValueFactory<Invitation,String>("invitingUser"));
        TableColumn<Invitation, String> messageColumn = new TableColumn<>("Message");
        messageColumn.setMinWidth(350);
        messageColumn.setCellValueFactory(new PropertyValueFactory<Invitation,String>("message"));
    	
        
        
    	ObservableList<Invitation> list = changeFridgeUI.takeInvitations();
    	
    	invitationTable.getColumns().add(nameColumn);
    	invitationTable.getColumns().add(messageColumn);
    	if( list != null ) {
    		invitationTable.setItems(list);
    	}
    	else {
    		invitationTable.setPlaceholder(new Label(" You don't have any invitation :( "));
    	}
    	SingletonInstances.getSingletonInstance();
    	usernameLabel.setText( SingletonInstances.getCurrentUser().getUsername());
    	emailLabel.setText(SingletonInstances.getCurrentUser().getEmailAddress());
    	yourFridgeLabel.setText(SingletonInstances.getMyFridge().getName());
    	
    	
    	this.initializeFridgesInteface();
    	

    }
    
    public void clickedOnDeleteAccount() {
    	try {
    		deletionWindow = new Stage();
    		deletionWindow.initModality( Modality.APPLICATION_MODAL);
    		deletionWindow.setTitle("Confirm deletion");
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("deletionBox.fxml"));
			Scene scene = new Scene(root,300,150);
			scene.getStylesheets().add(getClass().getResource("bootstrap3.css").toExternalForm());
			deletionWindow.setScene(scene);
			deletionWindow.showAndWait();
		} catch(Exception e) {
			e.printStackTrace();
		}   	
    }
    
    public void clickedOnDeclineButton() {
    	ObservableList<Invitation> allInvitations, selectedInvitations;
    	allInvitations = invitationTable.getItems();
    	selectedInvitations = invitationTable.getSelectionModel().getSelectedItems();
		changeFridgeUI.clickedOnDeclineInvitation( selectedInvitations.get(0) );
    	selectedInvitations.forEach(allInvitations::remove);
    }
    
    public void clickedOnAcceptButton() {
    	try {
	    	ObservableList<Invitation> allInvitations, selectedInvitations;
	    	allInvitations = invitationTable.getItems();
	    	selectedInvitations = invitationTable.getSelectionModel().getSelectedItems();
			changeFridgeUI.clickedOnAcceptInvitation( selectedInvitations.get(0) );
			selectedInvitations.forEach(allInvitations::remove);
			this.initializeFridgesInteface();
    	}catch( TooManyFridgesException te ) {
    		alertLabel.setText("Too many fridges!");
    	}
    	
    	return;
		
    }
    
    public static void deletionConfirmed() {
    	changeFridgeUI.clickedOnDeleteAccount();
    }
    
    public static Stage getDeletionStage() {
    	return deletionWindow;
    }
    
    public void initializeFridgesInteface() {
    	fridge1Image.setVisible(false);
    	fridge2Image.setVisible(false);
    	fridge3Image.setVisible(false);    
    	fridge4Image.setVisible(false);
    	
    	
    	listName = changeFridgeUI.startFridgesInterfaces();
    	if( listName != null ) {
	    	if( listName.size() >= 1 ) {
	    		fridge1Label.setText(listName.get(0));
	    		fridge1Image.setVisible(true);
	    	}
	    	
	    	if( listName.size() >= 2 ) {
	    		fridge2Label.setText(listName.get(1));
	    		fridge2Image.setVisible(true);
	    	}
	    	
	    	if( listName.size() >= 3 ) {
	    		fridge3Label.setText(listName.get(2));
	    		fridge3Image.setVisible(true);
	    	}
	    	
	    	if( listName.size() == 4 ) {
	    		fridge4Label.setText(listName.get(3));
	    		fridge4Image.setVisible(true);
	    	}
    	
    	}
    }
    

    public void clickedOnYourFridgeImage() {
    	changeFridgeUI.clickedOnChangeInMyFridge();
    	new Main().viewFridgeSetScene();
    }
    
    public void clickedOnFridge1Image() {
    	changeFridgeUI.clickedOnChangeFridge( listName.get(0) );
    	new Main().viewFridgeSetScene();
    }
    
    public void clickedOnFridge2Image() {
    	changeFridgeUI.clickedOnChangeFridge( listName.get(1) );
    	new Main().viewFridgeSetScene();
    }
    
    public void clickedOnFridge3Image() {
    	changeFridgeUI.clickedOnChangeFridge( listName.get(2) );
    	new Main().viewFridgeSetScene();
    }
    
    public void clickedOnFridge4Image() {
    	changeFridgeUI.clickedOnChangeFridge( listName.get(3) );
    	new Main().viewFridgeSetScene();
    }
    
    
    
    
}