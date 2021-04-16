package chess;

import tabuleiro.Posicao;

public class PosicaoXadrex {
	private char coluna;
	private int linha;
	
	
	public PosicaoXadrex(char coluna, int linha) {
		if(coluna < 'a' || coluna > 'h' || linha < 1 | linha > 8) {
			throw new ExcecaoDeXadrez(" Posicao invalida. Insira um valor de a1 ate h8. ");
		}
		this.coluna = coluna;
		this.linha = linha;
	}


	public char getColuna() {
		return coluna;
	}


	public int getLinha() {
		return linha;
	}
	
	protected Posicao paraPosicao() {
		return new Posicao( 8 - linha, coluna - 'a');
	}
	
		//converte a posicao da matrix pra posicao do xadrez
	protected static PosicaoXadrex daPosicao(Posicao posicao) {
		return new PosicaoXadrex((char)('a' + posicao.getColuna()), 8 - posicao.getLinha());
		
	}
	
	
	
	@Override
	public String toString() {
		return "" + coluna + linha;
	}
	
}

