package br.com.chess.main;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.com.chess.Canvas;
import br.com.chess.Jogador;
import br.com.chess.Peca;
import br.com.chess.Tabuleiro;

public class Chess {

	static Jogador jogadorPreto;

	static Jogador jogadorBranco;

	public static void main(String[] args) {

		JFrame janela;
		Canvas canvas;
		Tabuleiro tabuleiro;

		JLabel labelJogadorAtual;
		JLabel labelJogadorCheque0;
		JLabel labelJogadorCheque1;
		JLabel labelXequeMate;

		JPanel jPanelPrincipal;
		JPanel jPanelSecundario;

		boolean continuar = true;
		boolean repete = false;
		do {
			janela = new JFrame("Xadrez");
			canvas = new Canvas();
			jogadorPreto = new Jogador(Peca.COR_PRETA, "Jogador 1: Preto", canvas);
			jogadorBranco = new Jogador(Peca.COR_BRANCA, "Jogador 2: Branco", canvas);
			tabuleiro = new Tabuleiro(jogadorPreto, jogadorBranco);

			labelJogadorAtual = new JLabel("Jogador atual: " + tabuleiro.getJogadorAtual().printNome());
			labelJogadorAtual.setPreferredSize(new Dimension(230, 50));

			labelJogadorCheque0 = new JLabel("");
			labelJogadorCheque0.setPreferredSize(new Dimension(230, 50));

			labelJogadorCheque1 = new JLabel("");
			labelJogadorCheque1.setPreferredSize(new Dimension(230, 50));

			labelXequeMate = new JLabel("");
			labelXequeMate.setPreferredSize(new Dimension(230, 50));

			// painel secundario
			jPanelSecundario = new JPanel();
			jPanelSecundario.setPreferredSize(new Dimension(230, 320));
			jPanelSecundario.setLayout(new BoxLayout(jPanelSecundario, BoxLayout.Y_AXIS));
			jPanelSecundario.add(labelJogadorAtual);
			jPanelSecundario.add(labelJogadorCheque1);
			jPanelSecundario.add(labelJogadorCheque0);
			jPanelSecundario.add(labelXequeMate);

			// painel principal
			jPanelPrincipal = new JPanel();
			jPanelPrincipal.setPreferredSize(new Dimension(650, 320));
			jPanelPrincipal.setLayout(new BoxLayout(jPanelPrincipal, BoxLayout.X_AXIS));
			jPanelPrincipal.add(canvas);
			jPanelPrincipal.add(jPanelSecundario);

			janela.getContentPane().add(jPanelPrincipal);
			janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			janela.pack();
			janela.setVisible(true);

			tabuleiro.desenharTabuleiro(canvas);

			while (continuar) {

				// validar xeque
				if (jogadorPreto.isXeque()) {
					labelJogadorCheque1.setText("Jogador 1 Xeque");
				} else {
					labelJogadorCheque1.setText("");
				}
				if (jogadorBranco.isXeque()) {
					labelJogadorCheque0.setText("Jogador 2 Xeque ");
				} else {
					labelJogadorCheque0.setText("");
				}

				labelJogadorAtual.setText("Jogador atual: " + tabuleiro.getJogadorAtual().printNome());

				if (tabuleiro.nextMov()) {
					if (!tabuleiro.isXequeMate()) {
						tabuleiro.trocarVez();

					} else {
						continuar = false;
					}
				}
				tabuleiro.desenharTabuleiro(canvas);
			}

			labelXequeMate.setText("XequeMate \n");
			labelXequeMate.setText(labelXequeMate.getText() + "Vencedor: " + tabuleiro.getJogadorAtual().printNome());

			if (JOptionPane.showConfirmDialog(null, "Novo Jogo?", "XequeMate",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				janela.dispose();
				repete = true;
			} else {
				janela.dispose();
				repete = false;
			}

		} while (repete);
	}
}
