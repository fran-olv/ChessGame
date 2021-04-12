package chess.pecas;

import tabuleiro.Tabuleiro;
import tabuleiro.Posicao;
import chess.PecaXadrez;
import chess.Color;

public class Cavalo extends PecaXadrez{

	public Cavalo(Tabuleiro tabuleiro, Color color) {
		super(Tabuleiro, color);
	}
	
	@Override
	public String toString() {
		return "C";
	}

	// fala se o CAVALO pode se mover para determinada posicao ou nao
	private boolean canMove(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		// verificar se a peca p nao � nula, ou seja existe a peca ali, OU se nao � uma peca advers�ria. 
		return p == null || p.getColor() != getColor();
	}
	
	@Override
	public boolean[][] MovPossivel() {
		boolean [][] mat = new boolean[getTabuleiro().getlinhas()][getTabuleiro().getcolunas()];
		
		//movimentos possiveis do cavalo
		Posicao  p = new Posicao(0,0);

		//testes das 8 possiveis direcoes que o Rei pode se mover
		
		//1. 
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() -2) ; 
		if(getTabuleiro().posicaoExiste(p) && canMove(p)){ 
			mat[p.getLinha()][p.getColuna()] = true ;
		}
		
		//2. 
		p.setValores(posicao.getLinha() - 2, posicao.getColuna() -1); 
		if(getTabuleiro().posicaoExiste(p) && canMove(p)){ 			
			mat[p.getLinha()][p.getColuna()] = true ;
		}		

		//3. 
		p.setValores(posicao.getLinha()- 2, posicao.getColuna() +1 ); 
		if(getTabuleiro().posicaoExiste(p) && canMove(p)){ 
			mat[p.getLinha()][p.getColuna()] = true ;
		}

		//4. 
		p.setValores(posicao.getLinha() - 2 , posicao.getColuna() + 1 ); 
		if(getTabuleiro().posicaoExiste(p) && canMove(p)){
			mat[p.getLinha()][p.getColuna()] = true ;
		}
		

		//5. 
		p.setValores(posicao.getLinha() + 1 , posicao.getColuna() +2); 
		if(getTabuleiro().posicaoExiste(p) && canMove(p)){ 
			mat[p.getLinha()][p.getColuna()] = true ;
		}
		
		//6. 
		p.setValores(posicao.getLinha() - 1 , posicao.getColuna() + 1); 
		if(getTabuleiro().posicaoExiste(p) && canMove(p)){ 
			mat[p.getLinha()][p.getColuna()] = true ;
		}
		
		//7.   
		p.setValores(posicao.getLinha() + 2 , posicao.getColuna() - 1); 
		if(getTabuleiro().posicaoExiste(p) && canMove(p)){ 
			mat[p.getLinha()][p.getColuna()] = true ;
		}		
				

		//8. 
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 2 ); 
		if(getTabuleiro().posicaoExiste(p) && canMove(p)){ 
			mat[p.getLinha()][p.getColuna()] = true ;
		}
		
		return mat;
	}
}
