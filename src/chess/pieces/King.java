package chess.pieces;

import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;
import tabuleiro.Position;

public class King extends PecaXadrez{

	public King(Tabuleiro Tabuleiro, Cor cor) {
		super(Tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "R";
	}

	// fala se o rei pode se mover para determinada posicao ou nao
	private boolean canMove(Position position) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().piece(position);
		// verificar se a peca p nao � nula, ou seja existe a peca ali, OU se nao � uma peca advers�ria. 
		return p == null || p.getColor() != getColor();
	}
	
	@Override
	public boolean[][] MovPossivel() {
		boolean [][] mat = new boolean[getTabuleiro().getRows()][getTabuleiro().getColumns()];
		
		//movimentos possiveis do rei
		Position  p = new Position(0,0);

		//testes das 8 possiveis direcoes que o Rei pode se mover
		
		//1. acima
		p.setValues(position.getRow() - 1, position.getColumn()); //posicao acima do rei
		if(getTabuleiro().positionExists(p) && canMove(p)){ // se a posicao existe no tabuleiro e o rei pode mover pra ela
			mat[p.getRow()][p.getColumn()] = true ;
		}
		
		//2. abaixo
		p.setValues(position.getRow() + 1, position.getColumn()); //posicao abaixo do rei
		if(getTabuleiro().positionExists(p) && canMove(p)){ // se a posicao existe no tabuleiro e o rei pode mover pra ela
			mat[p.getRow()][p.getColumn()] = true ;
		}		

		//3. esquerda
		p.setValues(position.getRow(), position.getColumn() - 1 ); //posicao a esquerda do rei
		if(getTabuleiro().positionExists(p) && canMove(p)){ // se a posicao existe no tabuleiro e o rei pode mover pra ela
			mat[p.getRow()][p.getColumn()] = true ;
		}

		//3. direita
		p.setValues(position.getRow(), position.getColumn() + 1 ); //posicao a direita do rei
		if(getTabuleiro().positionExists(p) && canMove(p)){ // se a posicao existe no tabuleiro e o rei pode mover pra ela
			mat[p.getRow()][p.getColumn()] = true ;
		}
		

		//3. noroeste = mov em diagonal = 1 para cima e 1 para esquerda  
		p.setValues(position.getRow() - 1 , position.getColumn() - 1); //posicao a noroeste do rei
		if(getTabuleiro().positionExists(p) && canMove(p)){ // se a posicao existe no tabuleiro e o rei pode mover pra ela
			mat[p.getRow()][p.getColumn()] = true ;
		}
		
		//4. nordeste = mov em diagonal = 1 para cima e 1 para direita  
		p.setValues(position.getRow() - 1 , position.getColumn() + 1); //posicao a noroeste do rei
		if(getTabuleiro().positionExists(p) && canMove(p)){ // se a posicao existe no tabuleiro e o rei pode mover pra ela
			mat[p.getRow()][p.getColumn()] = true ;
		}
		
		//4. sudoeste = mov em diagonal = 1 para baixo e 1 para esquerda  
		p.setValues(position.getRow() + 1 , position.getColumn() - 1); //posicao a noroeste do rei
		if(getTabuleiro().positionExists(p) && canMove(p)){ // se a posicao existe no tabuleiro e o rei pode mover pra ela
			mat[p.getRow()][p.getColumn()] = true ;
		}		
				

		//3. sudeste = mov em diagonal =  1 para baixo e 1 para direta
		p.setValues(position.getRow() + 1, position.getColumn() + 1 ); //posicao a esquerda do rei
		if(getTabuleiro().positionExists(p) && canMove(p)){ // se a posicao existe no tabuleiro e o rei pode mover pra ela
			mat[p.getRow()][p.getColumn()] = true ;
		}
		
		return mat;
	}
}
