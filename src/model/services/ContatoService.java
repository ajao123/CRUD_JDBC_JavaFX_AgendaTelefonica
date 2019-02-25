package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Contato;

public class ContatoService {
	
	public List<Contato> findAlll(){
		List<Contato> list = new ArrayList<>();
		list.add(new Contato("Joao", 1));
		list.add(new Contato("Maria", 2));
		list.add(new Contato("Carlos", 3));
		list.add(new Contato("Ana", 4));
		return list;
	}
	
}
