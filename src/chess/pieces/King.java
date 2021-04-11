package chess.pecas;

import tabuleiro.Tabuleiro;
import tabuleiro.Posicao;
import chess.PecaXadrez;
import chess.Color;

public class King extends PecaXadrez{

	public King(Tabuleiro Tabuleiro, Color color) {
		super(Tabuleiro, color);
	}
	
	@Override
	public String toString() {
		return "R";
	}

	// fala se o rei pode se mover para determinada posicao ou nao
	private boolean canMove(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		// verificar se a peca p nao � nula, ou seja existe a peca ali, OU se nao � uma peca advers�ria. 
		return p == null || p.getColor() != getColor();
	}
	
	@Override
	public boolean[][] MovPossivel() {
		boolean [][] mat = new boolean[getTabuleiro().getlinhas()][getTabuleiro().getcolunas()];
		
		//movimentos possiveis do rei
		Posicao  p = new Posicao(0,0);

		//testes das 8 possiveis direcoes que o Rei pode se mover
		
		//1. acima
		p.setValues(posicao.getLinha() - 1, posicao.getColumn()); //posicao acima do rei
		if(getTabuleiro().positionExists(p) && canMove(p)){ // se a posicao existe no tabuleiro e o rei pode mover pra ela
			mat[p.getLinha()][p.getColumn()] = true ;
		}
		
		//2. abaixo
		p.setValues(posicao.getLinha() + 1, posicao.getColumn()); //posicao abaixo do rei
		if(getTabuleiro().positionExists(p) && canMove(p)){ // se a posicao existe no tabuleiro e o rei pode mover pra ela
			mat[p.getLinha()][p.getColumn()] = true ;
		}		

		//3. esquerda
		p.setValues(posicao.getLinha(), posicao.getColumn() - 1 ); //posicao a esquerda do rei
		if(getTabuleiro().positionExists(p) && canMove(p)){ // se a posicao existe no tabuleiro e o rei pode mover pra ela
			mat[p.getLinha()][p.getColumn()] = true ;
		}

		//3. direita
		p.setValues(posicao.getLinha(), posicao.getColumn() + 1 ); //posicao a direita do rei
		if(getTabuleiro().positionExists(p) && canMove(p)){ // se a posicao existe no tabuleiro e o rei pode mover pra ela
			mat[p.getLinha()][p.getColumn()] = true ;
		}
		

		//3. noroeste = mov em diagonal = 1 para cima e 1 para esquerda  
		p.setValues(posicao.getLinha() - 1 , posicao.getColumn() - 1); //posicao a noroeste do rei
		if(getTabuleiro().positionExists(p) && canMove(p)){ // se a posicao existe no tabuleiro e o rei pode mover pra ela
			mat[p.getLinha()][p.getColumn()] = true ;
		}
		
		//4. nordeste = mov em diagonal = 1 para cima e 1 para direita  
		p.setValues(posicao.getLinha() - 1 , posicao.getColumn() + 1); //posicao a noroeste do rei
		if(getTabuleiro().positionExists(p) && canMove(p)){ // se a posicao existe no tabuleiro e o rei pode mover pra ela
			mat[p.getLinha()][p.getColumn()] = true ;
		}
		
		//4. sudoeste = mov em diagonal = 1 para baixo e 1 para esquerda  
		p.setValues(posicao.getLinha() + 1 , posicao.getColumn() - 1); //posicao a noroeste do rei
		if(getTabuleiro().positionExists(p) && canMove(p)){ // se a posicao existe no tabuleiro e o rei pode mover pra ela
			mat[p.getLinha()][p.getColumn()] = true ;
		}		
				

		//3. sudeste = mov em diagonal =  1 para baixo e 1 para direta
		p.setValues(posicao.getLinha() + 1, posicao.getColumn() + 1 ); //posicao a esquerda do rei
		if(getTabuleiro().positionExists(p) && canMove(p)){ // se a posicao existe no tabuleiro e o rei pode mover pra ela
			mat[p.getLinha()][p.getColumn()] = true ;
		}
		
		return mat;
	}
}
