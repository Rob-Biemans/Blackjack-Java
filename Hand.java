package blackjack_verkort_tentamen_rob_biemans;

import java.util.ArrayList;
import java.util.Scanner;

public class Hand {
	
	// Declarations
	private ArrayList<ArrayList<Card>> hands; // array with amount of hands (with cards)
	private ArrayList<ArrayList<String>> handsInfo = new ArrayList<ArrayList<String>>(); // array with bet size and status of the particular hand
	private String status = " "; // dubbel, gepast, dood, blackjack
	private Cards c;
	private Participant p;
	
	public Hand(Participant p) {
		this.p = p;
		c = new Cards();
	} 
	
	public void CreatePlayerHandwithCards (int value) {
		hands = new ArrayList<ArrayList<Card>>();
		handsInfo.clear();
		String st = " ";
		for (int i = 0; i < value; i++) {
			ArrayList<Card> deck = new ArrayList<Card>();
			ArrayList<String> values = new ArrayList<String>();
			
			for (int a = 0; a < 2; a++) {
				deck.add(c.getCard());	
			}	
			 
			if (deck.get(0).getValue().toString() == "AAS" || deck.get(1).getValue().toString() == "AAS") {
				if (deck.get(0).getValue().toString() == "TIEN" || deck.get(1).getValue().toString() == "TIEN") {
					st = "Blackjack";
				} 
				else if (deck.get(0).getValue().toString() == "BOER" || deck.get(1).getValue().toString() == "BOER") {
					st = "Blackjack";
				} 
				else if (deck.get(0).getValue().toString() == "VROUW" || deck.get(1).getValue().toString() == "VROUW") {
					st = "Blackjack";
				} 
				else if (deck.get(0).getValue().toString() == "KONING" || deck.get(1).getValue().toString() == "KONING") {
					st = "Blackjack";
				}
			}
			
			String number = Integer.toString(((Player)p).getBet());
			values.add(st);
			st = " ";
			values.add(number);
			handsInfo.add(values);
			hands.add(deck);
		}
		
		((Player)p).setHandenInfo(handsInfo);
		p.setHanden(hands);
	}
	
	public void givePlayerCard(int value) {	
		hands = new ArrayList<ArrayList<Card>>();
		ArrayList<Card> deck = new ArrayList<Card>();
			
		deck.add(c.getCard());	
		
		p.addCardToHand(value,deck);

		printPlayerHandSituation(value);
	}
	
	public void printPlayerHandSituation(int value) {
		System.out.print("Nieuwe situatie: ");
		for (int a = 0; a < ((Player)p).getHanden().get(value).size(); a++) {
			System.out.print(" "+((Player)p).getHanden().get(value).get(a).getType().getValue()+((Player)p).getHanden().get(value).get(a).getValue().getValue());
		}
		System.out.println();
	}
	
	/* DEALER CODE BELOW */
	/*********************/
	public void CreateDealerHandwithCards() {
		hands = new ArrayList<ArrayList<Card>>();
		ArrayList<Card> deck = new ArrayList<Card>();
		ArrayList<String> values = new ArrayList<String>();
		deck.add(c.getCard());			
		
		values.add("none");
		handsInfo.add(values);
		hands.add(deck);
		((Dealer)p).setHandenInfo(handsInfo);
		((Dealer)p).setHanden(hands);
	}
	
	public void printDealerHandSituation() {
		int points = 0;
		System.out.print("Deler: ");
		for (int a = 0; a < ((Dealer)p).getHanden().get(0).size(); a++) {
			System.out.print(" "+((Dealer)p).getHanden().get(0).get(a).getType().getValue()+((Dealer)p).getHanden().get(0).get(a).getValue().getValue());
		}
		
		for (int a = 0; a < ((Dealer)p).getHanden().get(0).size(); a++) {
			switch (((Dealer)p).getHanden().get(0).get(a).getValue().getValue()){
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
					points += Integer.parseInt(((Dealer)p).getHanden().get(0).get(a).getValue().getValue());
					break;
			}
		}
		
		if (((Dealer)p).getHanden().get(0).size() == 2) {
			if (points == 21) {
				System.out.print("\tBlackjack");
			}
		}
		
		System.out.println();
		if (points >= 17) {
			if (points > 21) {
				System.out.println("De deler is dood");
				((Dealer)p).updateHandDeath();
			} else {
				System.out.println("Deler heeft gepast en is geëindigd met "+points+" punten");
			}
			((Dealer)p).setPoints(points);
			((Dealer)p).setFinished(true);
		} else if (points < 17) {
			System.out.print("Druk op ENTER om de deler te laten spelen...");
			try {
				Scanner sc = new Scanner(System.in);
				sc.nextLine();
				giveDealerCard();
			} catch (Exception e) {
				System.out.println("ERROR 7: " + e);
			}
		}
	}
	
	public void giveDealerCard() {	
		hands = new ArrayList<ArrayList<Card>>();
		ArrayList<Card> deck = new ArrayList<Card>();
			
		deck.add(c.getCard());	
		
		p.addCardToDealerHand(deck);
		printDealerHandSituation();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
