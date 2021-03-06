package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.ContatoService;

public class MainViewController implements Initializable{

	@FXML
	private MenuItem MenuItemContato;
	
	@FXML
	private MenuItem MenuItemAbout;
	
	public void onMenuItemActionContato() {
		loadView("/gui/ContatoListView.fxml", (ContatoListController controller) -> {
			controller.setService(new ContatoService());
			controller.updateTableView();
		});
	}
	
	public void onMenuItemActionAbout() {
		loadView("/gui/AboutView.fxml", x -> {});
	}
	
	private synchronized <T> void loadView(String absoluteName, Consumer<T> consumer) {
		
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
		
			Scene mainScene = Main.getMainScene();
			
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();	
			
			Node mainMenu = mainVBox.getChildren().get(0);
			
			mainVBox.getChildren().clear();
			
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			consumer.accept(loader.getController());
			
		} catch (IOException e) {
			
			Alerts.showAlert("IOException", "Error Loading View", e.getMessage(), AlertType.ERROR);
		}
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	
	
}
