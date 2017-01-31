/*package com.mn.jira;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mn.util.Jira;

@Controller
public class getProjectKeys {
	
	@Autowired
	Jira jira;
	
	@RequestMapping(value="/jira/rest/getprojectkeys", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getProjectKeys(@RequestBody String inputJson){
		String projects="";
		String response="";
		
		try {
			JSONParser parser  = new JSONParser();
			JSONObject objJson = (JSONObject)parser.parse(inputJson);
			String username = (String)objJson.get("username"); 
			String password  = (String)objJson.get("password"); 
			String url = "";
			projects = jira.doGet(url, username, password);
			
			System.out.println(projects);
		} catch (Exception e) {
			// TODO: handle exception 
			
		} 			
		return new ResponseEntity<String>(projects,HttpStatus.OK);
	}
	

}
*/