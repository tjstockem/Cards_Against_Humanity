package nl.sjaak.cards_against_humanity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WhiteCard
{
	@Id
	protected long id;
	protected String text;

	public WhiteCard()
	{

	}

	public WhiteCard(String txt, long id)
	{
		this.id = id;
		this.text = txt;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public String getText()
	{
		return this.text;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getId()
	{
		return this.id;
	}

	public String toString()
	{
		return this.text;
	}
}
