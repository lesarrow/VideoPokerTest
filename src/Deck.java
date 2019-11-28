import java.util.Random;
import java.util.LinkedHashSet;
import java.util.Iterator;

public class Deck {
	
	Card cards[];
	LinkedHashSet<Card> hashedCards;
	
	public Deck(Card input[]) {
		
		cards = input;
		hashedCards = null;
	}
	

	public void shuffle() {
		
		Random myRandom = new Random();
		
		for (int i=0; i<cards.length-1; i++) {
			
			int index = i + myRandom.nextInt(cards.length-i);
			
			Card tmpCard = cards[i];
			cards[i] = cards[index];
			cards[index] = tmpCard;			
		}		 			
		
	}
	
	
	public void setUpHash() {
		

		hashedCards = new LinkedHashSet<Card>(cards.length);
		for (int i=0; i<cards.length; i++) {						
			
			hashedCards.add(cards[i]);						
		}
		
	}
	
	
	public boolean removeFromHash(int value) {
		
		return hashedCards.remove(new Card(value));
		
	}			
	
	
	public Iterator<Card> iterator() {
		
		return hashedCards.iterator();
		
	}
	
	public Card pop() {
		
		Iterator<Card> it = iterator();
		if (it.hasNext()) {
			Card tmp = it.next();
			removeFromHash(tmp.masterValue);
			return tmp;
		}
		
		return null;
		
	}
	
}