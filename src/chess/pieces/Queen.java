package chess.pieces;

import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;
import tabuleiro.Position;

public class Queen extends PecaXadrez{

	public Queen(Tabuleiro Tabuleiro, Cor cor) {
		super(Tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "r";
	}

	@Override
	public boolean[][] MovPossivel() {
		//Declacao da matriz
		boolean [][] mat = new boolean[getTabuleiro().getRows()][getTabuleiro().getColumns()];
		
		
		Position p = new Position(0,0);
		
		//Movimento para acima		
		p.setValues(position.getRow() - 1 , position.getColumn() );
		while(getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)) { //marcando a todas as posicoes que pode se mover
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() - 1);
		}
		if(getTabuleiro().positionExists(p) && IsThereOpponentPiece(p)) { //marcando a posicao que tem uma opeca do oponente
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Movimento para esquerda		
		p.setValues(position.getRow(), position.getColumn() - 1);
		while(getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)) { //marcando a todas as posicoes que pode se mover
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() - 1);
		}
		if(getTabuleiro().positionExists(p) && IsThereOpponentPiece(p)) { //marcando a posicao que tem uma opeca do oponente
			mat[p.getRow()][p.getColumn()] = true;
		}
				
		//Movimento para direita		
		p.setValues(position.getRow(), position.getColumn() + 1);
		while(getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)) { //marcando a todas as posicoes que pode se mover
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() + 1);
		}
		if(getTabuleiro().positionExists(p) && IsThereOpponentPiece(p)) { //marcando a posicao que tem uma opeca do oponente
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Movimento para baixo		
		p.setValues(position.getRow() + 1 , position.getColumn() );
		while(getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)) { //marcando a todas as posicoes que pode se mover
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() + 1);
		}
		if(getTabuleiro().positionExists(p) && IsThereOpponentPiece(p)) { //marcando a posicao que tem uma opeca do oponente
			mat[p.getRow()][p.getColumn()] = true;
		}
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
