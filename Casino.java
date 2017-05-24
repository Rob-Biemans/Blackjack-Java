package blackjack_verkort_tentamen_rob_biemans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Casino {
	
	// Declarations
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private Scanner sc = new Scanner(System.in);
	private String input;
	private boolean finished;
	private boolean print;
	private int hand;
	private Blackjack b;
	private Player p;
	private Dealer d;
	
	// Constructor
	public Casino () {
		d = new Dealer();
		p = new Player();
	}
	
	public void play() {
		b = new Blackjack(p,d);
		setPrint(true);
		((Player)p).createHands();
		((Dealer)d).createHands();
		setFinished(false);
		
		while(!finished) {
			if(finished == false) {
				checkInput();
				if (p.getBet() > p.getMoney()) {
					finished = true;
				}
			}
		}
		askPlayAgain();
	}
	
	// Validate input with each hand
	public String checkInput() {
		int inactiveHands = 0;
		if (print == true) {
			b.printGame();	
		}
		for (int i = 0; i < ((Player)p).getHanden().size(); i++) {
			if (((Player)p).getHandenInfo().get(i).get(0).equals(" ") || ((Player)p).getHandenInfo().get(i).get(0).equals("Dubbel")) {
				System.out.print(((Player)p).getName()+", wat wil je doen met hand "+(i+1)+" ? ");
				try {
					input = br.readLine().toLowerCase();
					input = input.trim();
					switch(input) {
						case "p":
							b.passHand(i);
							System.out.println();
							break;
						case "d":
							b.addCard(i);
							break;
						case "2":
							if (((Player)p).getBet() > (((Player)p).getMoney()/getHand()/2)){
								System.out.println("Je hebt te weinig geld om te doublen!");
								break;
							} else {
								b.doubleBet(i);
								break;
							}
						case "s": 
							System.out.println("Tot de volgende keer " + p.getName() + "!");
							System.exit(0);
							break;
						default:
							System.out.println();
							System.out.println("Je kan alleen {P}, {D}, {2} of {S} invoeren!");
							setPrint(false);
							checkInput();
							break;
					}
				} catch (IOException e) {
					System.out.println("ERROR 5: " + e);
				}
			} else {
				inactiveHands+=1;
			}
		}
		
		// If all hands are inactive, its the dealers turn
		if (inactiveHands == ((Player)p).getHanden().size()) {
			setPrint(false);
			askDealerToContinue();
		}
		// if all hands are inactive incl dealer is finished
		if (inactiveHands == ((Player)p).getHanden().size() && d.getFinished() == true) {
			checkWonBets();
		}
		return input;
	}
	
	public void checkWonBets() {
		for (int i = 0; i < ((Player)p).getHanden().size(); i++) {
			int dealerpoints = d.getPoints();
			int pHandPoints = ((Player)p).calcPointsOfHand(i);
			try {
				// draw
				if (((Player)p).getHandenInfo().get(i).get(0) == "Blackjack" && dealerpoints == 21 && ((Dealer)d).getHanden().get(0).size() == 2) {
					((Player) p).setMoney(((Player) p).getMoney()-Integer.parseInt(((Player)p).getHandenInfo().get(i).get(1)));
					System.out.println("1 "+((Player)p).getName() +", je wint hand "+ (i+1) +" met inzet van "+((Player)p).getHandenInfo().get(i).get(1));
				} 
				// x2.5 bet
				else if (((Player)p).getHandenInfo().get(i).get(0) == "Blackjack" && dealerpoints == 21 && ((Dealer)d).getHanden().get(0).size() != 2) {
					((Player) p).setMoney(((Player) p).getMoney()+(Integer.parseInt(((Player)p).getHandenInfo().get(i).get(1))*2.5));
					System.out.println("2 "+((Player)p).getName() +", je wint hand "+ (i+1) +" met inzet van "+(Integer.parseInt(((Player)p).getHandenInfo().get(i).get(1))*2.5));
				}
				// -bet | player hand is death 
				else if (((Player)p).getHandenInfo().get(i).get(0) == "Dood" || pHandPoints > 21){
					((Player) p).setMoney(((Player) p).getMoney()-Integer.parseInt(((Player)p).getHandenInfo().get(i).get(1)));
					System.out.println("3 "+((Player)p).getName() +", je verliest hand "+ (i+1) +" met inzet van "+((Player)p).getHandenInfo().get(i).get(1));
				}
				// x2 bet | dealer hand is death OR player has more points than dealer
				else if (dealerpoints > 21 || pHandPoints > dealerpoints) {
					((Player) p).setMoney(((Player) p).getMoney()+(Integer.parseInt(((Player)p).getHandenInfo().get(i).get(1))*2));
					System.out.println("4 "+((Player)p).getName() +", je wint hand "+ (i+1) +" met inzet van "+(Integer.parseInt(((Player)p).getHandenInfo().get(i).get(1))*2));
				}
				// -bet
				else if (pHandPoints < dealerpoints && dealerpoints <= 21) {
					((Player) p).setMoney(((Player) p).getMoney()-Integer.parseInt(((Player)p).getHandenInfo().get(i).get(1)));
					System.out.println("5 "+((Player)p).getName() +", je verliest hand "+ (i+1) +" met inzet van "+((Player)p).getHandenInfo().get(i).get(1));
				}
				// -bet Dealer got blackjack and player don't.
				else if (dealerpoints == 21 && ((Dealer)d).getHanden().get(0).size() == 2 && ((Player)p).getHandenInfo().get(i).get(0) != "Blackjack") {
					((Player) p).setMoney(((Player) p).getMoney()-Integer.parseInt(((Player)p).getHandenInfo().get(i).get(1)));
					System.out.println("6 "+((Player)p).getName() +", je verliest hand "+ (i+1) +" met inzet van "+((Player)p).getHandenInfo().get(i).get(1));
				} 
				// draw
				else if(pHandPoints == dealerpoints && dealerpoints <= 21) {
					((Player) p).setMoney(((Player) p).getMoney()-Integer.parseInt(((Player)p).getHandenInfo().get(i).get(1)));
					System.out.println("7 "+((Player)p).getName() +", je wint hand "+ (i+1) +" met inzet van "+((Player)p).getHandenInfo().get(i).get(1));
				} 
			} catch (Exception e) {
				System.out.println("ERROR 6: "+ e);
			}	
			dealerpoints = 0;
		}	
		
		System.out.println();
		System.out.println("Je kapitaal is nu: "+((Player) p).getMoney() + " euro.");
		if (((Player) p).getMoney() <= 0) {
			askPlayAgain();
		} else {
			askToContinue();
		}	
	}
	
	public void askToContinue() {
		System.out.println("Wil je nog een keer spelen (j/n)?");
		String input = sc.nextLine().toLowerCase();
		if(input.equals("j")) {
			p.clearHands();
			d.clearHands();
			((Player)p).resetHandInfo();
			((Dealer)d).resetHandInfo();
			
			setHand();
		} else if (input.equals("n")) {
			System.out.println("Tot de volgende keer!");
			System.exit(0);
		} else {
			System.out.println("Dat begrijp ik niet.");
			askToContinue();
		}
	}
	
	public void askDealerToContinue() {
		System.out.print("Druk op ENTER om de deler te laten spelen...");
		try {
			sc.nextLine();
			b.dealerTurn();
		} catch (Exception e) {
			System.out.println("ERROR 0: " + e);
		}
	}

	// Check if player wants to play again
	public void askPlayAgain() {
		if (((Player) p).getMoney() <= -1){
			System.out.println("Je bent geëindigd op " + ((Player) p).getMoney() + " euro. Je kan niet meer verder spelen en je moet de schuld terug betalen door in de keuken te gaan werken.");
		} else {
			System.out.println("Helaas, je hebt niet genoeg geld meer om door te spelen. Je bent geëindigd op " + ((Player) p).getMoney() + " euro");
		}
		System.out.println("Wil je opnieuw beginnen spelen (j/n)?");
		String input = sc.nextLine().toLowerCase();
		if(input.equals("j")) {
			((Player)p).setMoney(1000);
			System.out.println("..START NIEUW SPEL..");
			resetFinished();
			askName();
		} else if (input.equals("n")) {
			System.out.println("Tot de volgende keer!");
			System.exit(0);
		} else {
			System.out.println("Dat begrijp ik niet.");
			askPlayAgain();
		}
	}
	
	// Show possible commands
	public void showCommands() {
		System.out.println();
		System.out.println("Commando's");
		System.out.println("-------------");
		System.out.println("p = passen");
		System.out.println("d = draaien");
		System.out.println("2 = inzet verdubbelen");
		System.out.println("s = stoppen met spelen");
		System.out.println();
	}
	
	// Asks for name
	public void askName() {
		System.out.print("Wat is je naam? ");
		try {
			input = br.readLine();
			input = input.trim();
			validateLetters(input);
		} catch (IOException e) {
			System.out.println("ERROR 1: " + e);
		}
		
		if (validateLetters(input) != false  && input.length() > 1) {
			p.setName(input);
			System.out.println("Welkom " + input +". Je startkapitaal is 1000 euro");
			setHand();
		} else {
			System.out.println("ERROR 2: Een naam bestaat alleen uit letters en/of spaties!");
			askName();
		}
	}
	
	// Validate possible name
	private boolean validateLetters(String txt) {
		String regx = "^[\\p{L} ']+$";
	    Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(txt);
	    return matcher.find();
	}
	
	// Mutators
	public void setHand() {
		System.out.print("Met hoeveel handen wil je spelen (1-5)? ");
		try {
			hand = Integer.parseInt(br.readLine());
		} catch(Exception e) {
			System.out.println("Dat aantal ligt niet tussen 1 en 5.");
			setHand();
		}
		
		if (hand >= 1 && hand <= 5) {
			((Player)p).setHand(hand);
			setBet();
		} else {
			setHand();
		}
	}
	
	public void setBet() {
		int bet = 0;
		System.out.print("Met welke inzet wil je spelen (1-"+((Player)p).getMoney()/getHand()+")? ");
		try {
			bet = Integer.parseInt(br.readLine());
			((Player)p).setBet(1000);
		} catch(Exception e) {
			System.out.println("Dat aantal ligt niet tussen 1 en "+((Player)p).getMoney()/getHand());
			setBet();
		}
		if (bet > ((Player)p).getMoney()/getHand()) {
			System.out.println("Inzet mag niet groter zijn dan "+ ((Player)p).getMoney()/getHand());
			setBet();
		} else if (bet >= 1 && bet <= ((Player)p).getMoney()/getHand()) {
			((Player)p).setBet(bet);
			play();
		} else {
			setBet();
		}
	}
	
	public void resetFinished() {
		p.clearHands();
		d.clearHands();
		((Player)p).resetHandInfo();
		((Dealer)d).resetHandInfo();
		setFinished(false);
	}
	
	// Accessors
	public int getHand() {
		return hand;
	}
	
	public void setPrint(boolean value) {
		print = value;
	}
	
	public boolean getPrint() {
		return print;
	}
	
	public boolean getFinished() {
		return finished;
	}
	
	public void setFinished(boolean value) {
		finished = value;
	}
}
