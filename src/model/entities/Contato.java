package model.entities;

public class Contato {
	
	private String nome;
	private Integer numero;
	private Integer id;
	

	public Contato() {
		// TODO Auto-generated constructor stub
	}

	public Contato(Integer id, String nome, Integer numero) {
		this.id = id;
		this.nome = nome;
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}
}
