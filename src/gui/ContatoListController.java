package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Contato;
import model.listeners.DataChangeListener;
import model.services.ContatoService;

public class ContatoListController implements Initializable, DataChangeListener{

	private ContatoService service;
 	
	@FXML
	private ObservableList<Contato> obsList;
	
	@FXML
	private Button newButton;
	
	@FXML
	private TableView<Contato> tableViewContato;
	
	@FXML
	private TableColumn<Contato, String> tableColumnNome;
	
	@FXML
	private TableColumn<Contato, Integer> tableColumnNumero;
	
	@FXML
	private TableColumn<Contato, Integer> tableColumnId;
	
	public void newButtonAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Contato contato = new Contato();
		createDialogForm(contato, "/gui/ContatoForm.fxml", parentStage);
	}
	
	private void createDialogForm(Contato contato, String absoluteName, Stage parentStage) {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			ContatoFormController controller = loader.getController();
			
			controller.setService(service);
			
			controller.setContato(contato);
			
			controller.subscribeDataChangedListener(this);
			
			controller.updateFormData();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Enter Contact Data");
			dialogStage.setScene(new Scene(pane));
			
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			
			dialogStage.showAndWait();
			
		} catch (IOException e) {
			Alerts.showAlert("IOException", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
		
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColumnNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewContato.prefHeightProperty().bind(stage.heightProperty());
	}
	
	public void setService(ContatoService service) {
		this.service = service;
	}
	
	public void updateTableView() {
		if(service == null) {
			throw new IllegalStateException();
		}
	
		List<Contato> list = service.findAll();
 		obsList = FXCollections.observableArrayList(list);
 		tableViewContato.setItems(obsList);
	}

	@Override
	public void onDataChanged() {
		updateTableView();
	}

}
