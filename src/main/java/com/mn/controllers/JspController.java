package com.mn.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Shaw
 *
 */

@Controller
public class JspController {

	@RequestMapping("/")
	public String StartHomePage(ModelAndView modelAndView){
		return "homepage";	
		
	}
	
}
