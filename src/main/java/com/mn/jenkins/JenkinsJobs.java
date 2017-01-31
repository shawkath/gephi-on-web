package com.mn.jenkins;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/jenkins")
public class JenkinsJobs {

	@RequestMapping("/getalljobs")
	public @ResponseBody String getAllJobs( ){
		String response = "";
		String URI = "http://localhost:8080/api/json?pretty=true";

		return null;

		
	}
	
}
