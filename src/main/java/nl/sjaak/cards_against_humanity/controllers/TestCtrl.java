package nl.sjaak.cards_against_humanity.controllers;

import java.awt.Color;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import nl.sjaak.cards_against_humanity.ReadCards;

@Controller
public class TestCtrl {
	
	static ReadCards reader = new ReadCards();
	
	
	@RequestMapping("/index")
	public String loadIndex()
	{
		return "test";
	}
	
	@RequestMapping("/thegame")
	public String startGame(Model model)
	{
		if(reader.testwhite.isEmpty())
			reader.read();
		if(reader.trackwhite == null)
			reader.resetTrack();
		
		//String tmp = reader.drawCards(10, Color.WHITE)[0].toString();
		//model.addAttribute("hand", tmp);
		
		model.addAttribute("hand",reader.drawCards(10, Color.WHITE));
		model.addAttribute("numCards", reader.trackwhite.size());
		model.addAttribute("numCards2", reader.testwhite.size());
		//model.addAttribute("hand", "OY");
		
		return "thegame";
	}

}

