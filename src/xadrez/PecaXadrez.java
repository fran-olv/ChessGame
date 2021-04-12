//nossas pecas
package xadrez;
//importando as nossas classes
import tabuleiro.Tabuleiro;
import tabuleiro.Peca;
import tabuleiro.Posicao;

//subclasse de peca
public abstract class PecaXadrez extends Peca {
	
	private Cor cor;
	private int moveCount; //iniciado com zero por padrao
	
	
	public PecaXadrez(Tabuleiro Tabuleiro, Cor cor) {
		super(Tabuleiro);
		this.cor = cor;
	}

<<<<<<< Updated upstream:src/chess/PecaXadrez.java
	public Cor getCor() {
=======
	public Cor getColor() {
>>>>>>> Stashed changes:src/xadrez/PecaXadrez.java
		return cor;
	}
	
	//obtendo a posicao da peca de Xadrez 
	public PosicaoXadrez getPosicaoXadrez() {
		return PosicaoXadrez.fromPosition(posicao);
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
	
<<<<<<< Updated upstream:src/chess/PecaXadrez.java
	protected boolean temPecaOponente(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		return p != null && p.getCor() != cor;  //teste se a cor da peca que esta na posicao � igual (minha) ou diferente(oponente)
=======
	protected boolean IsThereOpponentPiece(Position position) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().piece(position);
		return p != null && p.getColor() != cor;  //teste se a cor da peca que esta na posicao � igual (minha) ou diferente(oponente)
>>>>>>> Stashed changes:src/xadrez/PecaXadrez.java
	}
}
