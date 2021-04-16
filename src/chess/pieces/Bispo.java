package chess.pieces;

import chess.PecaXadrez;
import chess.Cor;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public class Bispo extends PecaXadrez{

	public Bispo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "B";
	}

	@Override
	public boolean[][] movimentoPossivel() {
		//Declacao da matriz
		boolean [][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		
		Posicao p = new Posicao(0,0);
		
		//Movimento para noroeste	
		p.setValores(posicao.getLinha() - 1 , posicao.getColuna() - 1);
		while(getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) { //marcando a todas as posicoes que pode se mover
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() -1,p.getColuna() - 1);
		}
		if(getTabuleiro().existePosicao(p) && ExistePecaOponente(p)) { //marcando a posicao que tem uma opeca do oponente
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//Movimento para nordeste		
		p.setValores(posicao.getLinha() -1, posicao.getColuna() + 1);
		while(getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) { //marcando a todas as posicoes que pode se mover
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() -1, p.getColuna() + 1);
		}
		if(getTabuleiro().existePosicao(p) && ExistePecaOponente(p)) { //marcando a posicao que tem uma opeca do oponente
			mat[p.getLinha()][p.getColuna()] = true;
		}
				
		//Movimento para sudeste		
		p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
		while(getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) { //marcando a todas as posicoes que pode se mover
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna() + 1);
		}
		if(getTabuleiro().existePosicao(p) && ExistePecaOponente(p)) { //marcando a posicao que tem uma opeca do oponente
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//Movimento para sudoeste		
				p.setValores(posicao.getLinha() + 1 , posicao.getColuna() );
				while(getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) { //marcando a todas as posicoes que pode se mover
					mat[p.getLinha()][p.getColuna()] = true;
					p.setValores(p.getLinha() + 1, p.getColuna() -1);
				}
				if(getTabuleiro().existePosicao(p) && ExistePecaOponente(p)) { //marcando a posicao que tem uma opeca do oponente
					mat[p.getLinha()][p.getColuna()] = true;
				}
		return mat;
	}
}
