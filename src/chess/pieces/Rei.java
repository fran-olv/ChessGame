package chess.pecas;

import tabuleiro.Tabuleiro;
import tabuleiro.Posicao;
import chess.PecaXadrez;
import chess.Cor;

public class Rei extends PecaXadrez{

	public Rei(Tabuleiro Tabuleiro, Cor cor) {
		super(Tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "R";
	}

	// fala se o rei pode se mover para determinada posicao ou nao
	private boolean podeMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		// verificar se a peca p nao � nula, ou seja existe a peca ali, OU se nao � uma peca advers�ria. 
		return p == null || p.getCor() != getCor();
	}
	
	@Override
	public boolean[][] MovPossivel() {
		boolean [][] mat = new boolean[getTabuleiro().getlinhas()][getTabuleiro().getcolunas()];
		
		//movimentos possiveis do rei
		Posicao  p = new Posicao(0,0);

		//testes das 8 possiveis direcoes que o Rei pode se mover
		
		//1. acima
		p.setValores(posicao.getLinha() - 1, posicao.getColuna()); //posicao acima do rei
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)){ // se a posicao existe no tabuleiro e o rei pode mover pra ela
			mat[p.getLinha()][p.getColuna()] = true ;
		}
		
		//2. abaixo
		p.setValores(posicao.getLinha() + 1, posicao.getColuna()); //posicao abaixo do rei
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)){ // se a posicao existe no tabuleiro e o rei pode mover pra ela
			mat[p.getLinha()][p.getColuna()] = true ;
		}		

		//3. esquerda
		p.setValores(posicao.getLinha(), posicao.getColuna() - 1 ); //posicao a esquerda do rei
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)){ // se a posicao existe no tabuleiro e o rei pode mover pra ela
			mat[p.getLinha()][p.getColuna()] = true ;
		}

		//3. direita
		p.setValores(posicao.getLinha(), posicao.getColuna() + 1 ); //posicao a direita do rei
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)){ // se a posicao existe no tabuleiro e o rei pode mover pra ela
			mat[p.getLinha()][p.getColuna()] = true ;
		}
		

		//3. noroeste = mov em diagonal = 1 para cima e 1 para esquerda  
		p.setValores(posicao.getLinha() - 1 , posicao.getColuna() - 1); //posicao a noroeste do rei
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)){ // se a posicao existe no tabuleiro e o rei pode mover pra ela
			mat[p.getLinha()][p.getColuna()] = true ;
		}
		
		//4. nordeste = mov em diagonal = 1 para cima e 1 para direita  
		p.setValores(posicao.getLinha() - 1 , posicao.getColuna() + 1); //posicao a noroeste do rei
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)){ // se a posicao existe no tabuleiro e o rei pode mover pra ela
			mat[p.getLinha()][p.getColuna()] = true ;
		}
		
		//4. sudoeste = mov em diagonal = 1 para baixo e 1 para esquerda  
		p.setValores(posicao.getLinha() + 1 , posicao.getColuna() - 1); //posicao a noroeste do rei
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)){ // se a posicao existe no tabuleiro e o rei pode mover pra ela
			mat[p.getLinha()][p.getColuna()] = true ;
		}		
				

		//3. sudeste = mov em diagonal =  1 para baixo e 1 para direta
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1 ); //posicao a esquerda do rei
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)){ // se a posicao existe no tabuleiro e o rei pode mover pra ela
			mat[p.getLinha()][p.getColuna()] = true ;
		}
		
		return mat;
	}
}
