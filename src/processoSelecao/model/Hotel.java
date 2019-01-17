package processoSelecao.model;

public class Hotel {
	
	private String nome;
	private int estrelas;
	private double precoSemanaComum;
	private double precoFimSemanaComum;
	private double precoSemanaVip;
	private double precoFimSemanaVip;
	
	public Hotel(){
		
	}
	
	public Hotel(String nome, int estrelas, double precoSemanaComum, double precoFimSemanaComum, double precoSemanaVip, double precoFimSemanaVip){
		this.nome = nome;
		this.estrelas = estrelas;
		this.precoSemanaComum = precoSemanaComum;
		this.precoFimSemanaComum = precoFimSemanaComum;
		this.precoSemanaVip = precoSemanaVip;
		this.precoFimSemanaVip = precoFimSemanaVip;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getEstrelas() {
		return estrelas;
	}

	public void setEstrelas(int estrelas) {
		this.estrelas = estrelas;
	}

	public double getPrecoSemanaComum() {
		return precoSemanaComum;
	}

	public void setPrecoSemanaComum(double precoSemanaComum) {
		this.precoSemanaComum = precoSemanaComum;
	}

	public double getPrecoFimSemanaComum() {
		return precoFimSemanaComum;
	}

	public void setPrecoFimSemanaComum(double precoFimSemanaComum) {
		this.precoFimSemanaComum = precoFimSemanaComum;
	}

	public double getPrecoSemanaVip() {
		return precoSemanaVip;
	}

	public void setPrecoSemanaVip(double precoSemanaVip) {
		this.precoSemanaVip = precoSemanaVip;
	}

	public double getPrecoFimSemanaVip() {
		return precoFimSemanaVip;
	}

	public void setPrecoFimSemanaVip(double precoFimSemanaVip) {
		this.precoFimSemanaVip = precoFimSemanaVip;
	}
}
