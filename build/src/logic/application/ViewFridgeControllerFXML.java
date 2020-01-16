package logic.application;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import logic.boundary.ViewFridgeUI;
import logic.entity.Food;
import logic.implementationClasses.SingletonInstances;


public class ViewFridgeControllerFXML implements Initializable {
	
	private ViewFridgeUI viewFridgeUI;
	
	@FXML
	private Button removeButton;
	
	@FXML
	private BorderPane borderpane;
	
    @FXML
    private JFXHamburger hamburger;
    
    @FXML
    private JFXDrawer leftDrawer;
    
    @FXML
    private VBox toolbar;
    
    @FXML
    private TextField fridgeName;
    
    @FXML
    private TableView<Food> table;
    
    @FXML
    private ImageView editImage;
    
    @FXML
    private ImageView arrowImage;
    
    
    public ViewFridgeControllerFXML() {
		this.viewFridgeUI = new ViewFridgeUI();
    	
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
    		removeButton.setVisible(false);
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
    	
    	fridgeName.setEditable(false);
    	SingletonInstances.getSingletonInstance();
    	fridgeName.setText( SingletonInstances.getCurrentFridge().getName());
    	
    	TableColumn<Food, String> nameColumn = new TableColumn<>("Name");
    	nameColumn.setMinWidth(240);
    	nameColumn.setCellValueFactory(new PropertyValueFactory<Food,String>("name"));
        TableColumn<Food, String> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(240);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Food,String>("quantity"));
        TableColumn<Food, String> expirationDateColumn = new TableColumn<>("Expiration Date");
        expirationDateColumn.setMinWidth(240);
        expirationDateColumn.setCellValueFactory(new PropertyValueFactory<Food,String>("expirationDate"));
    	
    	ObservableList<Food> list = this.viewFridgeUI.showContent();
    	
    	table.getColumns().add(nameColumn);
        table.getColumns().add(quantityColumn);
        table.getColumns().add(expirationDateColumn);
    	if( list != null ) {
        	table.setItems(list);
    	}
    	else {
    		table.setPlaceholder(new Label("Frigo vuoto! Riempilo "));
    	}
    	
    	
    	
    }
    
    public void clickedOnEditName(){
    	fridgeName.setEditable(true);
    																																																																	
    }
    
    public void clickedOnEnter() {
    	fridgeName.setEditable(false);
    	String name = fridgeName.getText();
    	this.viewFridgeUI.clickedOnChangeNameFridge(name);
    	
    }
    
    public void clickedOnRemoveButton() {
    	ObservableList<Food> allFood, selectedFood;
    	allFood = table.getItems();
    	selectedFood = table.getSelectionModel().getSelectedItems();
		viewFridgeUI.clickedOnRemoveFood( selectedFood.get(0) );
    	selectedFood.forEach(allFood::remove);
    }
    
}
