package chess.pecas;

import tabuleiro.Tabuleiro;
import tabuleiro.Posicao;
import chess.PecaXadrez;
import chess.Color;

public class Rainha extends PecaXadrez{

	public Rainha(Tabuleiro Tabuleiro, Color color) {
		super(Tabuleiro, color);
	}

	@Override
	public String toString() {
		return "r";
	}

	@Override
	public boolean[][] MovPossivel() {
		//Declacao da matriz
		boolean [][] mat = new boolean[getTabuleiro().getlinhas()][getTabuleiro().getcolunas()];
		
		
		Posicao p = new Posicao(0,0);
		
		//Movimento para acima		
		p.setValues(posicao.getLinha() - 1 , posicao.getColumn() );
		while(getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)) { //marcando a todas as posicoes que pode se mover
			mat[p.getLinha()][p.getColumn()] = true;
			p.setRow(p.getLinha() - 1);
		}
		if(getTabuleiro().positionExists(p) && IsThereOpponentPiece(p)) { //marcando a posicao que tem uma opeca do oponente
			mat[p.getLinha()][p.getColumn()] = true;
		}
		
		//Movimento para esquerda		
		p.setValues(posicao.getLinha(), posicao.getColumn() - 1);
		while(getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)) { //marcando a todas as posicoes que pode se mover
			mat[p.getLinha()][p.getColumn()] = true;
			p.setColumn(p.getColumn() - 1);
		}
		if(getTabuleiro().positionExists(p) && IsThereOpponentPiece(p)) { //marcando a posicao que tem uma opeca do oponente
			mat[p.getLinha()][p.getColumn()] = true;
		}
				
		//Movimento para direita		
		p.setValues(posicao.getLinha(), posicao.getColumn() + 1);
		while(getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)) { //marcando a todas as posicoes que pode se mover
			mat[p.getLinha()][p.getColumn()] = true;
			p.setColumn(p.getColumn() + 1);
		}
		if(getTabuleiro().positionExists(p) && IsThereOpponentPiece(p)) { //marcando a posicao que tem uma opeca do oponente
			mat[p.getLinha()][p.getColumn()] = true;
		}
		
		//Movimento para baixo		
		p.setValues(posicao.getLinha() + 1 , posicao.getColumn() );
		while(getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)) { //marcando a todas as posicoes que pode se mover
			mat[p.getLinha()][p.getColumn()] = true;
			p.setRow(p.getLinha() + 1);
		}
		if(getTabuleiro().positionExists(p) && IsThereOpponentPiece(p)) { //marcando a posicao que tem uma opeca do oponente
			mat[p.getLinha()][p.getColumn()] = true;
		}
		//Movimento para noroeste	
		p.setValues(posicao.getLinha() - 1 , posicao.getColumn() - 1);
		while(getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)) { //marcando a todas as posicoes que pode se mover
			mat[p.getLinha()][p.getColumn()] = true;
			p.setValues(p.getLinha() -1,p.getColumn() - 1);
		}
		if(getTabuleiro().positionExists(p) && IsThereOpponentPiece(p)) { //marcando a posicao que tem uma opeca do oponente
			mat[p.getLinha()][p.getColumn()] = true;
		}
		
		//Movimento para nordeste		
		p.setValues(posicao.getLinha() -1, posicao.getColumn() + 1);
		while(getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)) { //marcando a todas as posicoes que pode se mover
			mat[p.getLinha()][p.getColumn()] = true;
			p.setValues(p.getLinha() -1, p.getColumn() + 1);
		}
		if(getTabuleiro().positionExists(p) && IsThereOpponentPiece(p)) { //marcando a posicao que tem uma opeca do oponente
			mat[p.getLinha()][p.getColumn()] = true;
		}
				
		//Movimento para sudeste		
		p.setValues(posicao.getLinha(), posicao.getColumn() + 1);
		while(getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)) { //marcando a todas as posicoes que pode se mover
			mat[p.getLinha()][p.getColumn()] = true;
			p.setValues(p.getLinha() + 1, p.getColumn() +1);
		}
		if(getTabuleiro().positionExists(p) && IsThereOpponentPiece(p)) { //marcando a posicao que tem uma opeca do oponente
			mat[p.getLinha()][p.getColumn()] = true;
		}
		
		//Movimento para sudoeste		
		p.setValues(posicao.getLinha() + 1 , posicao.getColumn() );
		while(getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)) { //marcando a todas as posicoes que pode se mover
			mat[p.getLinha()][p.getColumn()] = true;
			p.setValues(p.getLinha() + 1, p.getColumn() -1);
		}
		if(getTabuleiro().positionExists(p) && IsThereOpponentPiece(p)) { //marcando a posicao que tem uma opeca do oponente
			mat[p.getLinha()][p.getColumn()] = true;
		}
		
		return mat;
	}
}
