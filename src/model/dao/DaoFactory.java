package model.dao;

import db.DB;
import model.dao.impl.ContatoJDBC;

public class DaoFactory {
	public static ContatoDAO createContatoDAO() {
		return new ContatoJDBC(DB.getConnection());
	}
}
