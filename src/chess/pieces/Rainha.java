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
		p.setValores(posicao.getLinha() - 1 , posicao.getColuna() );
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)) { //marcando a todas as posicoes que pode se mover
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if(getTabuleiro().posicaoExiste(p) && IsThereOpponentPiece(p)) { //marcando a posicao que tem uma opeca do oponente
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//Movimento para esquerda		
		p.setValores(posicao.getLinha(), posicao.getColuna() - 1);
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)) { //marcando a todas as posicoes que pode se mover
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if(getTabuleiro().posicaoExiste(p) && IsThereOpponentPiece(p)) { //marcando a posicao que tem uma opeca do oponente
			mat[p.getLinha()][p.getColuna()] = true;
		}
				
		//Movimento para direita		
		p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)) { //marcando a todas as posicoes que pode se mover
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if(getTabuleiro().posicaoExiste(p) && IsThereOpponentPiece(p)) { //marcando a posicao que tem uma opeca do oponente
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//Movimento para baixo		
		p.setValores(posicao.getLinha() + 1 , posicao.getColuna() );
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)) { //marcando a todas as posicoes que pode se mover
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if(getTabuleiro().posicaoExiste(p) && IsThereOpponentPiece(p)) { //marcando a posicao que tem uma opeca do oponente
			mat[p.getLinha()][p.getColuna()] = true;
		}
		//Movimento para noroeste	
		p.setValores(posicao.getLinha() - 1 , posicao.getColuna() - 1);
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)) { //marcando a todas as posicoes que pode se mover
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() -1,p.getColuna() - 1);
		}
		if(getTabuleiro().posicaoExiste(p) && IsThereOpponentPiece(p)) { //marcando a posicao que tem uma opeca do oponente
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//Movimento para nordeste		
		p.setValores(posicao.getLinha() -1, posicao.getColuna() + 1);
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)) { //marcando a todas as posicoes que pode se mover
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() -1, p.getColuna() + 1);
		}
		if(getTabuleiro().posicaoExiste(p) && IsThereOpponentPiece(p)) { //marcando a posicao que tem uma opeca do oponente
			mat[p.getLinha()][p.getColuna()] = true;
		}
				
		//Movimento para sudeste		
		p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)) { //marcando a todas as posicoes que pode se mover
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna() +1);
		}
		if(getTabuleiro().posicaoExiste(p) && IsThereOpponentPiece(p)) { //marcando a posicao que tem uma opeca do oponente
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//Movimento para sudoeste		
		p.setValores(posicao.getLinha() + 1 , posicao.getColuna() );
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)) { //marcando a todas as posicoes que pode se mover
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna() -1);
		}
		if(getTabuleiro().posicaoExiste(p) && IsThereOpponentPiece(p)) { //marcando a posicao que tem uma opeca do oponente
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		return mat;
	}
}
