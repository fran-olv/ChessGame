//Boardgame === resposavel pelo nosso tabuleiro 
package tabuleiro;

public class Tabuleiro {
	//quantidade de linhas e colunas 
	private int linhas;
	private int colunas;
	//matriz da nossas pecas 
	private Peca[][] pecas;
	
	public Tabuleiro(int linhas, int colunas) {
		if(linhas < 1 || colunas < 1) {
			throw new ExcecaoDeTabuleiro("Erro na criacao do tabuleiro: tem que existir pelo menos 1 linha e 1 coluna");
		}
		
		this.linhas = linhas;
		this.colunas = colunas;
		//quantidade de linhas e colunas da nossa matriz de pecas.
		pecas = new Peca[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}


	public int getColunas() {
		return colunas;
	}

	//printando nosso tabuleiro para o usuario visualizar...
	public Peca peca(int linha, int coluna) {
		if(!existePosicao(linha,coluna)) {
			throw new ExcecaoDeTabuleiro("Posicao nao esta no tabuleiro");
		}
		
		return pecas[linha][coluna];
	}	
		
	public Peca peca(Posicao posicao) {
		if(!existePosicao(posicao)) {
			throw new ExcecaoDeTabuleiro("Posicao nao esta no tabuleiro");
		}
		
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}	
	
	//posiciona uma peça no tabuleiro
	public void posicionaPeca(Peca peca, Posicao posicao) {
		if(existePeca(posicao)) {
			throw new ExcecaoDeTabuleiro("Ja existe uma peca na posicao: " + posicao);
		}
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}
	
	public Peca removePeca(Posicao posicao) {
		if (!existePosicao(posicao)) {
			throw new ExcecaoDeTabuleiro("Posicao nao esta no tabuleiro");
		}
		if (peca(posicao) == null){
			return null;
		}
		Peca aux = peca(posicao);
			aux.posicao = null;
			pecas[posicao.getLinha()][posicao.getColuna()] = null;
			return aux;
	}
		
	public boolean existePosicao(int linha, int coluna) { 
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
	}
	
	public boolean existePosicao(Posicao posicao){
		return existePosicao(posicao.getLinha(), posicao.getColuna());
	}
	
	public boolean existePeca(Posicao posicao) {
		if(!existePosicao(posicao)) {
			throw new ExcecaoDeTabuleiro("Posicao nao esta no tabuleiro");
		}
		return peca(posicao) != null;
	}
	
}
