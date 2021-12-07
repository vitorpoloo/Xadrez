package br.com.chess;

public class Lugar {

	private Peca peca;

	private boolean ocupado;

	public Lugar() {
		this.ocupado = false;
	}

	public Peca getPeca() {
		return peca;
	}

	public void setPeca(Peca peca) {
		this.peca = peca;
		this.setOcupado(Boolean.TRUE);
	}

	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

}