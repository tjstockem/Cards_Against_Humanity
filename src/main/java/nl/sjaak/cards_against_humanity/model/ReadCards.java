package nl.sjaak.cards_against_humanity.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadCards 
{

	
	public ArrayList<Long> trackblack;
	public ArrayList<Long> trackwhite;


	
	public ReadCards()
	{
		trackwhite = new ArrayList<>();
		trackblack = new ArrayList<>();

	}
	
	
	public void read(CardRepoBlack repob, CardRepoWhite repow)
	{
		int idxp = 0, numpicks;
		long idB = 0, idW = 0;
		Scanner sc = null;
		String filename = "src/main/resources/MainDeck.csv";
		File f = new File(filename);
		
		BlackCard tmpb;
		WhiteCard tmpw;
		
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
				else {
					numpicks = 1;
				}
				tmpb = new BlackCard(lineseg[1],idB++,numpicks);

				if(tmpb != null) {
					repob.save(tmpb);
				}
			}
			else if(line.length() > 15 && line.substring(0, 10).toLowerCase().contains("response"))
			{
				tmpw = new WhiteCard(lineseg[1],idW++);
				if(tmpw != null)
				{
					repow.save(tmpw);
				}
			}
			
		}

	}
	
	public void resetTrack(CardRepoBlack repob, CardRepoWhite repow)
	{
		this.trackblack.clear();
		this.trackwhite.clear();
		
		for(int i = 0; i < repow.count(); ++i)
		{
			trackwhite.add(Long.valueOf(i));
		}
		
		for(int i = 0; i < repob.count(); ++i)
		{
			trackblack.add(Long.valueOf(i));
		}
	}
	
	public BlackCard drawBlack(CardRepoBlack repob)
	{
		int idx;
		BlackCard res = null;
		if(trackblack.size()>0)
		{
			idx = (int)(Math.random() * trackblack.size());
			res = repob.findById(trackblack.get(idx)).get();
			trackblack.remove(trackblack.get(idx));
		}
		
		return res;
	}
	
	public WhiteCard[] drawWhite(int numCards, CardRepoWhite repow) {
		WhiteCard[] res = new WhiteCard[numCards];
		int idx;


		if (numCards < trackwhite.size()) {
			for (int i = 0; i < numCards; ++i) {
				idx = (int) (Math.random() * trackwhite.size());
				res[i] = repow.findById(trackwhite.get(idx)).get();
				trackwhite.remove(trackwhite.get(idx));
			}
		}


		return res;
	}
}
