package entidades;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Viagem {
	int id;
	List<PontoParada> pontosDeParada;
	Onibus onibus;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<PontoParada> getPontosDeParada() {
		return pontosDeParada;
	}
	public void setPontosDeParada(List<PontoParada> pontosDeParada) {
		this.pontosDeParada = pontosDeParada;
	}
	public Onibus getOnibus() {
		return onibus;
	}
	public void setOnibus(Onibus onibus) {
		this.onibus = onibus;
	}
	public void atualizaHorariosOnibus() {
		if (onibus.getHorarios() == null) {
			onibus.setHorarios(new ArrayList<HorarioDeFuncionamento>());
		}
		HorarioDeFuncionamento novoHorario = new HorarioDeFuncionamento();
		novoHorario.setInicio(this.pontosDeParada.get(0).getHorario());
		novoHorario.setFim(this.pontosDeParada.get(pontosDeParada.size() - 1).getHorario());
		onibus.getHorarios().add(novoHorario);
	}
	
	public String getDesc() {
		DateTimeFormatter formataBr = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
		List<PontoParada> pontos = getPontosDeParada();
		String desc = pontos.get(0).getParada().getCidade();
		desc += " " + pontos.get(0).getHorario().format(formataBr) + " -- ";
		
		desc += pontos.get(pontos.size() -1).getParada().getCidade() + " ";
		desc += pontos.get(pontos.size() -1).getHorario().format(formataBr);
		
		return desc;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Viagem [id=");
		builder.append(id);
		builder.append(", pontosDeParada=");
		builder.append(pontosDeParada);
		builder.append(", onibus=");
		builder.append(onibus);
		builder.append("]");
		return builder.toString();
	}
	
	
}
