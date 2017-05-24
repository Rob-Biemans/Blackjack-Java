package blackjack_verkort_tentamen_rob_biemans;

import java.util.ArrayList;

public class Participant {

	// Declarations
	private String name;
	private ArrayList<ArrayList<Card>> handArrayList = new ArrayList<ArrayList<Card>>();
	
	// Constructor
	public Participant() {
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String input) {
		this.name = input;
	}
	
	// return ArrayList with hands
	public ArrayList<ArrayList<Card>> getHanden() {
		return handArrayList;
	}
	
	// Get hands (from Hand)
	// Add the hands to the player his hands array
	public void setHanden(ArrayList<ArrayList<Card>> value) {
		this.handArrayList.addAll(value);
	}
	
	public void addCardToHand(int value, ArrayList<Card> deck) {
		this.handArrayList.get(value).addAll(deck);
	}
	
	public void addCardToDealerHand(ArrayList<Card> deck) {
		this.handArrayList.get(0).addAll(deck);
	}
	
	public void clearHands() {
		this.handArrayList.clear();
	}
	
}
