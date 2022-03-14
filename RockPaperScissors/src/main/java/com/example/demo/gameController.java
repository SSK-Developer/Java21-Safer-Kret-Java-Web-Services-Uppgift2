package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/RPS")
public class gameController {
	
	private gameBean gameBean = new gameBean();

	@RequestMapping(method = RequestMethod.POST)
	public String game(String userInput) {
		
		gameBean.setPlayerMove(userInput.toLowerCase());
		String result = gameBean.compareInputs();
		
		return 
				"Player: " + gameBean.getPlayerMove() + "<br>" + 
				"Computer: " + gameBean.getComputerMove() + "<br>" +
				"Winner: " + result;
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String totalScore(){
		
		return ObjectJson();
	}
	
	private String ObjectJson() {
		
		return "{"+ 
				"\"Total_Games\": \"" + gameBean.getGamesPlayed() + 
				"\"," +
				
				"\"Player\":" + 
					"[{"+
						"\"Total_Wins\":" + "\"" + gameBean.getPlayerTotalWins() + "\""+ 
						"," +
						"\"Total_Losses\":" + "\"" + gameBean.getPlayerTotalLosses() + "\""+ 
						"," +
						"\"Total_Ties\":" + "\"" + gameBean.getPlayerTotalTies() + "\""+
					"}]"+
				"," +
				
				"\"Computer\":" + 
					"[{"+
						"\"Total_Wins\":" + "\"" + gameBean.getComputerTotalWins() + "\""+ 
						"," +
						"\"Total_Losses\":" + "\"" + gameBean.getComputerTotalLosses() + "\""+ 
						"," +
						"\"Total_Ties\":" + "\"" + gameBean.getComputerTotalTies() + "\""+
					"}]"+
				"}";
	}
}
