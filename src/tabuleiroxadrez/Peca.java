// Bordgame ==> funcao responsavel pelo nosso tabuleiro
package tabuleiro;

public abstract class Peca {
	//posicao do nosso tabuleiro
	protected Posicao posicao;// protegida
	private Tabuleiro Tabuleiro;
	
	//a posicao nula
	public Peca(Tabuleiro Tabuleiro) {
		this.Tabuleiro = Tabuleiro;
		posicao = null; //peca recem criada nao tem posicao
		
	}
	//o tabuleiro so vai ser acessado pelas classe das pecas e tabuleiro
	protected Tabuleiro getTabuleiro() {
		return Tabuleiro;
	}
	
	public abstract boolean[][] MovPossivel();
	
	public boolean possibleMove(Posicao posicao) {
		return MovPossivel()[posicao.getLinha()][posicao.getColuna()];
	}
	
	public boolean MovimentoPossivel() {
		boolean [][] mat = MovPossivel();
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
