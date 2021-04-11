package tabuleiro;

public class Posicao {
	
	private int linha ;
	private	int coluna;
	
	public Posicao(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
	public int getLinha() {
		return linha;
	}
	public void setRow(int linha) {
		this.linha = linha;
	}
	public int getColumn() {
		return coluna;
	}
	public void setColumn(int coluna) {
		this.coluna = coluna;
	}
	
	//atualiza valores da posicao e da coluna
	public void setValues(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
	// printando as nossas posicoes na tela
	@Override //funcao que subscreve ...
	public String toString() {
		//a linha contatenada com a coluna.
		return linha + ", " + coluna;
	}
	


}
