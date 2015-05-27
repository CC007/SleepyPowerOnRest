/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.sleepypoweron;

import com.google.gson.JsonObject;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author john
 */
@Controller
public class DefaultController {

	@RequestMapping(value = "/sleepy/poweron/{mac}", method = RequestMethod.GET)
	public @ResponseBody Result powerOn(@PathVariable String mac) {
		Result r = new Result();
		InetAddress broadcast;
		try {
			broadcast = InetAddress.getByName("255.255.255.255");
			if (broadcast != null) {
				WakeOnLan.wake(broadcast, mac);
				r.result = 1;
			} else {
				r.result = 0;
			}
		} catch (UnknownHostException ex) {
			Logger.getLogger(DefaultController.class.getName()).log(Level.SEVERE, null, ex);
			r.result = 0;
		}

		return r;
	}

}
