package chess.pieces;

import chess.PecaXadrez;
import chess.Cor;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public class Piao extends PecaXadrez {

	public Piao(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		
	}

	@Override
	public boolean[][] movimentoPossivel() {
		
		boolean [][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0,0);
		
		//regraDoPeaoBranco = se move linha para cima ou seja na mtriz � -1 
		if(getCor() == Cor.BRANCO) {
			 //1 posicao para frente
			p.setValores(posicao.getLinha()- 1, posicao.getColuna());
			//testa para ver se o peao pode mover 
			if(getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) { //existe a posicao e esta vazia
				mat[p.getLinha()][p.getColuna()] = true;
			}
			//peao se movendo 2 linhas para frente
			p.setValores(posicao.getLinha()- 2, posicao.getColuna()); //2 posicao acima
			Posicao p2 = new Posicao(posicao.getLinha()- 1, posicao.getColuna());
			
			
			//testa para ver se o peao pode mover 2 casas pra frente 
			if(getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p) && getTabuleiro().existePosicao(p2) && !getTabuleiro().existePeca(p2) && getContadorMovimento() == 0) { //existe a posicao e esta vazia
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			//teste do mov diagonal esquerda quando tiver pe�a adversaria
			p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1 );
			//testa para ver se o peao pode mover 
			if(getTabuleiro().existePosicao(p) && ExistePecaOponente(p)) { //existe a posicao e tem uma peca do oponente ali
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			//teste do mov diagonal direita quando tiver pe�a adversaria
			p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1 );
			//testa para ver se o peao pode mover 
			if(getTabuleiro().existePosicao(p) && ExistePecaOponente(p)) { //existe a posicao e tem uma peca do oponente ali
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
		}
		else { //se nao � branca � preta. OBS: o posicao ROW vai ser + 1 
			
			//1 posicao para baixo
			p.setValores(posicao.getLinha() + 1, posicao.getColuna());
			//testa para ver se o peao pode mover 
			if(getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) { //existe a posicao e esta vazia
				mat[p.getLinha()][p.getColuna()] = true;
			}
			//peao se movendo 2 linhas para baixo
			p.setValores(posicao.getLinha() + 2, posicao.getColuna()); //2 posicao acima
			Posicao p2 = new Posicao(posicao.getLinha()+ 1, posicao.getColuna());
			
			
			//testa para ver se o peao pode mover 2 casas parra baixo
			if(getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p) && getTabuleiro().existePosicao(p2) && !getTabuleiro().existePeca(p2) && getContadorMovimento() == 0) { //existe a posicao e esta vazia
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			//teste do mov diagonal esquerda quando tiver pe�a adversaria
			p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1 );
			//testa para ver se o peao pode mover 
			if(getTabuleiro().existePosicao(p) && ExistePecaOponente(p)) { //existe a posicao e tem uma peca do oponente ali
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			//teste do mov diagonal direita quando tiver pe�a adversaria
			p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1 );
			//testa para ver se o peao pode mover 
			if(getTabuleiro().existePosicao(p) && ExistePecaOponente(p)) { //existe a posicao e tem uma peca do oponente ali
				mat[p.getLinha()][p.getColuna()] = true;
			}
		}
		
		return mat;
	}
	
	@Override
	public String toString() {
		return "p";
	}
	

}
