package blackjack_verkort_tentamen_rob_biemans;

import java.util.ArrayList;
import java.util.Collections;

public class Cards {
	
	// Declarations
	private ArrayList<ArrayList<Card>> cards = new ArrayList<ArrayList<Card>>();
	
	// Constructor
	public Cards() {
        Card.Value[] values = Card.Value.values();
        Card.Type[] types = Card.Type.values();
        
        // Create 6 seperate decks
        for (int i = 0; i < 6; i++) {
        	ArrayList<Card> deck = new ArrayList<Card>();
        	
	        // for every card value (2) 
	        for (Card.Value value : values) {
	        	// for every card type (spades)
	            for (Card.Type type : types) {
	            	// new init of Card class
	                deck.add(new Card(value, type)); 
	            }
	        }
	        
	        cards.add(deck);
        }
        mergeDecks();
	}
	
	public void mergeDecks() {
		ArrayList<Card> temp = new ArrayList<Card>();
		
		for (int i = 0; i < cards.size(); i++){
			temp.addAll(cards.get(i));
		}
		cards.clear();
		cards.add(temp);
	}
	
	public int getDeckSize(int value) {
		return cards.get(value).size();
	}
	
	// from card from the deck
	public Card getCard() {

		// shuffle the arraylist
		Collections.shuffle(cards.get(0));

		if (cards.get(0).size() <= 156){
	        Card.Value[] values = Card.Value.values();
	        Card.Type[] types = Card.Type.values();
	        
	        // Create 6 seperate decks
	        for (int i = 0; i < 6; i++) {
	        	ArrayList<Card> deck = new ArrayList<Card>();
	        	
		        // for every card value (2) 
		        for (Card.Value value : values) {
		        	// for every card type (spades)
		            for (Card.Type type : types) {
		            	// new init of Card class
		                deck.add(new Card(value, type)); 
		            }
		        }
		        
		        cards.add(deck);
	        }
	        mergeDecks();
		}
		
		Card card = cards.get(0).get(0);
		cards.get(0).remove(0);
		cards.trimToSize();
		return card;
	}
	
}
