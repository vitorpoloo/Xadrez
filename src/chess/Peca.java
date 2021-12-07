package chess;

public abstract class Peca {
	
	public static int COR_BRANCA = 0;
	
	public static int COR_PRETA = 1;
	
	private int cor;
	
	private int x;
	
	private int y;
	
	private boolean capturada;

	public Peca(int cor, int x, int y) {
		this.setCor(cor);
		this.setX(x);
		this.setY(y);
		this.setCapturada(false);
	}

	public void setCor(int cor) {
		this.cor = cor;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getCor() {
		return this.cor;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setCapturada(boolean b) {
		this.capturada = b;
	}

	public boolean isCapturada() {
		return this.capturada;
	}

	public abstract String printPeca();

	public abstract boolean isMovValido(Tabuleiro t, int toX, int toY);
}