package nl.sjaak.cards_against_humanity;

import java.util.List;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadCards 
{
	
	public List<WhiteCard> testwhite;
	public List<BlackCard> testblack;
	
	public ArrayList<Integer> trackblack;
	public ArrayList<Integer> trackwhite;
	
	public ReadCards()
	{
		testwhite = new ArrayList<WhiteCard>();
		testblack = new ArrayList<BlackCard>();
	}
	
	
	public void read()
	{
		int idb = 1, idw = 1, idxp = 0, numpicks;
		Scanner sc = null;
		String filename = "src/main/resources/MainDeck.csv";
		File f = new File(filename);
		
		String line;
		String[] lineseg;
		
		try {
			sc = new Scanner(f);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		while(sc.hasNextLine())
		{	
			line = sc.nextLine();
			
			line = line.replaceAll("\"\"\"\"", "'");
			
			if(line.charAt(0)=='\"')
			{
				lineseg = line.split("\"\"");
			}
			else
			{
				lineseg = line.split(",");
			}
				
			if(line.length() > 15 && line.substring(0, 10).toLowerCase().contains("prompt"))
			{
				
				if((idxp = line.indexOf("PICK"))>0)
				{
					numpicks = line.charAt(idxp+5)-48;
					
				}
				else
				{
					numpicks = 1;
				}
				
				testblack.add(new BlackCard(lineseg[1],idb++,numpicks));
			}
			else if(line.length() > 15 && line.substring(0, 10).toLowerCase().contains("response"))
			{
				testwhite.add(new WhiteCard(lineseg[1], idw++));
			}
			
		}
	}
	
	public void resetTrack()
	{
		this.trackblack = new ArrayList<Integer>();
		this.trackwhite = new ArrayList<Integer>();
		
		for(int i = 0; i < testwhite.size(); ++i)
		{
			trackwhite.add(i);
		}
		
		for(int i = 0; i < testblack.size(); ++i)
		{
			trackblack.add(i);
		}
	}
	
	public Card[] drawCards(int numCards, Color color)
	{
		Card[] res = new Card[numCards];
		int idx;
		
		if(color == Color.BLACK && numCards < trackblack.size())
		{
			for(int i = 0; i < numCards; ++i)
			{
				idx = (int)(Math.random() * trackblack.size());
				res[i] = testblack.get(idx);
				trackblack.remove(idx);
			}
		}
		else if(color == Color.WHITE && numCards < trackwhite.size())
		{
			for(int i = 0; i < numCards; ++i)
			{
				idx = (int)(Math.random() * trackwhite.size());
				res[i] = testwhite.get(idx);
				trackwhite.remove(idx);
			}
		}
		else
		{
			System.err.println("UH OH something went wrong");
			return null;
		}
		
		return res;
	}
	
	
}
