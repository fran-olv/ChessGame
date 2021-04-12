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
		if(!posicaoExiste(linha,coluna)) {
			throw new TabuleiroException("Posição não existe.");
		}
		
		return pecas[linha][coluna];
	}	
		
	public Peca peca(Posicao posicao) {
		if(!posicaoExiste(posicao)) {
			throw new TabuleiroException("Posição não existe.");
		}
		
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}	
	
	//posiciona uma peca no tabuleiro
	public void posicaoPeca(Peca peca, Posicao posicao) {
		if(temPeca(posicao)) {
			throw new TabuleiroException(" Já existe uma peça nessa posição" + posicao);
		}
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}
	
	public Peca removePeca(Posicao posicao) {
		if (!posicaoExiste(posicao)) {
			throw new TabuleiroException("Posição não existe.");
		}
		if (peca(posicao) == null){
			return null;
		}
		Peca aux = peca(posicao);
			aux.posicao = null;
			pecas[posicao.getLinha()][posicao.getColuna()] = null;
			return aux;
	}
		
	public boolean posicaoExiste(int linha, int coluna) { 
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
	}
	
	public boolean posicaoExiste(Posicao posicao){
		return posicaoExiste(posicao.getLinha(), posicao.getColuna());
	}
	
	public boolean temPeca(Posicao posicao) {
		if(!posicaoExiste(posicao)) {
			throw new TabuleiroException("Posicao not on the Tabuleiro");
		}
		return peca(posicao) != null;
	}
	
}
