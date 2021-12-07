package chess;

public class Tabuleiro {
	public Lugar[][] lugares = new Lugar[9][9];
	private boolean xequeMate;
	private int vez;
	private Jogador atual, p1, p0;

	public Tabuleiro(Jogador jogadorPreto, Jogador jogadorBranco) {
		this.p1 = jogadorPreto;
		this.p0 = jogadorBranco;
		this.vez = 0;
		this.atual = jogadorBranco;
		this.xequeMate = false;
		this.inicializarTabuleiro();
	}

	public void inicializarTabuleiro() {
		for (int c = 1; c < 9; c++) {
			for (int d = 1; d < 9; d++) {
				this.lugares[c][d] = new Lugar();
			}
		}
		Lugar l = null;

		Jogador p = this.p1;

		for (int c = 1; c < 17; c++) {
			Peca peca = p.pecas[c];
			if (peca != null) {
				l = this.lugares[peca.getX()][peca.getY()];
				l.setPeca(peca);
			}
		}

		p = this.p0;

		for (int c = 1; c < 17; c++) {
			Peca peca = p.pecas[c];
			if (peca != null) {
				l = this.lugares[peca.getX()][peca.getY()];
				l.setPeca(peca);
			}
		}

	}

	public boolean nextMov() {
		Move m = this.atual.gerarMov();
		Lugar l = this.lugares[m.getFromX()][m.getFromY()];
		if (l.isOcupado()) {
			Peca p = l.getPeca();
			if (p.getCor() == this.vez) {
				if (p.isMovValido(this, m.getToX(), m.getToY())) {
					this.moverPeca(m);

					this.testaXeque();

					this.testaXequeMate();

					return true;
				}
			}
		}
		return false;
	}

	private void moverPeca(Move m) {
		Lugar l1 = this.lugares[m.getFromX()][m.getFromY()];
		Peca p1 = l1.getPeca();
		p1.setX(m.getToX());
		p1.setY(m.getToY());
		l1.setPeca(null);
		l1.setOcupado(false);
		if (this.lugares[m.getToX()][m.getToY()].isOcupado()) {
			Peca p2 = this.lugares[m.getToX()][m.getToY()].getPeca();
			p2.setCapturada(true);
		}
		this.lugares[m.getToX()][m.getToY()].setPeca(p1);
	}

	public void trocarVez() {
		if (this.vez == 1) {
			this.vez = 0;
			this.atual = this.p0;
		} else {
			this.vez = 1;
			this.atual = this.p1;
		}
	}

	public void testaXeque() {
		Jogador inimigo = null;
		Jogador atual = null;
		boolean xeque = false;

		atual = this.p1;
		inimigo = this.p0;

		for (int c = 1; c < 17; c++) {
			Peca pa = atual.pecas[c];
			if (pa != null && !pa.isCapturada() && pa.isMovValido(this, inimigo.rei.getX(), inimigo.rei.getY())) {
				xeque = true;
				break;
			}
		}
		if (xeque) {
			inimigo.setXeque(true);
		} else {
			inimigo.setXeque(false);
		}

		atual = this.p0;
		inimigo = this.p1;
		xeque = false;

		for (int c = 1; c < 17; c++) {
			Peca pa = atual.pecas[c];
			if (pa != null && !pa.isCapturada() && pa.isMovValido(this, inimigo.rei.getX(), inimigo.rei.getY())) {
				xeque = true;
				break;
			}
		}
		if (xeque) {
			inimigo.setXeque(true);
		} else {
			inimigo.setXeque(false);
		}
	}

	public void testaXequeMate() {
		this.xequeMate = (this.p0.rei.isCapturada() || this.p1.rei.isCapturada());
	}

	public boolean isXequeMate() {
		return this.xequeMate;
	}

	public Jogador getJogadorAtual() {
		if (vez == 1) {
			return this.p1;
		} else {
			return this.p0;
		}
	}

	public void desenharTabuleiro(Canvas can) {
		can.draw(this);
	}
}
