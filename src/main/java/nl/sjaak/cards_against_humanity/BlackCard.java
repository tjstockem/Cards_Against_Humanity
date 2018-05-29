package nl.sjaak.cards_against_humanity;

public class BlackCard extends Card
{
	private int num_picks;
	public BlackCard(String txt, int id, int picks)
	{
		super(txt, id);
		this.num_picks = picks;
	}
	
	public int getNumPicks()
	{
		return this.num_picks;
	}
}
