package model.services;

import java.util.ArrayList;
import java.util.List;

import model.dao.ContatoDAO;
import model.dao.DaoFactory;
import model.entities.Contato;

public class ContatoService {
	
	private ContatoDAO dao = DaoFactory.createContatoDAO(); 
	
	public List<Contato> findAll(){
		return dao.findAll();
	}
	
	public void insert(Contato contato) {
		if(contato.getId() == null) {
			dao.insert(contato);
		}else {
			dao.update(contato);
		}
	}
	
}
