package com.anpo.userconsumer;

import java.net.URI;
import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class RestService {
	
	@Autowired
	RestTemplate restTemplate;

	@HystrixCommand(defaultFallback = "back")
	public String alive() {
		
		String url = "http://user-provider/alive";
		
		String string = restTemplate.getForObject(url, String.class);
		
		return "alive == " + string;
	}
	
	public String back() {
		
		return "back";
	}
	
	

}
