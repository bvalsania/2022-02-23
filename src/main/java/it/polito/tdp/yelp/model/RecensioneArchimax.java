package it.polito.tdp.yelp.model;

public class RecensioneArchimax {

	private String r;
	private int peso;
	public RecensioneArchimax(String r, int peso) {
		super();
		this.r = r;
		this.peso = peso;
	}
	public String getR() {
		return r;
	}
	public void setR(String r) {
		this.r = r;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return "RecensioneArchimax [r=" + r + ", peso=" + peso + "]";
	}
	
	
}
