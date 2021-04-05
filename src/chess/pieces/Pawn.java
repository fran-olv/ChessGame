package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {
	public Pawn(Board board, Color color) {
		super(board, color);
	}

	@Override
	public boolean[][] possibleMoves() {
		
		boolean [][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0,0);
		
		//regraDoPeaoBranco = se move linha para cima ou seja na mtriz é -1 
		if(getColor() == Color.WHITE) {
			 //1 posicao para frente
			p.setValues(position.getRow()- 1, position.getColumn());
			//testa para ver se o peao pode mover 
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { //existe a posicao e esta vazia
				mat[p.getRow()][p.getColumn()] = true;
			}
			//peao se movendo 2 linhas para frente
			p.setValues(position.getRow()- 2, position.getColumn()); //2 posicao acima
			Position p2 = new Position(position.getRow()- 1, position.getColumn());
			
			
			//testa para ver se o peao pode mover 2 casas pra frente 
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) { //existe a posicao e esta vazia
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			//teste do mov diagonal esquerda quando tiver peça adversaria
			p.setValues(position.getRow() - 1, position.getColumn() - 1 );
			//testa para ver se o peao pode mover 
			if(getBoard().positionExists(p) && IsThereOpponentPiece(p)) { //existe a posicao e tem uma peca do oponente ali
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			//teste do mov diagonal direita quando tiver peça adversaria
			p.setValues(position.getRow() - 1, position.getColumn() + 1 );
			//testa para ver se o peao pode mover 
			if(getBoard().positionExists(p) && IsThereOpponentPiece(p)) { //existe a posicao e tem uma peca do oponente ali
				mat[p.getRow()][p.getColumn()] = true;
			}
			
		}
		else { //se nao é branca é preta. OBS: o posicao ROW vai ser + 1 
			
			//1 posicao para baixo
			p.setValues(position.getRow() + 1, position.getColumn());
			//testa para ver se o peao pode mover 
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { //existe a posicao e esta vazia
				mat[p.getRow()][p.getColumn()] = true;
			}
			//peao se movendo 2 linhas para baixo
			p.setValues(position.getRow() + 2, position.getColumn()); //2 posicao acima
			Position p2 = new Position(position.getRow()- 1, position.getColumn());
			
			
			//testa para ver se o peao pode mover 2 casas parra baixo
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) { //existe a posicao e esta vazia
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			//teste do mov diagonal esquerda quando tiver peça adversaria
			p.setValues(position.getRow() + 1, position.getColumn() - 1 );
			//testa para ver se o peao pode mover 
			if(getBoard().positionExists(p) && IsThereOpponentPiece(p)) { //existe a posicao e tem uma peca do oponente ali
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			//teste do mov diagonal direita quando tiver peça adversaria
			p.setValues(position.getRow() + 1, position.getColumn() + 1 );
			//testa para ver se o peao pode mover 
			if(getBoard().positionExists(p) && IsThereOpponentPiece(p)) { //existe a posicao e tem uma peca do oponente ali
				mat[p.getRow()][p.getColumn()] = true;
			}
		}
		
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}
	

}
