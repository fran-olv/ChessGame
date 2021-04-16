// Bordgame ==> funcao responsavel pelo nosso tabuleiro
package tabuleiro;

public abstract class Peca {
	//posicao do nosso tabuleiro
	protected Posicao posicao;
	private Tabuleiro tabuleiro;
	
	//a posicao nula
	public Peca(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		posicao = null; //peca recem criada nao tem posicao
		
	}
	
	//o tabuleiro so vai ser acessado pelas classe das pecas e tabuleiro
	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
	public abstract boolean[][] movimentoPossivel();
	
	public boolean movimentoPossivel(Posicao posicao) {
		return movimentoPossivel()[posicao.getLinha()][posicao.getColuna()];
	}
	
	public boolean ExisteMovimentoPossivel() {
		boolean [][] mat = movimentoPossivel();
		for(int i=0; i<mat.length; i++) {
			for (int j=0; j<mat.length; j++) {
				if(mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}

	
	
}
