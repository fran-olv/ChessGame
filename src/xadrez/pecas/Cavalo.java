package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Cavalo extends PecaXadrez{

	public Cavalo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "C";
	}

	// fala se o CAVALO pode se mover para determinada posicao ou nao
	private boolean podeMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		// verificar se a peca p nao é nula, ou seja existe a peca ali, OU se nao é uma peca adversária. 
		return p == null || p.getCor() != getCor();
	}
	
	@Override
	public boolean[][] movimentoPossivel() {
		boolean [][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		//movimentos possiveis do cavalo
		Posicao  p = new Posicao(0,0);

		//testes das 8 possiveis direcoes que o Rei pode se mover
		
		//1. 
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() -2) ; 
		if(getTabuleiro().existePosicao(p) && podeMover(p)){ 
			mat[p.getLinha()][p.getColuna()] = true ;
		}
		
		//2. 
		p.setValores(posicao.getLinha() - 2, posicao.getColuna() -1); 
		if(getTabuleiro().existePosicao(p) && podeMover(p)){ 			
			mat[p.getLinha()][p.getColuna()] = true ;
		}		

		//3. 
		p.setValores(posicao.getLinha() - 2, posicao.getColuna() + 1); 
		if(getTabuleiro().existePosicao(p) && podeMover(p)){ 
			mat[p.getLinha()][p.getColuna()] = true ;
		}

		//4. 
		p.setValores(posicao.getLinha() - 1 , posicao.getColuna() + 2 ); 
		if(getTabuleiro().existePosicao(p) && podeMover(p)){
			mat[p.getLinha()][p.getColuna()] = true ;
		}
		

		//5. 
		p.setValores(posicao.getLinha() + 1 , posicao.getColuna() + 2); 
		if(getTabuleiro().existePosicao(p) && podeMover(p)){ 
			mat[p.getLinha()][p.getColuna()] = true ;
		}
		
		//6. 
		p.setValores(posicao.getLinha() + 2, posicao.getColuna() + 1); 
		if(getTabuleiro().existePosicao(p) && podeMover(p)){ 
			mat[p.getLinha()][p.getColuna()] = true ;
		}
		
		//7.   
		p.setValores(posicao.getLinha() + 2 , posicao.getColuna() - 1); 
		if(getTabuleiro().existePosicao(p) && podeMover(p)){ 
			mat[p.getLinha()][p.getColuna()] = true ;
		}		
				

		//8. 
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 2 ); 
		if(getTabuleiro().existePosicao(p) && podeMover(p)){ 
			mat[p.getLinha()][p.getColuna()] = true ;
		}
		
		return mat;
	}
}
