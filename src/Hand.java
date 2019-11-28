import java.util.LinkedList;
import java.util.Iterator;
import java.util.Collections;

public class Hand {
	
	LinkedList<Card> cards;
	
	public Hand() {
		
		cards = new LinkedList<Card>();
		
	}
	
	public Hand(Hand handToCopy) {
		
		cards = (LinkedList<Card>) handToCopy.cards.clone();
	}
	
	public boolean add(Card c) {
		
		return cards.add(c);
	}
		
	public void print() {
		
		Iterator<Card> it = cards.iterator();
		while (it.hasNext()) {
			
			System.out.print(it.next() + " ");
			
		}
		System.out.println();
	}
	
	public int length() {
		
		return cards.size();
	}
	
	public Iterator<Card> iterator() {
		
		return cards.iterator();
	}
	
	public void sort() {
		
		Collections.sort(cards);
	}
	
}