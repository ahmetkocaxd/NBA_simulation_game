package player;

import java.util.Random;

public class PlayerPF extends Player {
	
	protected double ptsW = 0.2;
	protected double trbW = 0.4;
	protected double astW = 0.1;
	protected double blkW = 0.2;
	protected double stlW = 0.1;
	
	
	public PlayerPF(String playerName, double pts, double trb, double ast, double blk, double stl) {
		super(playerName, "PF", pts, trb, ast, blk, stl);
		super.setScore((int) Math.round(pts*ptsW + trb*trbW + ast*astW + blk*blkW + stl*stlW));
	}

	public double getPtsW() {
		return ptsW;
	}





	public double getTrbW() {
		return trbW;
	}





	public double getAstW() {
		return astW;
	}





	public double getBlkW() {
		return blkW;
	}





	public double getStlW() {
		return stlW;
	}
	
	public void calculateScore() {
		// TODO Auto-generated method stub
		
		Random random = new Random();
		
		int ptsValue = (int) (random.nextGaussian()*(pts/4) + pts);
		int trbValue = (int) (random.nextGaussian()*(trb/4) + trb);
		int astValue = (int) (random.nextGaussian()*(ast/4) + ast);
		int blkValue = (int) (random.nextGaussian()*(blk/4) + blk);
		int stlValue = (int) (random.nextGaussian()*(stl/4) + stl);
		super.setScore((int) Math.round(ptsValue * ptsW + trbValue * trbW + astValue * astW + blkValue * blkW + stlValue * stlW));
		
	}
	
	
	
	

}
