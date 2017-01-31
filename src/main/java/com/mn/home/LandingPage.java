package com.mn.home;

import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value="/rest")
@Controller
public class LandingPage {

	@RequestMapping(value="/jirainfo")
	 public @ResponseBody String fetchJiraIssueFields(@RequestBody String inputJson){
		String output= "";
		
		JSONParser parser = new JSONParser();
		
		
		return output; 
	}
}
