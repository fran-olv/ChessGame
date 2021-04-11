package chess.pecas;

import tabuleiro.Tabuleiro;
import tabuleiro.Posicao;
import chess.PecaXadrez;
import chess.Color;

public class Torre extends PecaXadrez{

	public Torre(Tabuleiro Tabuleiro, Color color) {
		super(Tabuleiro, color);
	}

	@Override
	public String toString() {
		return "T";
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
		return mat;
	}
}
