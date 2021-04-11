package boardgame;

public abstract class Piece {
	protected Position position;// protegida
	private Board board;  

	//contrutores 
	public Piece(Board board) {
		this.board = board;
		position = null; //peca recem criada nao tem posicao
		
	}

	//tabuleiro de uso interno 
	protected Board getBoard() {
		return board;
	}
	
	public abstract boolean[][] possibleMoves();
	
	//vendo se a peca pode ir para uma posicao. gancho com a subclasse.  
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	public boolean isThereAnyPossibleMove() {
		boolean [][] mat = possibleMoves();
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
