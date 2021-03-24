package boardgame;

public class Piece {
	protected Position position;// protegida
	private Board board;
	
	public Piece(Board board) {
		this.board = board;
		position = null; //peca recem criada nao tem posicao
		
	}

	protected Board getBoard() {
		return board;
	}


	
	
}
