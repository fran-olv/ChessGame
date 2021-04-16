//nossa partida de xadrez, aqui estao as nossas regras.
package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import chess.pieces.Bispo;
import chess.pieces.Rei;
import chess.pieces.Cavalo;
import chess.pieces.Piao;
import chess.pieces.Rainha;
import chess.pieces.Torre;
import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;


//
public class Partida {
//variaveis que controlam o turno e qual jogador está na vez
	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	
	//propriedade do xeque e xeque mate
	private boolean xeque;
	private boolean xequeMate;

	
//lista que controlam as pecas que estao no jogo e as que estao fora
	private List<Peca> pecasEmJogo = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();
	
	public Partida() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1; // primeiro turno 
		jogadorAtual = Cor.BRANCO; // primeiro a jogar peca branca
		configuracaoInicial(); //<<<<TROCAR pra chamada do Xeque >>>>>
		
	}
	
	//gets das variaveis: turno, Jogador atual, xeque e xeque mate
	public int getTurno() {
		return turno;
	}
	
	
	public Cor getJogadorAtual() {
		return jogadorAtual;
	}
	//retorna  apropriedade de xeque
	public boolean getXeque() {
		return xeque;
	}
	
	public boolean getXequeMate() {
		return xequeMate;
	}
	
	//mapeando as pecas no tabuleiro

	public PecaXadrez[][] getPieces() {
		PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i=0; i<tabuleiro.getLinhas(); i++) {
			for (int j=0; j<tabuleiro.getColunas(); j++) {
				mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}

		//classe que retorna uma matriz de booleano com os possiveis movimentos da peï¿½a

	
	public boolean[][] MovimentosPossiveis(PosicaoXadrex sourcePosition) {
		Posicao posicao = sourcePosition.paraPosicao();
		validaPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).movimentoPossivel();
	}
	
	// movimentando as pecas
	public PecaXadrez facaMovimento(PosicaoXadrex sourcePosition, PosicaoXadrex targetPosition) {
		Posicao source = sourcePosition.paraPosicao();
		Posicao target = targetPosition.paraPosicao();
		validaPosicaoOrigem(source);
		validaPosicaoDestino(source, target);
		Peca capturedPeca = movimento(source, target);
		
		if (testeXeque(jogadorAtual)) { //se o jogador entrar em xeque
			desfazMovimento(source, target, capturedPeca);
			throw new ExcecaoDeXadrez("Voce nao pode se colocar em xeque");
		}
			//se o oponente entrou em xeque		
		xeque = (testeXeque(oponente(jogadorAtual))) ? true : false; //verdadeiro senao falso

		if (testeXequeMate(oponente(jogadorAtual))) {
			xequeMate = true;
		}
		else {
			proximoTurno();
		}
		
		return (PecaXadrez)capturedPeca;
	}
	
	private Peca movimento(Posicao source, Posicao target) {
		Peca p = tabuleiro.removePeca(source);
		Peca capturedPeca = tabuleiro.removePeca(target);
		tabuleiro.posicionaPeca(p, target);
		
		if (capturedPeca != null) {
			pecasEmJogo.remove(capturedPeca);
			pecasCapturadas.add(capturedPeca);
		}
		
		return capturedPeca;
	}
	
	private void desfazMovimento(Posicao source, Posicao target, Peca capturedPeca) {
		Peca p = tabuleiro.removePeca(target);
		tabuleiro.posicionaPeca(p, source);
		
		if (capturedPeca != null) {
			tabuleiro.posicionaPeca(capturedPeca, target);
			pecasCapturadas.remove(capturedPeca);
			pecasEmJogo.add(capturedPeca);
		}
	}
	
	private void validaPosicaoOrigem(Posicao posicao) {
		if (!tabuleiro.existePeca(posicao)) {
			throw new ExcecaoDeXadrez("Nao existe peca na posicao solicitada");
		}
		if (jogadorAtual != ((PecaXadrez)tabuleiro.peca(posicao)).getCor()) {
			throw new ExcecaoDeXadrez("A peca escolhida nao e sua");
		}
		if (!tabuleiro.peca(posicao).ExisteMovimentoPossivel()) {
			throw new ExcecaoDeXadrez("Nao existe movimento para peca escolhida");
		}
	}
	
	private void validaPosicaoDestino(Posicao source, Posicao target) {
		if (!tabuleiro.peca(source).movimentoPossivel(target)) {
			throw new ExcecaoDeXadrez("A peca escolhida nao pode se mover para a posicao de destino");
		}
	}
	
	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private Cor oponente(Cor cor) {
		return (cor == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private PecaXadrez rei(Cor cor) {
		List<Peca> list = pecasEmJogo.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList());
		for (Peca p : list) {
			if (p instanceof Rei) {
				return (PecaXadrez)p;
			}
		}
		throw new IllegalStateException("Nao existe Rei da " + cor + " no tabuleiro ");
	}
	
	private boolean testeXeque(Cor cor) {
		Posicao kingPosicao = rei(cor).getPosicaoXadrex().paraPosicao();
		List<Peca> opponentPecas = pecasEmJogo.stream().filter(x -> ((PecaXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
		for (Peca p : opponentPecas) {
			boolean[][] mat = p.movimentoPossivel();
			if (mat[kingPosicao.getLinha()][kingPosicao.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testeXequeMate(Cor cor) {
		if (!testeXeque(cor)) {
			return false;
		}
		List<Peca> list = pecasEmJogo.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList());
		for (Peca p : list) {
			boolean[][] mat = p.movimentoPossivel();
			for (int i=0; i<tabuleiro.getLinhas(); i++) {
				for (int j=0; j<tabuleiro.getColunas(); j++) {
					if (mat[i][j]) {
						Posicao source = ((PecaXadrez)p).getPosicaoXadrex().paraPosicao();
						Posicao target = new Posicao(i, j);
						Peca capturedPeca = movimento(source, target);
						boolean testCheck = testeXeque(cor);
						desfazMovimento(source, target, capturedPeca);
						if (!testCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}	
	
	private void posicionandoNovaPeca(char column, int row, PecaXadrez piece) {
		tabuleiro.posicionaPeca(piece, new PosicaoXadrex(column, row).paraPosicao());
		pecasEmJogo.add(piece);
	}
	
	//demonstrar captura, troca de turno, possiveis movimentos
	private void configuracaoInicial() { 
		posicionandoNovaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCO));
		posicionandoNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		posicionandoNovaPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
		posicionandoNovaPeca('d', 1, new Rainha(tabuleiro, Cor.BRANCO));
		posicionandoNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO));
		posicionandoNovaPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
		posicionandoNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		posicionandoNovaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
		posicionandoNovaPeca('a', 2, new Piao(tabuleiro, Cor.BRANCO));
		posicionandoNovaPeca('b', 2, new Piao(tabuleiro, Cor.BRANCO));
		posicionandoNovaPeca('c', 2, new Piao(tabuleiro, Cor.BRANCO));
		posicionandoNovaPeca('d', 2, new Piao(tabuleiro, Cor.BRANCO));
		posicionandoNovaPeca('e', 2, new Piao(tabuleiro, Cor.BRANCO));
		posicionandoNovaPeca('f', 2, new Piao(tabuleiro, Cor.BRANCO));
		posicionandoNovaPeca('g', 2, new Piao(tabuleiro, Cor.BRANCO));
		posicionandoNovaPeca('h', 2, new Piao(tabuleiro, Cor.BRANCO));
		
		posicionandoNovaPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
		posicionandoNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETO));
		posicionandoNovaPeca('c', 8, new Bispo(tabuleiro, Cor.PRETO));
		posicionandoNovaPeca('e', 8, new Rainha(tabuleiro, Cor.PRETO));
		posicionandoNovaPeca('d', 8, new Rei(tabuleiro, Cor.PRETO));
		posicionandoNovaPeca('f', 8, new Bispo(tabuleiro, Cor.PRETO));
		posicionandoNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETO));
		posicionandoNovaPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
		posicionandoNovaPeca('a', 7, new Piao(tabuleiro, Cor.PRETO));
		posicionandoNovaPeca('b', 7, new Piao(tabuleiro, Cor.PRETO));
		posicionandoNovaPeca('c', 7, new Piao(tabuleiro, Cor.PRETO));
		posicionandoNovaPeca('d', 7, new Piao(tabuleiro, Cor.PRETO));
		posicionandoNovaPeca('e', 7, new Piao(tabuleiro, Cor.PRETO));
		posicionandoNovaPeca('f', 7, new Piao(tabuleiro, Cor.PRETO));
		posicionandoNovaPeca('g', 7, new Piao(tabuleiro, Cor.PRETO));
		posicionandoNovaPeca('h', 7, new Piao(tabuleiro, Cor.PRETO));
	}
	
//ESCREVER cheque indo pra cheque mate
	/*
	 * private void 1movimentoProCheckMate() {  }
	 */
	
}

