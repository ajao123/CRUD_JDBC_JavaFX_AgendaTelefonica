package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Contato;
import model.services.ContatoService;

public class ContatoListController implements Initializable{

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
	
	public void newButtonAction() {
		System.out.println("newButton");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
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
	
		List<Contato> list = service.findAlll();
 		obsList = FXCollections.observableArrayList(list);
 		tableViewContato.setItems(obsList);
	}

}
