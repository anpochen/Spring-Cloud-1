package com.anpo.eurekaprovider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

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
public class MainController3 {
	
	@Autowired
	DiscoveryClient client;
	
	@Autowired
	EurekaClient client1;
	
	@Autowired
	LoadBalancerClient loadBalancerClient;
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/client7")
	public String client7() {
		
		String url = "http://provider/getHi";
		System.out.println("url==" + url);
		
		String respStr = restTemplate.getForObject(url, String.class);
		System.out.println(respStr);
		
		ResponseEntity<String> respStr1 = restTemplate.getForEntity(url, String.class);
		System.out.println(respStr1);
		
		return "respStr == " + respStr + "</br>respStr1 == " + respStr1;
	}
	
	@GetMapping("/client8")
	public String client8() {
		
		String url = "http://provider/getMap";
		System.out.println("url==" + url);
		
		Map map = restTemplate.getForObject(url, Map.class);
		System.out.println(map);
		
		ResponseEntity<Map> map1 = restTemplate.getForEntity(url, Map.class);
		System.out.println(map1);
		
		return "map == " + map + "</br>map1 == " + map1;
	}
	
	@GetMapping("/client9")
	public String client9() {
		
		String url = "http://provider/getObj";
		System.out.println("url==" + url);
		
		Person person = restTemplate.getForObject(url, Person.class);
		System.out.println(person);
		
		ResponseEntity<Person> person1 = restTemplate.getForEntity(url, Person.class);
		System.out.println(person1);
		
		return "person == " + person + "</br>person1 == " + person1;
	}
	
	@GetMapping("/client10")
	public String client10() {
		
		String url = "http://provider/getObj2?name={1}&age={2}";
		System.out.println("url==" + url);
		
		Person person = restTemplate.getForObject(url, Person.class,"anpo1",100);
		System.out.println(person);
		
		ResponseEntity<Person> person1 = restTemplate.getForEntity(url, Person.class,"anpo1",100);
		System.out.println(person1);
		
		return "person == " + person + "</br>person1 == " + person1;
	}
	
	@GetMapping("/client11")
	public String client11() {
		
		String url = "http://provider/getObj2?name={name}&age={age}";
		System.out.println("url==" + url);
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("name", "anpo2");
		map.put("age", 21);
		Person person = restTemplate.getForObject(url, Person.class,map);
		System.out.println(person);
		
		ResponseEntity<Person> person1 = restTemplate.getForEntity(url, Person.class,map);
		System.out.println(person1);
		
		return "person == " + person + "</br>person1 == " + person1;
	}
	
	@GetMapping("/client12")
	public String client12() {
		
		String url = "http://provider/postObj";
		System.out.println("url==" + url);
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("name", "anpo3");
		map.put("age", 24);
		Person person = restTemplate.postForObject(url, map, Person.class);
		System.out.println(person);
		
		ResponseEntity<Person> person1 = restTemplate.postForEntity(url, map, Person.class);
		System.out.println(person1);
		
		return "person == " + person + "</br>person1 == " + person1;
	}
}
