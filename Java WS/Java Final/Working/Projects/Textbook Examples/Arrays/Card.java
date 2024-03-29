public class Card {
	
	
	private String face; // face of card ("Ace", "Deuce", ...)
	private String suit; // suit of card ("Hearts", "Diamonds", ...)

	// two-argument constructor initializes card's face and suit
	public Card(String cardFace, String cardSuit) {
		face = cardFace; // initialize face of card
		suit = cardSuit; // initialize suit of card
	} // end two-argument Card constructor
		// end class Card
		// return String representation of Card

	public String toString() {
		return face + " of " + suit;
	} // end method toString

}