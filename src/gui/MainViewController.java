package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class MainViewController implements Initializable{

	@FXML
	private MenuItem MenuItemContato;
	
	@FXML
	private MenuItem MenuItemAbout;
	
	public void onMenuItemActionContato() {
		System.out.println("MenuItemContato");
	}
	
	public void onMenuItemActionAbout() {
		System.out.println("MenuItemAbout");
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	
	
}
