package chess;

public class Jogador {

	private String nome;

	private int cor;

	private boolean xeque;

	private Canvas inter;

	public Peca[] pecas = new Peca[17];

	public Peca rei;

	public Jogador(int cor, String nome, Canvas inter) {
		this.nome = nome;
		this.cor = cor;
		this.xeque = false;
		this.inter = inter;

		for (int i = 1; i < 17; i++) {
			this.pecas[i] = null;
		}

		int cont = 1;
		if (this.cor == Peca.COR_PRETA) {
			this.pecas[cont] = (new Torre(this.cor, cont++, 1));
			this.pecas[cont] = (new Cavalo(this.cor, cont++, 1));
			this.pecas[cont] = (new Bispo(this.cor, cont++, 1));
			this.rei = new Rei(this.cor, cont, 1);
			this.pecas[cont++] = (this.rei);
			this.pecas[cont] = (new Rainha(this.cor, cont++, 1));
			this.pecas[cont] = (new Bispo(this.cor, cont++, 1));
			this.pecas[cont] = (new Cavalo(this.cor, cont++, 1));
			this.pecas[cont] = (new Torre(this.cor, cont++, 1));

			for (int i = 1; i < 9; i++) {
				this.pecas[i + 8] = new Peao(this.cor, i, 2, true);
			}
		} else {
			this.pecas[cont] = (new Torre(this.cor, cont++, 8));
			this.pecas[cont] = (new Cavalo(this.cor, cont++, 8));
			this.pecas[cont] = (new Bispo(this.cor, cont++, 8));
			this.rei = new Rei(this.cor, cont, 8);
			this.pecas[cont++] = (this.rei);
			this.pecas[cont] = (new Rainha(this.cor, cont++, 8));
			this.pecas[cont] = (new Bispo(this.cor, cont++, 8));
			this.pecas[cont] = (new Cavalo(this.cor, cont++, 8));
			this.pecas[cont] = (new Torre(this.cor, cont++, 8));

			for (int i = 1; i < 9; i++) {
				this.pecas[i + 8] = new Peao(this.cor, i, 7, false);
			}
		}
	}

	public void setXeque(boolean b) {
		this.xeque = b;
	}

	public boolean isXeque() {
		return this.xeque;
	}

	public boolean isXequeMate() {
		return this.rei.isCapturada();
	}

	public int getCor() {
		return this.cor;
	}

	public String printNome() {
		return this.nome;
	}

	public Move gerarMov() {

		this.inter.setReady(true);
		while (this.inter.isReady()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {

			}
		}

		Move m = this.inter.getMove();
		return m;
	}

}
