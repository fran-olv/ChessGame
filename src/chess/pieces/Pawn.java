package chess.pieces;

import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;
import tabuleiro.Position;

public class Pawn extends PecaXadrez {

	public Pawn(Tabuleiro Tabuleiro, Cor cor) {
		super(Tabuleiro, cor);
		
	}

	@Override
	public boolean[][] MovPossivel() {
		
		boolean [][] mat = new boolean[getTabuleiro().getRows()][getTabuleiro().getColumns()];
		
		Position p = new Position(0,0);
		
		//regraDoPeaoBranco = se move linha para cima ou seja na mtriz � -1 
		if(getColor() == Cor.WHITE) {
			 //1 posicao para frente
			p.setValues(position.getRow()- 1, position.getColumn());
			//testa para ver se o peao pode mover 
			if(getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)) { //existe a posicao e esta vazia
				mat[p.getRow()][p.getColumn()] = true;
			}
			//peao se movendo 2 linhas para frente
			p.setValues(position.getRow()- 2, position.getColumn()); //2 posicao acima
			Position p2 = new Position(position.getRow()- 1, position.getColumn());
			
			
			//testa para ver se o peao pode mover 2 casas pra frente 
			if(getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p) && getTabuleiro().positionExists(p2) && !getTabuleiro().thereIsAPiece(p2) && getMoveCount() == 0) { //existe a posicao e esta vazia
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			//teste do mov diagonal esquerda quando tiver pe�a adversaria
			p.setValues(position.getRow() - 1, position.getColumn() - 1 );
			//testa para ver se o peao pode mover 
			if(getTabuleiro().positionExists(p) && IsThereOpponentPiece(p)) { //existe a posicao e tem uma peca do oponente ali
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			//teste do mov diagonal direita quando tiver pe�a adversaria
			p.setValues(position.getRow() - 1, position.getColumn() + 1 );
			//testa para ver se o peao pode mover 
			if(getTabuleiro().positionExists(p) && IsThereOpponentPiece(p)) { //existe a posicao e tem uma peca do oponente ali
				mat[p.getRow()][p.getColumn()] = true;
			}
			
		}
		else { //se nao � branca � preta. OBS: o posicao ROW vai ser + 1 
			
			//1 posicao para baixo
			p.setValues(position.getRow() + 1, position.getColumn());
			//testa para ver se o peao pode mover 
			if(getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)) { //existe a posicao e esta vazia
				mat[p.getRow()][p.getColumn()] = true;
			}
			//peao se movendo 2 linhas para baixo
			p.setValues(position.getRow() + 2, position.getColumn()); //2 posicao acima
			Position p2 = new Position(position.getRow()- 1, position.getColumn());
			
			
			//testa para ver se o peao pode mover 2 casas parra baixo
			if(getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p) && getTabuleiro().positionExists(p2) && !getTabuleiro().thereIsAPiece(p2) && getMoveCount() == 0) { //existe a posicao e esta vazia
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			//teste do mov diagonal esquerda quando tiver pe�a adversaria
			p.setValues(position.getRow() + 1, position.getColumn() - 1 );
			//testa para ver se o peao pode mover 
			if(getTabuleiro().positionExists(p) && IsThereOpponentPiece(p)) { //existe a posicao e tem uma peca do oponente ali
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			//teste do mov diagonal direita quando tiver pe�a adversaria
			p.setValues(position.getRow() + 1, position.getColumn() + 1 );
			//testa para ver se o peao pode mover 
			if(getTabuleiro().positionExists(p) && IsThereOpponentPiece(p)) { //existe a posicao e tem uma peca do oponente ali
				mat[p.getRow()][p.getColumn()] = true;
			}
		}
		
		return mat;
	}
	
	@Override
	public String toString() {
		return "p";
	}
	

}
