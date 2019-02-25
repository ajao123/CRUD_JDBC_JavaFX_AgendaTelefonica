package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import db.DbException;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Contato;
import model.listeners.DataChangeListener;
import model.services.ContatoService;

public class ContatoFormController implements Initializable{

	private Contato contato;
	private ContatoService service;
	
	List<DataChangeListener> listeners = new ArrayList<>();
	
	@FXML
	private TextField tfId;
	
	@FXML
	private TextField tfNome;
	
	@FXML
	private TextField tfNumero;
	
	@FXML
	private Button btnSave;
	
	@FXML 
	private Button btnCancel;
	
	@FXML
	private Label lblError;
	
	public void btnSaveAction(ActionEvent event) {
		try {
			if(contato == null) {
				throw new IllegalStateException("Entity was null");
			}
			if(service == null) {
				throw new IllegalStateException("Service was null");
			}
			
			contato.setNome(tfNome.getText());
			contato.setNumero(Integer.parseInt(tfNumero.getText()));
			service.insert(contato);
			
			onDataChangedListener();
			Utils.currentStage(event).close();
			
		}catch(DbException e) {
			Alerts.showAlert("Error saving object", null, e.getMessage(), AlertType.ERROR);
		}
	}
	
	public void btnCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}
	
	public void setContato(Contato contato) {
		this.contato = contato;
	}
	
	public void setService(ContatoService service) {
		this.service = service;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initilizeNodes();
		
	}

	private void initilizeNodes() {
		Constraints.setTextFieldInteger(tfNumero);
		Constraints.setTextFieldMaxLength(tfNome, 30);
	}

	public void updateFormData() {
		if(contato == null) {
			throw new IllegalStateException("Entity was null");
		}
		tfNome.setText(contato.getNome());
		tfNumero.setText(String.valueOf(contato.getNumero()));
	}
	
	public void subscribeDataChangedListener(DataChangeListener listener) {
		listeners.add(listener);
	}

	public void onDataChangedListener() {
		for(DataChangeListener listener : listeners) {
			listener.onDataChanged();
		}
	}
}
