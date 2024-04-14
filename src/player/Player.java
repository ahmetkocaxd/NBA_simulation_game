/**
 * 
 * Player class is an abstract class and superclass of other Player classes.
 * It contains every necessary fields of a Player and an abstract method called calculateScore()
 * to be defined in subclasses. Since every Player have different values of weights, I implemented this method
 * in subclasses.
 * 
 */

package player;


public abstract class Player {
	
	protected String playerName;
	protected String position;
	protected double pts, trb, ast, blk, stl;
	protected int score;
	protected int N = 5;
	protected double ptsW, trbW, astW, blkW, stlW;
	

	
	public Player(String playerName, String position, double pts, double trb, double ast, double blk, double stl) {
	
		this.playerName = playerName;
		this.position = position;
		this.pts = pts;
		this.trb = trb;
		this.ast = ast;
		this.blk = blk;
		this.stl = stl;
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
	
	
	public double getPts() {
		return pts;
	}




	public void setPts(double pts) {
		this.pts = pts;
	}




	public double getTrb() {
		return trb;
	}




	public void setTrb(double trb) {
		this.trb = trb;
	}




	public double getAst() {
		return ast;
	}




	public void setAst(double ast) {
		this.ast = ast;
	}




	public double getBlk() {
		return blk;
	}




	public void setBlk(double blk) {
		this.blk = blk;
	}




	public double getStl() {
		return stl;
	}




	public void setStl(double stl) {
		this.stl = stl;
	}




	public String getPlayerName() {
		return playerName;
	}




	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}




	public String getPosition() {
		return position;
	}




	public void setPosition(String position) {
		this.position = position;
	}




	public int getScore() {
		return score;
	}




	public void setScore(int score) {
		this.score = score;
	}




	abstract public void calculateScore();
	
}
