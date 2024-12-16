package com.classes.DTO;

public class Vaga {
	int id;
	int num;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Vaga [id=");
		builder.append(id);
		builder.append(", num=");
		builder.append(num);
		builder.append("]");
		return builder.toString();
	}
	
	
}
