package entidades;

import java.time.LocalDateTime;

public class PontoParada {
	Parada parada;
	LocalDateTime horario;
	
	public Parada getParada() {
		return parada;
	}
	public void setParada(Parada parada) {
		this.parada = parada;
	}
	public LocalDateTime getHorario() {
		return horario;
	}
	public void setHorario(LocalDateTime horario) {
		this.horario = horario;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PontoParada [parada=");
		builder.append(parada);
		builder.append(", horario=");
		builder.append(horario);
		builder.append("]");
		return builder.toString();
	}
	
	
}
