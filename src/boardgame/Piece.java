// Bordgame ==> funcao responsavel pelo nosso tabuleiro
package boardgame;

public abstract class Piece {
	//posicao do nosso tabuleiro
	protected Position position;// protegida
	private Board board;
	
	//a posicao nula
	public Piece(Board board) {
		this.board = board;
		position = null; //peca recem criada nao tem posicao
		
	}
	//o tabuleiro so vai ser acessado pelas classe das pecas e tabuleiro
	protected Board getBoard() {
		return board;
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
