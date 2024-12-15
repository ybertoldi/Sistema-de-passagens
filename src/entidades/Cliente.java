package entidades;

import java.time.LocalDate;
import java.util.Date;

public class Cliente extends Usuario{

	public Cliente() {}
	
	public Cliente(String nome, String sobrenome, LocalDate dataDeNascimento, String cpf, Endereco endereco) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataDeNascimento = dataDeNascimento;
		this.cpf = cpf;
		this.endereco = endereco;
	}
	
	public Cliente(Usuario usuario) {
		this.id = usuario.getId();
		this.cpf = usuario.getCpf();
		this.dataDeNascimento = usuario.getDataDeNascimento();
		this.endereco = usuario.getEndereco();
		this.nome = usuario.getNome();
		this.sobrenome = usuario.getSobrenome();
	}
	
//	public List<Viagem> verificarPassagens() {}
	
	public void comprarPassagens() {
		
	}
	
	public void imprimirPassagens() {
		
	}
	
	public void cancelarPassagens() {
		
	}
	
}
