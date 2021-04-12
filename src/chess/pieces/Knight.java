package chess.pieces;

import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;
import tabuleiro.Position;

public class Knight extends PecaXadrez{

	public Knight(Tabuleiro Tabuleiro, Cor cor) {
		super(Tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "C";
	}

	// fala se o CAVALO pode se mover para determinada posicao ou nao
	private boolean canMove(Position position) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().piece(position);
		// verificar se a peca p nao � nula, ou seja existe a peca ali, OU se nao � uma peca advers�ria. 
		return p == null || p.getColor() != getColor();
	}
	
	@Override
	public boolean[][] MovPossivel() {
		boolean [][] mat = new boolean[getTabuleiro().getRows()][getTabuleiro().getColumns()];
		
		//movimentos possiveis do cavalo
		Position  p = new Position(0,0);

		//testes das 8 possiveis direcoes que o Rei pode se mover
		
		//1. 
		p.setValues(position.getRow() - 1, position.getColumn() -2) ; 
		if(getTabuleiro().positionExists(p) && canMove(p)){ 
			mat[p.getRow()][p.getColumn()] = true ;
		}
		
		//2. 
		p.setValues(position.getRow() - 2, position.getColumn() -1); 
		if(getTabuleiro().positionExists(p) && canMove(p)){ 			
			mat[p.getRow()][p.getColumn()] = true ;
		}		

		//3. 
		p.setValues(position.getRow()- 2, position.getColumn() +1 ); 
		if(getTabuleiro().positionExists(p) && canMove(p)){ 
			mat[p.getRow()][p.getColumn()] = true ;
		}

		//4. 
		p.setValues(position.getRow() - 2 , position.getColumn() + 1 ); 
		if(getTabuleiro().positionExists(p) && canMove(p)){
			mat[p.getRow()][p.getColumn()] = true ;
		}
		

		//5. 
		p.setValues(position.getRow() + 1 , position.getColumn() +2); 
		if(getTabuleiro().positionExists(p) && canMove(p)){ 
			mat[p.getRow()][p.getColumn()] = true ;
		}
		
		//6. 
		p.setValues(position.getRow() - 1 , position.getColumn() + 1); 
		if(getTabuleiro().positionExists(p) && canMove(p)){ 
			mat[p.getRow()][p.getColumn()] = true ;
		}
		
		//7.   
		p.setValues(position.getRow() + 2 , position.getColumn() - 1); 
		if(getTabuleiro().positionExists(p) && canMove(p)){ 
			mat[p.getRow()][p.getColumn()] = true ;
		}		
				

		//8. 
		p.setValues(position.getRow() + 1, position.getColumn() - 2 ); 
		if(getTabuleiro().positionExists(p) && canMove(p)){ 
			mat[p.getRow()][p.getColumn()] = true ;
		}
		
		return mat;
	}
}
