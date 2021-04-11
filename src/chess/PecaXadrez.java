//nossas pecas
package chess;
//importando as nossas classes
import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

//subclasse de peca
public abstract class PecaXadrez extends Piece {
	
	private Color color;
	private int moveCount; //iniciado com zero por padrao
	
	
	public PecaXadrez(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	//obtendo a posicao da peca de Xadrez 
	public PosicaoXadrez getPosicaoXadrez() {
		return PosicaoXadrez.fromPosition(position);
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
		PecaXadrez p = (PecaXadrez)getBoard().piece(position);
		return p != null && p.getColor() != color;  //teste se a cor da peca que esta na posicao � igual (minha) ou diferente(oponente)
	}
}
