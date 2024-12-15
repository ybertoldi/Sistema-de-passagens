package entidades;

public class Passagem {
	int id = -1;
	String numeroPassagem;
	Cliente cliente;
	Viagem viagem;
	
	PontoParada embarque;
	PontoParada saida;
	Vaga vaga;
	
	public Passagem() {}
	
	public Passagem(int id, String numeroPassagem, Cliente cliente, Viagem viagem, PontoParada embarque,
			PontoParada saida, Vaga vaga) {
		super();
		this.id = id;
		this.numeroPassagem = numeroPassagem;
		this.cliente = cliente;
		this.viagem = viagem;
		this.embarque = embarque;
		this.saida = saida;
		this.vaga = vaga;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumeroPassagem() {
		return numeroPassagem;
	}
	public void setNumeroPassagem(String numeroPassagem) {
		this.numeroPassagem = numeroPassagem;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public void setCliente(Usuario cliente) {
		this.cliente = (Cliente) cliente;
	}
	public Viagem getViagem() {
		return viagem;
	}
	public void setViagem(Viagem viagem) {
		this.viagem = viagem;
	}
	public PontoParada getEmbarque() {
		return embarque;
	}
	public void setEmbarque(PontoParada embarque) {
		this.embarque = embarque;
	}
	public PontoParada getSaida() {
		return saida;
	}
	public void setSaida(PontoParada saida) {
		this.saida = saida;
	}
	public Vaga getVaga() {
		return vaga;
	}
	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Passagem [id=");
		builder.append(id);
		builder.append(", numeroPassagem=");
		builder.append(numeroPassagem);
		builder.append(", cliente=");
		builder.append(cliente);
		builder.append(", viagem=");
		builder.append(viagem);
		builder.append(", embarque=");
		builder.append(embarque);
		builder.append(", saida=");
		builder.append(saida);
		builder.append(", vaga=");
		builder.append(vaga);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
