package blackjack_verkort_tentamen_rob_biemans;

public class Card {
	
	// Declarations
	private Value value;
	private Type type;
	
	// Constructor
	public Card(Value value, Type type) {
		this.value = value;
		this.type = type;
	}

    public Value getValue() {
        return value;
    }

    public Type getType() {
        return type;
    }
	
    // predefined constants for card {Type}
    public enum Type {

        SCHOPPEN("\u2660"), HARTEN("\u2764"), RUITEN("\u2666"), KLAVEREN("\u2663");

        private String type;

        private Type(final String type) {
            this.type = type;
        }

        public String getValue() {
            return type;
        }
    }   
    
    // predefined constants for card {Value}
    public enum Value {

        TWEE("2"),
        DRIE("3"),
        VIER("4"),
        VIJF("5"),
        ZES("6"),
        ZEVEN("7"),
        ACHT("8"),
        NEGEN("9"),
        TIEN("10"),
        BOER("B"), 		//11
        VROUW("V"), 	//12
        KONING("K"),	//13
        AAS("A");		//14

        private String value;

        private Value(final String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
	
}
