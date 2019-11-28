import java.util.Iterator;

public class VideoPokerTest {
	
	
	public static void main(String argv[]) {
		
		Card[] myCards = new Card[52];
		
		for (int i=0; i<52; i++) {
			Card newCard = new Card(i);
			myCards[i] = newCard;
		}
		
		
		final int TRIALS = 12;
		
		int winnings = 0;
		for (int i=0; i<TRIALS; i++) {
		
			Deck myDeck = new Deck(myCards);
			myDeck.shuffle();
			myDeck.setUpHash();
			
			/* remove 5s 6h, 7d, 9c, As from deck */
			
			myDeck.removeFromHash(4);
			myDeck.removeFromHash(18);
			myDeck.removeFromHash(32);
			myDeck.removeFromHash(48);
			myDeck.removeFromHash(0);				
						
			NSUD myHand = new NSUD();
			
			for (int j=0; j<5; j++) {
			
				Card drawnCard = myDeck.pop();
				if (drawnCard != null)
					myHand.add(drawnCard);				
			}
			
			myHand.sort();
			winnings += myHand.evaluate();
		}
		

		System.out.println("Expected winnings from straight: " + (int) TRIALS/6);
		System.out.println("Amount won from tossing hand: " + winnings);
		
	}
	
}