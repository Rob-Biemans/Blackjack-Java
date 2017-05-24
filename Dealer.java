package blackjack_verkort_tentamen_rob_biemans;

import java.util.ArrayList;

public class Dealer extends Participant {
	
	private Hand h = new Hand(this);
	private boolean finished;
	private int points;
	private ArrayList<ArrayList<String>> handsInfo = new ArrayList<ArrayList<String>>();
	
	public Dealer() {
		
	}
	
	// Create hands (in Hand)
	public void createHands() {
		h.CreateDealerHandwithCards();
	}
	
	public void giveDealerCard() {
		h.giveDealerCard();
		ArrayList<String> status = new ArrayList<String>();
		status.add("none");
		this.handsInfo.add(status);
		this.setHandenInfo(handsInfo);
	}
	
	public void setFinished(boolean value) {
		this.finished = value;
	}
	
	public boolean getFinished() {
		return finished;
	}
	
	public void setPoints(int value) {
		this.points = value;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void updateHandDeath() {
		ArrayList<String> status = new ArrayList<String>();
		status.add("Dood");
		this.handsInfo.set(0, status);
	}
	
	// return ArrayList with hands
	public ArrayList<ArrayList<String>> getHandenInfo() {
		return handsInfo;
	}
	
	// Get hands (from Hand)
	// Add the hands to the player his hands array
	public void setHandenInfo(ArrayList<ArrayList<String>> value) {
		this.handsInfo.addAll(value);
	}
	
	public void resetHandInfo() {
		this.handsInfo.clear();
	}
}
