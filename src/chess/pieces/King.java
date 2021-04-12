package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{

	public King(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "R";
	}

	// fala se o rei pode se mover para determinada posicao ou nao
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		// verificar se a peca p nao é nula, ou seja existe a peca ali, OU se nao é uma peca adversária. 
		return p == null || p.getColor() != getColor();
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean [][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		//movimentos possiveis do rei
		Position  p = new Position(0,0);

		//testes das 8 possiveis direcoes que o Rei pode se mover
		
		//1. acima
		p.setValues(position.getRow() - 1, position.getColumn()); //posicao acima do rei
		if(getBoard().positionExists(p) && canMove(p)){ // se a posicao existe no tabuleiro e o rei pode mover pra ela
			mat[p.getRow()][p.getColumn()] = true ;
		}
		
		//2. abaixo
		p.setValues(position.getRow() + 1, position.getColumn()); //posicao abaixo do rei
		if(getBoard().positionExists(p) && canMove(p)){ // se a posicao existe no tabuleiro e o rei pode mover pra ela
			mat[p.getRow()][p.getColumn()] = true ;
		}		

		//3. esquerda
		p.setValues(position.getRow(), position.getColumn() - 1 ); //posicao a esquerda do rei
		if(getBoard().positionExists(p) && canMove(p)){ // se a posicao existe no tabuleiro e o rei pode mover pra ela
			mat[p.getRow()][p.getColumn()] = true ;
		}

		//3. direita
		p.setValues(position.getRow(), position.getColumn() + 1 ); //posicao a direita do rei
		if(getBoard().positionExists(p) && canMove(p)){ // se a posicao existe no tabuleiro e o rei pode mover pra ela
			mat[p.getRow()][p.getColumn()] = true ;
		}
		

		//3. noroeste = mov em diagonal = 1 para cima e 1 para esquerda  
		p.setValues(position.getRow() - 1 , position.getColumn() - 1); //posicao a noroeste do rei
		if(getBoard().positionExists(p) && canMove(p)){ // se a posicao existe no tabuleiro e o rei pode mover pra ela
			mat[p.getRow()][p.getColumn()] = true ;
		}
		
		//4. nordeste = mov em diagonal = 1 para cima e 1 para direita  
		p.setValues(position.getRow() - 1 , position.getColumn() + 1); //posicao a noroeste do rei
		if(getBoard().positionExists(p) && canMove(p)){ // se a posicao existe no tabuleiro e o rei pode mover pra ela
			mat[p.getRow()][p.getColumn()] = true ;
		}
		
		//4. sudoeste = mov em diagonal = 1 para baixo e 1 para esquerda  
		p.setValues(position.getRow() + 1 , position.getColumn() - 1); //posicao a noroeste do rei
		if(getBoard().positionExists(p) && canMove(p)){ // se a posicao existe no tabuleiro e o rei pode mover pra ela
			mat[p.getRow()][p.getColumn()] = true ;
		}		
				

		//3. sudeste = mov em diagonal =  1 para baixo e 1 para direta
		p.setValues(position.getRow() + 1, position.getColumn() + 1 ); //posicao a esquerda do rei
		if(getBoard().positionExists(p) && canMove(p)){ // se a posicao existe no tabuleiro e o rei pode mover pra ela
			mat[p.getRow()][p.getColumn()] = true ;
		}
		
		return mat;
	}
}
