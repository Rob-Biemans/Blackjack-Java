package blackjack_verkort_tentamen_rob_biemans;

import java.util.ArrayList;

public class Player extends Participant {
	
	// Declarations
	private double money = 1000;
	private int hand;
	private int bet; // bet amount
	private Hand h = new Hand(this);
	private ArrayList<ArrayList<String>> handsInfo = new ArrayList<ArrayList<String>>();
	
	public Player() {
		
	}
	
	public void setHand(int value) {
		this.hand = value;
	}
	
	// get amount of hands from user input
	public int getHand() {
		return hand;
	}
	
	public void setMoney(double d) {
		this.money = d;
	}
	
	public double getMoney() {
		return money;
	}
	
	public void resetBet() {
		this.bet = getBet();
	}
	
	public void setBet(int value) {
		this.bet = value;
	}
	
	public int getBet() {
		return bet;
	}
	
	// Create hands (in Hand)
	public void createHands() {
		h.CreatePlayerHandwithCards(getHand());
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
	
	
	public void passHand(int value) {
		ArrayList<String> status = new ArrayList<String>();
		status.add("Gepast");					  // status
		status.add(handsInfo.get(value).get(1));  // bets
		status.add(Integer.toString(calcPointsOfHand(value))); // points
		this.handsInfo.set(value, status);
	}
	
	
	public void updateHandBet(int value) {
		h.givePlayerCard(value);
		int number = Integer.parseInt(handsInfo.get(value).get(1));
		number = number*2;
		ArrayList<String> status = new ArrayList<String>();
		int score = calcPointsOfHand(value);
		if(score > 21) {// status
			status.add("Dood");
		} else if (score < 21) {
			status.add("Dubbel");
		} else {
			status.add("Dubbel");
		}
		status.add(Integer.toString(number)); // bet
		status.add(Integer.toString(score)); // points
		this.handsInfo.set(value, status);
	}
	
	public void turnCard(int value) {
		h.givePlayerCard(value);
		ArrayList<String> status = new ArrayList<String>();
		int score = calcPointsOfHand(value);
		if(score > 21) { // status
			status.add("Dood");
		} else {
			status.add(" ");
		}
		status.add(getHandenInfo().get(value).get(1)); // bet
		status.add(Integer.toString(score)); // points
		this.handsInfo.set(value, status);
	}
	
	public int calcPointsOfHand(int value) {
		int points = 0;
		for (int a = 0; a < getHanden().get(value).size(); a++) {
			switch (getHanden().get(value).get(a).getValue().getValue()) {
				case "B":
					points += 10;
					break;
				case "V":
					points += 10;
					break;
				case "K":
					points += 10;
					break;
				case "A":
					if (points <= 10) {
						points += 11;
					} else {
						points += 1;
					}
					break;
				default:
					points += Integer.parseInt(getHanden().get(value).get(a).getValue().getValue());
					break;
			}
		}
		return points;
	}
	
	public void resetHandInfo() {
		this.handsInfo.clear();
	}
	
}