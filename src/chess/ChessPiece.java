package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

//subclasse da Piece
public abstract class ChessPiece extends Piece {
	
	private Color color;
	
	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	protected boolean IsThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p.getColor() != color;  //teste se a cor da peca que esta na posicao � igual (minha) ou diferente(oponente)
		
	}
}
