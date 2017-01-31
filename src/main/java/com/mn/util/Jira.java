package com.mn.util;

import org.springframework.http.ResponseEntity;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class Jira {

	public String doPost(String _url, String username, String password) throws Exception{
		
		Client client = null;
		ClientResponse response = null;
		 try {
			 client = Client.create();
			 client.addFilter(new HTTPBasicAuthFilter(username, password));
			WebResource  webResc = client.resource(_url);
			response = webResc.type("application/json").post(ClientResponse.class);
		/*	if(str.equals("")){
				response = webResc.type("application/json").post(ClientResponse.class);
				}else {
					response = webResc.type("application/json").post(ClientResponse.class);
					}*/
			if(response.getStatus() == 200 || response.getStatus() == 201){
				return response.getEntity(String.class);
				}else{
					throw new RuntimeException("Failed : HTTP error code : " + response.getStatus() + " " + response.getEntity(String.class) );
				}
		} finally {
            client.destroy();
        }
	}
	
	public String doGet(String _url, String username, String password) throws RuntimeException, Exception {
		Client client = null;
		ClientResponse response = null;
		try {
			client = Client.create();  
			 client.addFilter(new HTTPBasicAuthFilter(username, password));
			WebResource  webResc = client.resource(_url);			
			 response = webResc.type("application/json").get(ClientResponse.class);
			if (response.getStatus() == 200 || response.getStatus() == 201) {
				return response.getEntity(String.class);
			} else { 
				throw new RuntimeException("Failed : HTTP Error Code : "+ response.getStatus() + " "+ response.getEntity(String.class));
			}
		} finally {
			client.destroy();
		}
	}
	
}
