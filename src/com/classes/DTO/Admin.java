package com.classes.DTO;

import java.time.LocalDate;
import java.util.Date;

public class Admin extends Usuario{

	public Admin() {}
	
	public Admin(String nome, String sobrenome, LocalDate dataDeNascimento, String cpf, Endereco endereco) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataDeNascimento = dataDeNascimento;
		this.cpf = cpf;
		this.endereco = endereco;
	}
	
	public Admin(Usuario usuario) {
		this.id = usuario.getId();
		this.cpf = usuario.getCpf();
		this.dataDeNascimento = usuario.getDataDeNascimento();
		this.endereco = usuario.getEndereco();
		this.nome = usuario.getNome();
		this.sobrenome = usuario.getSobrenome();
	}
	
	public void registrarViagem() {}
	
	public void atualizarViage() {}
	
	public void excluirViagem() {}
	
	//nao sera void
	public void verificarDadosCliente() {}
	
	//nao sera void
	public void verificarViagem() {}
}
