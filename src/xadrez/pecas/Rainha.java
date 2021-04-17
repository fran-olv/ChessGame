package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rainha extends PecaXadrez{

	public Rainha(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "R";
	}

	@Override
	public boolean[][] movimentoPossivel() {
		//Declacao da matriz
		boolean [][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		
		Posicao p = new Posicao(0,0);
		
		//Movimento para acima		
		p.setValores(posicao.getLinha() - 1 , posicao.getColuna() );
		while(getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) { //marcando a todas as posicoes que pode se mover
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if(getTabuleiro().existePosicao(p) && ExistePecaOponente(p)) { //marcando a posicao que tem uma opeca do oponente
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//Movimento para esquerda		
		p.setValores(posicao.getLinha(), posicao.getColuna() - 1);
		while(getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) { //marcando a todas as posicoes que pode se mover
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if(getTabuleiro().existePosicao(p) && ExistePecaOponente(p)) { //marcando a posicao que tem uma opeca do oponente
			mat[p.getLinha()][p.getColuna()] = true;
		}
				
		//Movimento para direita		
		p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
		while(getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) { //marcando a todas as posicoes que pode se mover
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if(getTabuleiro().existePosicao(p) && ExistePecaOponente(p)) { //marcando a posicao que tem uma opeca do oponente
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//Movimento para baixo		
		p.setValores(posicao.getLinha() + 1 , posicao.getColuna() );
		while(getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) { //marcando a todas as posicoes que pode se mover
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if(getTabuleiro().existePosicao(p) && ExistePecaOponente(p)) { //marcando a posicao que tem uma opeca do oponente
			mat[p.getLinha()][p.getColuna()] = true;
		}
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
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		while(getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) { //marcando a todas as posicoes que pode se mover
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna() +1);
		}
		if(getTabuleiro().existePosicao(p) && ExistePecaOponente(p)) { //marcando a posicao que tem uma opeca do oponente
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//Movimento para sudoeste		
		p.setValores(posicao.getLinha() + 1 , posicao.getColuna()-1);
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
