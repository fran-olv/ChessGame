package chess.pieces;

import TabuleiroXadrez.Tabuleiro;
import TabuleiroXadrez.Posicao;
import xadrez.PecaXadrez;
import xadrez.Cor;

public class Bispo extends PecaXadrez{

	public Bispo(Tabuleiro Tabuleiro, Cor cor) {
		super(Tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "B";
	}

	@Override
	public boolean[][] MovPossivel() {
		//Declacao da matriz
		boolean [][] mat = new boolean[getTabuleiro().getlinhas()][getTabuleiro().getcolunas()];
		
		
		Posicao p = new Posicao(0,0);
		
		//Movimento para noroeste	
		p.setValores(posicao.getLinha() - 1 , posicao.getColuna() - 1);
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)) { //marcando a todas as posicoes que pode se mover
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() -1,p.getColuna() - 1);
		}
		if(getTabuleiro().posicaoExiste(p) && temPecaOponente(p)) { //marcando a posicao que tem uma opeca do oponente
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//Movimento para nordeste		
		p.setValores(posicao.getLinha() -1, posicao.getColuna() + 1);
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)) { //marcando a todas as posicoes que pode se mover
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() -1, p.getColuna() + 1);
		}
		if(getTabuleiro().posicaoExiste(p) && temPecaOponente(p)) { //marcando a posicao que tem uma opeca do oponente
			mat[p.getLinha()][p.getColuna()] = true;
		}
				
		//Movimento para sudeste		
		p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)) { //marcando a todas as posicoes que pode se mover
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna() +1);
		}
		if(getTabuleiro().posicaoExiste(p) && temPecaOponente(p)) { //marcando a posicao que tem uma opeca do oponente
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//Movimento para sudoeste		
				p.setValores(posicao.getLinha() + 1 , posicao.getColuna() );
				while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)) { //marcando a todas as posicoes que pode se mover
					mat[p.getLinha()][p.getColuna()] = true;
					p.setValores(p.getLinha() + 1, p.getColuna() -1);
				}
				if(getTabuleiro().posicaoExiste(p) && temPecaOponente(p)) { //marcando a posicao que tem uma opeca do oponente
					mat[p.getLinha()][p.getColuna()] = true;
				}
		return mat;
	}
}
