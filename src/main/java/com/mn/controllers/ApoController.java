/**
 * @author Shawkath Khan
 * 
 * Added on: Oct 28, 2015
 * 
 * Purpose: 
 * 
 * Last Updated By: RLE0221
 */


package com.mn.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mn.view.ApoView;

@Controller
@RequestMapping(value="/apo")
public class ApoController {

//	@Autowired ApoView apoView;
	
	 @RequestMapping(value = "/values", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody List<ApoView> getAPOValues(@FormParam("name") String name)throws Exception{
		
	List<ApoView> lstView = new ArrayList<ApoView>();
	ApoView apoView = new ApoView();
	apoView.setName("Shaw");
	apoView.setAddress("160 North St");
	apoView.setCity("Bangalore");
	apoView.setState("Karnataka");
	apoView.setCountry("India");
	apoView.setPincode("506030");
	apoView.setPhoneNo("9791006006");	
	lstView.add(apoView);
	System.out.println(apoView);
		return lstView;
	}
	
	
}
