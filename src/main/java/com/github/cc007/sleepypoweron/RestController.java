/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.sleepypoweron;

import com.google.gson.JsonObject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//POJO, no interface no extends

//The class registers its methods for the HTTP GET request using the @GET annotation. 
//Using the @Produces annotation, it defines that it can deliver several MIME types,
//text, XML and HTML. 

//The browser requests per default the HTML MIME type.

//Sets the path to base URL + /hello

@Path("/poweron")
public class RestController {

	// This method is called if TEXT_PLAIN is request
	@POST
    @Path("/{mac}")
	@Produces(MediaType.APPLICATION_JSON)
	public String powerOn(@PathParam("mac") String mac) {
        
        
        
        
        
        JsonObject g = new JsonObject();
        g.addProperty("result", 1);
		return g.toString();
	}

} 