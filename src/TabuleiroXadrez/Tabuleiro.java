//tabuleiro === resposavel pelo nosso tabuleiro 
package tabuleiro;

public class Tabuleiro {
	//quantidade de linhas e colunas 
	private int linhas;
	private int colunas;
	//matriz da nossas pecas 
	private Peca[][] pecas;
	
	public Tabuleiro(int linhas, int colunas) {
		if(linhas < 1 || colunas < 1) {
			throw new TabuleiroException("Erro ao criar o Tabuleiro: necessita ter pelo menos 1 linha e 1 coluna");
		}
		
		this.linhas = linhas;
		this.colunas = colunas;
		//quantidade de linhas e colunas da nossa matriz de pecas.
		pecas = new Peca[linhas][colunas];
	}

	public int getlinhas() {
		return linhas;
	}


	public int getcolunas() {
		return colunas;
	}

	//printando nosso tabuleiro para o usuario visualizar...
	public Peca peca(int linha, int coluna) {
		if(!positionExists(linha,coluna)) {
			throw new TabuleiroException("Posição não existe.");
		}
		
		return pecas[linha][coluna];
	}	
		
	public Peca peca(Posicao posicao) {
		if(!positionExists(posicao)) {
			throw new TabuleiroException("Posicao not on the Tabuleiro");
		}
		
		return pecas[posicao.getLinha()][posicao.getColumn()];
	}	
	
	//posiciona uma pe�a no tabuleiro
	public void placePiece(Peca peca, Posicao posicao) {
		if(thereIsAPiece(posicao)) {
			throw new TabuleiroException("There is already a peca on the posicao " + posicao);
		}
		pecas[posicao.getLinha()][posicao.getColumn()] = peca;
		peca.posicao = posicao;
	}
	
	public Peca removePiece(Posicao posicao) {
		if (!positionExists(posicao)) {
			throw new TabuleiroException("Posicao not on the Tabuleiro");
		}
		if (peca(posicao) == null){
			return null;
		}
		Peca aux = peca(posicao);
			aux.posicao = null;
			pecas[posicao.getLinha()][posicao.getColumn()] = null;
			return aux;
	}
		
	public boolean positionExists(int linha, int coluna) { 
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
	}
	
	public boolean positionExists(Posicao posicao){
		return positionExists(posicao.getLinha(), posicao.getColumn());
	}
	
	public boolean thereIsAPiece(Posicao posicao) {
		if(!positionExists(posicao)) {
			throw new TabuleiroException("Posicao not on the Tabuleiro");
		}
		return peca(posicao) != null;
	}
	
}
