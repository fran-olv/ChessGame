//nossas pecas
package chess;
//importando as nossas classes
import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

//subclasse de peca
public abstract class ChessPiece extends Piece {
	
	private Color color;
	private int moveCount; //iniciado com zero por padrao
	
	
	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	//obtendo a posicao da peca de xadrex 
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}
	
	public int getMoveCount() {
		return moveCount;
	}
	
	public void decreaseMoveCount() {
		moveCount--;
	}
	
	public void increaseMoveCount() {
		moveCount++;
	}
	
	protected boolean IsThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p.getColor() != color;  //teste se a cor da peca que esta na posicao é igual (minha) ou diferente(oponente)
	}
}
