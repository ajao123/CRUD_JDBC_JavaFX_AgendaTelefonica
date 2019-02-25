package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Contato;

public class ContatoFormController implements Initializable{

	private Contato contato;
	
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
	
	public void btnSaveAction() {
		System.out.println("btnSaveAction");
	}
	
	public void btnCancelAction() {
		System.out.println("btnCancelAction");
	}
	
	public void setContato(Contato contato) {
		this.contato = contato;
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
}
