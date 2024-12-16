package com.classes.DTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Usuario {
	int id = -1;
	String nome;
	String sobrenome;
	LocalDate dataDeNascimento;
	String cpf;
	Endereco endereco;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}
	public String getDataDeNascimentoFormatada() {
		DateTimeFormatter formataBr = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return dataDeNascimento.format(formataBr);
	}
	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	public void setDataDeNascimento(String s) {
		DateTimeFormatter formataBr = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.dataDeNascimento = LocalDate.parse(s, formataBr);
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
	public Usuario(String nome, String sobrenome, LocalDate dataDeNascimento, String cpf, Endereco endereco) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataDeNascimento = dataDeNascimento;
		this.cpf = cpf;
		this.endereco = endereco;
	}
	
	public Usuario() {}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Usuario [id=");
		builder.append(id);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", sobrenome=");
		builder.append(sobrenome);
		builder.append(", dataDeNascimento=");
		builder.append(getDataDeNascimentoFormatada());
		builder.append(", cpf=");
		builder.append(cpf);
		builder.append(", endereco=");
		builder.append(endereco);
		builder.append("]");
		return builder.toString();
	}
	
	
}
