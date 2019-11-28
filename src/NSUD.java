import java.util.Iterator;

class NSUD extends Hand {
	
	public static final int[] PAYTABLE = {800, 200, 25, 16, 10, 4, 4, 3, 2, 1, 0};
	
	public enum Hands {NATURALROYAL, QUADDEUCES, WILDROYAL, FIVEKIND, STRAIGHTFLUSH, FOURKIND, FULLHOUSE, FLUSH, STRAIGHT, THREEKIND, NOTHING;}  
	
	public NSUD() {
		
	}
	
	public NSUD(Hand handToCopy) {
		
		super(handToCopy);
	}
	
	public int evaluate() {
		
		/* make a copy of the hand first */
		
		NSUD tmpHand = new NSUD(this);
		
		/* count deuces first */
		int deuceCount = 0;
		
		Iterator<Card> it = tmpHand.iterator();
		while (it.hasNext()) {
			
			Card tmpCard = it.next();
			if (tmpCard.isCardValue(2)) {
				deuceCount++;
				it.remove();
			}		
		}
		
		
		if (deuceCount == 4)
			return PAYTABLE[Hands.QUADDEUCES.ordinal()];
		
		/* look for 3, 4 and 5 of a kind, and two pair */
		
		int numberOfPairs = 0;
		int numberOfTrips = 0;
		int numberOfQuads = 0;
		
		for (int i=0; i<tmpHand.cards.size()-1;) {
			
			int secondPtr = i+1;
			int count = 0;
			while ((secondPtr < tmpHand.cards.size()) && (tmpHand.cards.get(i).compareTo(tmpHand.cards.get(secondPtr)) == 0)) {
				
				count++;
				secondPtr++;				
			}
			
			switch(count) {
			
				case 3: numberOfQuads++;
						break;
				case 2: numberOfTrips++;
						break;
				case 1: numberOfPairs++;
						break;						
			}
			
			i = secondPtr;			
			
		}
		
		
		// Quads found - check for five of a kind
	
		if (numberOfQuads != 0) {
			
			if (deuceCount == 1)
				return PAYTABLE[Hands.FIVEKIND.ordinal()];
			else return PAYTABLE[Hands.FOURKIND.ordinal()];
			
		}
		
		// Trips found - check for 4 or 5 of a kind
		
		if (numberOfTrips != 0) {
			
			if (deuceCount == 0)
				return PAYTABLE[Hands.THREEKIND.ordinal()];
			else if (deuceCount == 1)
				return PAYTABLE[Hands.FOURKIND.ordinal()];
			else if (deuceCount == 2)
				return PAYTABLE[Hands.FIVEKIND.ordinal()];
			
		}
		
		// 2 Pairs found - check for full house
		
		if (numberOfPairs == 2 && deuceCount == 1)
			return PAYTABLE[Hands.FULLHOUSE.ordinal()];
		
		// 1 pair found - check for 3, 4 or 5 of a kind
		
		if (numberOfPairs == 1 ) {
			
			if (deuceCount == 1)
				return PAYTABLE[Hands.THREEKIND.ordinal()];
			else if (deuceCount == 2)
				return PAYTABLE[Hands.FOURKIND.ordinal()];
			else if (deuceCount == 3)
				return PAYTABLE[Hands.FIVEKIND.ordinal()];			
		}
			
		
		/* Skip this section if any pairs were found */
		
		if (numberOfPairs == 0 && numberOfTrips == 0 && numberOfQuads == 0) {
		
			/* look for flush */
			
			it = tmpHand.iterator();
			
			int compSuit;
			boolean flush = true;
			if (it.hasNext()) {
				compSuit = it.next().getSuit();
				while (it.hasNext()) {
					
					if (!(it.next().getSuit() == compSuit)) {
						flush = false;
						break;
					}
					
				}						
			}
			
					
			/* Look for straight, straight flush, royal, and natroyal */
			
			boolean straight = true;
			int highCards = 0;
			int distance = deuceCount;
		
			it = tmpHand.iterator();
			Card lastCard = null;
			while (it.hasNext()) {
				
				if (lastCard == null) {
					lastCard = it.next();
					if (lastCard.isHighCard())
						highCards++;
					continue;
				}
				
				Card compCard = it.next();
				if (compCard.isHighCard())
					highCards++;
				distance -= compCard.getCardValue() - lastCard.getCardValue() - 1;								
				
				if (distance < 0) {
					straight = false;
					break;
				}
				
				lastCard = compCard;
				
			}
			
			if (straight && flush) {
				
				if (highCards == 5)
					return PAYTABLE[Hands.NATURALROYAL.ordinal()];
				
				if (highCards + deuceCount == 5)
					return PAYTABLE[Hands.WILDROYAL.ordinal()];
				
				return PAYTABLE[Hands.STRAIGHTFLUSH.ordinal()];
				
			}

			if (flush)
				return PAYTABLE[Hands.FLUSH.ordinal()];
			
			if (straight)
				return PAYTABLE[Hands.STRAIGHT.ordinal()];
			
		}
		
		/* Finally, if 2 deuces and nothing else it's trips. If 3 deuces and nothing else, it's quads */
		
		if (deuceCount == 3)
			return PAYTABLE[Hands.FOURKIND.ordinal()];
		
		if (deuceCount == 2)
			return PAYTABLE[Hands.THREEKIND.ordinal()];
		
		return PAYTABLE[Hands.NOTHING.ordinal()];
	}
	
	@Override
	public void print() {
		
		Iterator<Card> it = cards.iterator();
		while (it.hasNext()) {
			
			System.out.print(it.next() + " ");
			
		}
		System.out.println(evaluate());
	}
	
}