package entidades;

import java.util.List;

public class Onibus {
	int id;
	String codigo;
	List<Vaga> vagas;
	List<HorarioDeFuncionamento> horarios;
	
	
	public List<HorarioDeFuncionamento> getHorarios() {
		return horarios;
	}
	public void setHorarios(List<HorarioDeFuncionamento> horarios) {
		this.horarios = horarios;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public List<Vaga> getVagas() {
		return vagas;
	}
	public void setVagas(List<Vaga> vagas) {
		this.vagas = vagas;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Onibus [id=");
		builder.append(id);
		builder.append(", codigo=");
		builder.append(codigo);
		builder.append(", vagas=");
		builder.append(vagas);
		builder.append(", horarios=");
		builder.append(horarios);
		builder.append("]");
		return builder.toString();
	}
	
	
}
