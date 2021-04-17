//nossas pecas
package xadrez;
import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

//subclasse de peca
public abstract class PecaXadrez extends Peca {
	
	private Cor cor;
	private int contadorMovimento; //iniciado com zero por padrao
	
	
	public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
	//obtendo a posicao da peca de xadrez 
	public PosicaoXadrex getPosicaoXadrex() {
		return PosicaoXadrex.daPosicao(posicao);
	}
	
	public int getContadorMovimento() {
		return contadorMovimento;
	}
	
	public void decrementaContadorMovimento() {
		contadorMovimento--;
	}
	
	public void IncrementaContadorMovimento() {
		contadorMovimento++;
	}
	
	protected boolean ExistePecaOponente(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		return p != null && p.getCor() != cor;  //teste se a cor da peca que esta na posicao é igual (minha) ou diferente(oponente)
	}
}
