package com.mn.jira;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/rest")
public class JiraReporter {
	
	@RequestMapping( value="/jirareporter", method=RequestMethod.POST, consumes=MediaType.APPLICATION_ATOM_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody void  publishJson(@RequestBody String json, HttpServletResponse response, HttpServletRequest request) throws Exception{

		try { 
			 System.out.println("incoming..."); 
	
	// Returns & Prints the input
	System.out.println(json); 

 
	response.addHeader("Content-Type", "application/jsonp");
	response.setHeader("Access-Control-Allow-Origin", "*");
	response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	response.setHeader("Access-Control-Max-Age", "3600");
	response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	response.setContentType("application/json");
	response.getOutputStream().write(new String("success").getBytes());

		} catch (Exception e) { 
			
			e.printStackTrace(); 
		}
//		return json;

	}
	
	public enum days{
		MONDAY, 
		TUESDAY,
		WEDNESDAY
		
	}

	

	
	
}
 