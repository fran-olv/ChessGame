package chess.pieces;

import tabuleiro.Tabuleiro;
import tabuleiro.Position;
import chess.PecaXadrez;
import chess.Color;

public class Bispo extends PecaXadrez{

	public Bispo(Tabuleiro Tabuleiro, Color color) {
		super(Tabuleiro, color);
	}

	@Override
	public String toString() {
		return "B";
	}

	@Override
	public boolean[][] MovPossivel() {
		//Declacao da matriz
		boolean [][] mat = new boolean[getTabuleiro().getRows()][getTabuleiro().getColumns()];
		
		
		Position p = new Position(0,0);
		
		//Movimento para noroeste	
		p.setValues(position.getRow() - 1 , position.getColumn() - 1);
		while(getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)) { //marcando a todas as posicoes que pode se mover
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() -1,p.getColumn() - 1);
		}
		if(getTabuleiro().positionExists(p) && IsThereOpponentPiece(p)) { //marcando a posicao que tem uma opeca do oponente
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Movimento para nordeste		
		p.setValues(position.getRow() -1, position.getColumn() + 1);
		while(getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)) { //marcando a todas as posicoes que pode se mover
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() -1, p.getColumn() + 1);
		}
		if(getTabuleiro().positionExists(p) && IsThereOpponentPiece(p)) { //marcando a posicao que tem uma opeca do oponente
			mat[p.getRow()][p.getColumn()] = true;
		}
				
		//Movimento para sudeste		
		p.setValues(position.getRow(), position.getColumn() + 1);
		while(getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)) { //marcando a todas as posicoes que pode se mover
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() +1);
		}
		if(getTabuleiro().positionExists(p) && IsThereOpponentPiece(p)) { //marcando a posicao que tem uma opeca do oponente
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Movimento para sudoeste		
				p.setValues(position.getRow() + 1 , position.getColumn() );
				while(getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)) { //marcando a todas as posicoes que pode se mover
					mat[p.getRow()][p.getColumn()] = true;
					p.setValues(p.getRow() + 1, p.getColumn() -1);
				}
				if(getTabuleiro().positionExists(p) && IsThereOpponentPiece(p)) { //marcando a posicao que tem uma opeca do oponente
					mat[p.getRow()][p.getColumn()] = true;
				}
		return mat;
	}
}
