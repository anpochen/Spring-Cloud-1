package com.anpo.eurekaprovider;

import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.EurekaClient;

@RestController
public class MainController4 {
	
	@Autowired
	DiscoveryClient client;
	
	@Autowired
	EurekaClient client1;
	
	@Autowired
	LoadBalancerClient loadBalancerClient;
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/client13")
	public String client13(HttpServletResponse response) throws Exception {
		
		String url = "http://provider/postLocation";
		System.out.println("url==" + url);
		
		String string = restTemplate.getForObject(url, String.class);
		
		return string;
	}
}
