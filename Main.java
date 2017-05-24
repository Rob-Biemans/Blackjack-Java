package blackjack_verkort_tentamen_rob_biemans;

public class Main {

	public static void main(String[] args) {
		Casino c = new Casino();
		
		System.out.println("Welkom in het iCasino! We gaan fijn Blackjack spelen.");
		
		c.showCommands();
		c.askName();

	}
}
