package com.classes.DTO;

public class Parada {
	int id;
	String cidade;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Parada [id=");
		builder.append(id);
		builder.append(", cidade=");
		builder.append(cidade);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
