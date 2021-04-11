// Bordgame ==> funcao responsavel pelo nosso tabuleiro
package tabuleiro;

public abstract class Peca {
	//posicao do nosso tabuleiro
	protected Position position;// protegida
	private Tabuleiro Tabuleiro;
	
	//a posicao nula
	public Peca(Tabuleiro Tabuleiro) {
		this.Tabuleiro = Tabuleiro;
		position = null; //peca recem criada nao tem posicao
		
	}
	//o tabuleiro so vai ser acessado pelas classe das pecas e tabuleiro
	protected Tabuleiro getTabuleiro() {
		return Tabuleiro;
	}
	
	public abstract boolean[][] MovPossivel();
	
	public boolean possibleMove(Position position) {
		return MovPossivel()[position.getRow()][position.getColumn()];
	}
	
	public boolean isThereAnyPossibleMove() {
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
