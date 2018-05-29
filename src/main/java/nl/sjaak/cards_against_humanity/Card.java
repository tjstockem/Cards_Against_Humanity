package nl.sjaak.cards_against_humanity;

public abstract class Card 
{
	private final String text;
	private final int id;
	
	public Card(String txt, int id)
	{
		this.text = txt;
		this.id = id;
	}
	
	public String getText()
	{
		return this.text;
	}
	
	public String toString()
	{
		return this.text;
	}
	
	public int getID()
	{
		return this.id;
	}

	
	
}
