package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.ContatoDAO;
import model.entities.Contato;

public class ContatoJDBC implements ContatoDAO{
	
	Connection conn;
	
	public ContatoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Contato contato) {
		PreparedStatement st = null;
		
		try {	
			st= conn.prepareStatement("INSERT INTO contacts (nome, numero) VALUES (?,?)");
			st.setString(1, contato.getNome());
			st.setInt(2, contato.getNumero());
			st.executeUpdate();		
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Contato contato) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("UPDATE contacts SET nome = ?, numero = ? WHERE id = ?");
			
			st.setString(1, contato.getNome());
			st.setInt(2, contato.getNumero());
			st.setInt(3, contato.getId());
			
			st.executeUpdate();		
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM contacts WHERE id = ?");
			st.setInt(1, id);
			st.executeUpdate();		
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Contato> findAll() {
		List<Contato> contacts = new ArrayList<>();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st= conn.prepareStatement("SELECT *FROM contacts");
			rs = st.executeQuery();
			
			while(rs.next()) {
				contacts.add(instantiateContact(rs));
			}
			return contacts;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}
	public Contato instantiateContact(ResultSet rs) {
		try {
			return new Contato(rs.getInt("id"), rs.getString("nome"), rs.getInt("numero"));
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}	
	}
}
