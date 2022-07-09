package it.polito.tdp.yelp.model;

public class Coppia {
	
	private String r1;
	private String r2;
	private int peso;
	
	public Coppia(String r1, String r2, int peso) {
		super();
		this.r1 = r1;
		this.r2 = r2;
		this.peso = peso;
	}
	public String getR1() {
		return r1;
	}
	public void setR1(String r1) {
		this.r1 = r1;
	}
	public String getR2() {
		return r2;
	}
	public void setR2(String r2) {
		this.r2 = r2;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return "Coppia [r1=" + r1 + ", r2=" + r2 + ", peso=" + peso + "]";
	};
	
	

}
