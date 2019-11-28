public class Card implements Comparable<Card> {
	
	int masterValue;
	
	public Card(int value) {
		
		masterValue = value;
	}
	
	@Override
	public String toString() {
		
		
		String retval;
	    int unconvertedValue = getCardValue();
		switch(unconvertedValue) {
		
			case  1: retval = "A";
					 break;
			case 11: retval = "J";
					 break;
			case 12: retval = "Q";
					 break;
			case 13: retval = "K";
					 break;
			default: retval = Integer.toString(unconvertedValue);
		
		}
		
		switch(this.getSuit()) {
		
			case 0: retval+= 's';
					break;
			case 1: retval+= 'h';
					break;
			case 2: retval+= 'd';
					break;
			case 3: retval += 'c';
		
		}
		
		return retval;
	}
	
	
	public int getCardValue() {
		
		return (masterValue % 13) + 1;
		
	}
	
	public int getSuit() {
		
		return (int) Math.floor(masterValue / 13);
	}

	
	public boolean isCardValue(int value) {
		
		return getCardValue() == value;
		
	}
	
	public boolean isHighCard() {
		
		int cardValue = this.getCardValue();
		if (cardValue == 11 || cardValue == 0 || cardValue == 12 || cardValue == 13)
			return true;
		
		return false;
	}
	
	
	@Override
	public boolean equals(Object o) {
		
		if (o == null)
			return false;
		
		if (!(o instanceof Card))
			return false;
		
		return masterValue == ((Card) o).masterValue;
	}
	
	@Override
	public int hashCode() {
		
		return masterValue;
		
	}	
	
	public int compareTo(Card c) {
		
		return getCardValue() - c.getCardValue();
		
	}

	
}