package entidades;

public class Endereco {
	int id = -1;
	String cep;
	String cidade;
	String rua;
	String bairro;
	String numero;
	
	public Endereco() {}
	
	public Endereco(String cep, String cidade, String rua, String bairro, String numero) {
		this.cep = cep;
		this.cidade = cidade;
		this.rua = rua;
		this.bairro = bairro;
		this.numero = numero;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Endereco [id=");
		builder.append(id);
		builder.append(", cep=");
		builder.append(cep);
		builder.append(", cidade=");
		builder.append(cidade);
		builder.append(", rua=");
		builder.append(rua);
		builder.append(", bairro=");
		builder.append(bairro);
		builder.append(", numero=");
		builder.append(numero);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
