package chess.pecas;

import tabuleiro.Tabuleiro;
import tabuleiro.Posicao;
import chess.PecaXadrez;
import chess.Color;

public class Peao extends PecaXadrez {

	public Peao(Tabuleiro Tabuleiro, Color color) {
		super(Tabuleiro, color);
		
	}

	@Override
	public boolean[][] MovPossivel() {
		
		boolean [][] mat = new boolean[getTabuleiro().getlinhas()][getTabuleiro().getcolunas()];
		
		Posicao p = new Posicao(0,0);
		
		//regraDoPeaoBranco = se move linha para cima ou seja na mtriz � -1 
		if(getColor() == Color.WHITE) {
			 //1 posicao para frente
			p.setValores(posicao.getLinha()- 1, posicao.getColuna());
			//testa para ver se o peao pode mover 
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)) { //existe a posicao e esta vazia
				mat[p.getLinha()][p.getColuna()] = true;
			}
			//peao se movendo 2 linhas para frente
			p.setValores(posicao.getLinha()- 2, posicao.getColuna()); //2 posicao acima
			Posicao p2 = new Posicao(posicao.getLinha()- 1, posicao.getColuna());
			
			
			//testa para ver se o peao pode mover 2 casas pra frente 
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().temPeca(p2) && getMoveCount() == 0) { //existe a posicao e esta vazia
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			//teste do mov diagonal esquerda quando tiver pe�a adversaria
			p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1 );
			//testa para ver se o peao pode mover 
			if(getTabuleiro().posicaoExiste(p) && IsThereOpponentPiece(p)) { //existe a posicao e tem uma peca do oponente ali
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			//teste do mov diagonal direita quando tiver pe�a adversaria
			p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1 );
			//testa para ver se o peao pode mover 
			if(getTabuleiro().posicaoExiste(p) && IsThereOpponentPiece(p)) { //existe a posicao e tem uma peca do oponente ali
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
		}
		else { //se nao � branca � preta. OBS: o posicao ROW vai ser + 1 
			
			//1 posicao para baixo
			p.setValores(posicao.getLinha() + 1, posicao.getColuna());
			//testa para ver se o peao pode mover 
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)) { //existe a posicao e esta vazia
				mat[p.getLinha()][p.getColuna()] = true;
			}
			//peao se movendo 2 linhas para baixo
			p.setValores(posicao.getLinha() + 2, posicao.getColuna()); //2 posicao acima
			Posicao p2 = new Posicao(posicao.getLinha()- 1, posicao.getColuna());
			
			
			//testa para ver se o peao pode mover 2 casas parra baixo
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().temPeca(p2) && getMoveCount() == 0) { //existe a posicao e esta vazia
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			//teste do mov diagonal esquerda quando tiver pe�a adversaria
			p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1 );
			//testa para ver se o peao pode mover 
			if(getTabuleiro().posicaoExiste(p) && IsThereOpponentPiece(p)) { //existe a posicao e tem uma peca do oponente ali
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			//teste do mov diagonal direita quando tiver pe�a adversaria
			p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1 );
			//testa para ver se o peao pode mover 
			if(getTabuleiro().posicaoExiste(p) && IsThereOpponentPiece(p)) { //existe a posicao e tem uma peca do oponente ali
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
