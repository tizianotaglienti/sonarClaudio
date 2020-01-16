package logic.application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.controlsfx.control.CheckComboBox;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

//import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import logic.boundary.ChooseRecipesUI;
import logic.implementationClasses.SingletonInstances;




public class ChooseRecipesControllerFXML implements Initializable {
	private ObservableList<String> selectedFeatures;
	private ChooseRecipesUI chooseRecipesUI;
	
	@FXML
	private TextField numberRecipesInp;
	
	@FXML
	private Label alertLabel;
	
	@FXML
	private CheckComboBox<String> checkComboBox;
	
	@FXML
    private JFXHamburger hamburger;
    
    @FXML
    private JFXDrawer leftDrawer;
    
    @FXML
    private VBox toolbar;
	
	@FXML
	private Button setSearchButton;
	
	@FXML
	private Button startSearchButton;
	
	@FXML
	private Hyperlink link1;
	
	@FXML
	private Hyperlink link2;
	
	@FXML
	private Hyperlink link3;
	
	@FXML
	private Hyperlink link4;
	
	@FXML
	private Hyperlink link5;
	
	@FXML
	private Label label1;
	
	@FXML
	private Label label2;
	
	@FXML
	private Label label3;
	
	@FXML
	private Label label4;
	
	@FXML
	private Label label5;
    
    public ChooseRecipesControllerFXML() {
    	this.chooseRecipesUI = new ChooseRecipesUI();
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
    	alertLabel.setText("Select ingredients to be excluded");
    	
    	//prendo array di stringhe
    	ObservableList<String> data = this.chooseRecipesUI.getListFood();
		if( data != null ) {
			checkComboBox.getItems().addAll( data );
		}
		//checkComboBox.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>(){
		//	public void onChanged(ListChangeListener.Change<? extends String> c) {
		//		selectedFeatures = checkComboBox.getCheckModel().getCheckedItems();
		//	}
		//});
    	
    	
    }
    
    
    
    public void clickedOnStartSearch() {
    	//prendi il numero di ricette
    	alertLabel.setText("");
    	if( numberRecipesInp.getText().trim().isEmpty() ) {
    		alertLabel.setText(" Inserisci il numero di ricette! ");
    		return;
    	}
    	ArrayList<String> listEliminatedIngredients = null;
    	int numRecipes = Integer.parseInt(numberRecipesInp.getText());
    	if( selectedFeatures != null ) {
    		listEliminatedIngredients = new ArrayList<String>(selectedFeatures);    	
    	}
    	
    	
    	//vai a escludere ingredienti selezionati
    	this.chooseRecipesUI.clickedOnStartSearch(listEliminatedIngredients, numRecipes);
    	this.initializeRecipesInterface();
    }
    
    public void initializeRecipesInterface() {
    	ArrayList<String> links = this.chooseRecipesUI.getLinks();
    	ArrayList<String> ingredients = this.chooseRecipesUI.getIngredients();
    	System.out.println(links);
    	System.out.println(ingredients);
    	
    	
    	if( links.size() >= 1 ) {
    		link1.setText( links.get(0) );
    		// set link action
    		label1.setText( String.format( ingredients.get(0) + ingredients.get(1)  + ingredients.get(2) ) );
    	}
    	if( links.size() >= 2 ) {
    		link2.setText( links.get(1) );
    		// set link action
    		label2.setText( String.format( ingredients.get(3) + ingredients.get(4)  + ingredients.get(5) ) );
    	}
    	if( links.size() >= 3 ) {
    		link3.setText( links.get(2) );
    		// set link action
    		label3.setText( String.format( ingredients.get(6) + ingredients.get(7)  + ingredients.get(8) ) );
    	}
    	if( links.size() >= 4 ) {
    		link4.setText( links.get(3) );
    		// set link action
    		label4.setText( String.format( ingredients.get(9) + ingredients.get(10)  + ingredients.get(11) ) );
    	}
    	if( links.size() == 5 ) {
    		link5.setText( links.get(4) );
    		// set link action
    		label5.setText( String.format( ingredients.get(12) + ingredients.get(13)  + ingredients.get(14) ) );
    	}
    	
    }
    
    public void clickedOnLink1() {
    	
    }
	public void clickedOnLink2() {
	    	
	}
	public void clickedOnLink3() {
		
	}
	public void clickedOnLink4() {
		
	}
	public void clickedOnLink5() {
		
	}
    
}


