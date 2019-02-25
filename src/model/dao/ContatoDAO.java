package model.dao;

import java.util.List;

import model.entities.Contato;

public interface ContatoDAO {
	void insert(Contato contato);
	void update(Contato contato);
	void deleteById(Integer contato);
	List<Contato> findAll();
}
