package blackjack_verkort_tentamen_rob_biemans;

public class Blackjack {
	
	// Declarations
	private Player p;
	private Dealer d;
	
	// Constructor
	public Blackjack (Player p, Dealer d) {
		this.p = p;
		this.d = d;
	}
	
	// Pass
	public void passHand(int value) {
		((Player)p).passHand(value);
	}
	
	// Turn card (new card into player hand)
	public void addCard(int value) {
		((Player)p).turnCard(value);
	}
	
	// Double bet of the hand and add card as well
	public void doubleBet(int value) {
		((Player)p).updateHandBet(value);
	}
	
	public void printGame() {
		System.out.println("***************************************************************");
			for (int i = 0; i < ((Dealer)d).getHanden().size(); i++){
				System.out.print("Deler:");
				for (int a = 0; a < ((Dealer)d).getHanden().get(i).size(); a++) {
					System.out.print(" "+((Dealer)d).getHanden().get(i).get(a).getType().getValue()+((Dealer)d).getHanden().get(i).get(a).getValue().getValue());
				}
			}
		System.out.println();
		System.out.println("---------------------------------------------------------------");	
			for (int i = 0; i < ((Player)p).getHanden().size(); i++){
				System.out.print(p.getName()+", "+"hand "+(i+1)+": ");
				for (int a = 0; a < ((Player)p).getHanden().get(i).size(); a++) {
					System.out.print(" "+((Player)p).getHanden().get(i).get(a).getType().getValue()+((Player)p).getHanden().get(i).get(a).getValue().getValue());
				}
				if (((Player)p).getHandenInfo().get(i).get(0) != null) {
					System.out.println("\t\tINZET = "+((Player)p).getHandenInfo().get(i).get(1) + " "+((Player)p).getHandenInfo().get(i).get(0));
				} else {
					System.out.println("\t\tINZET = "+((Player)p).getHandenInfo().get(i).get(1));
				}
			}
		System.out.println("***************************************************************");
	}
	
	public void dealerTurn() {
		((Dealer)d).giveDealerCard();
	}
	
}
