package entidades;

import java.time.LocalDateTime;

public class HorarioDeFuncionamento {
	int id;
	LocalDateTime inicio;
	LocalDateTime fim;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getInicio() {
		return inicio;
	}
	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}
	public LocalDateTime getFim() {
		return fim;
	}
	public void setFim(LocalDateTime fim) {
		this.fim = fim;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HorarioDeFuncionamento [id=");
		builder.append(id);
		builder.append(", inicio=");
		builder.append(inicio);
		builder.append(", fim=");
		builder.append(fim);
		builder.append("]");
		return builder.toString();
	}
	
	
}
