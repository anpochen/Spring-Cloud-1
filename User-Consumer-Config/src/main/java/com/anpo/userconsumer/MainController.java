package com.anpo.userconsumer;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anpo.feignuserapi.Person;

@RestController
public class MainController {
	
	@Autowired
	ConsumerApi api;
	
	@Value("${server.port}")
	String port;
	
	@Value("${MyConfig}")
	String myConfig;
	
	//不写路径代表根路径
	@GetMapping
	public String getMyConfig() {
		return "myConfig -->> "+ myConfig;
	}
	
	@GetMapping("/alive")
	public String alive() {
		System.out.println("alive");
		return "consumer -->> "+ port + "-----" + api.alive();
	}
	
	@Autowired
	RestService restService;
	
	@GetMapping("/alive2")
	public String alive2() {
		System.out.println("alive2");
		return restService.alive();
	}
	
	@GetMapping("/getById")
	public String getById(Integer id) {
		return api.getById(id);
	}
	
	@GetMapping("/getMap")
	public Map<String,Object> getMap(Integer id) {
		return api.getMap(id);
	}
	
	@GetMapping("/getMap1")
	public Map<String,Object> getMap1(Integer id,String name) {
		return api.getMap1(id,name);
	}
	
	@GetMapping("/getMap2")
	public Map<String,Object> getMap2(@RequestParam Map<String,Object> map) {
		System.out.println(map);
		return api.getMap2(map);
	}
	
	@GetMapping("/postPerson")
	public Person postPerson(@RequestParam Map<String,Object> map) {
		Person person = new Person(Integer.parseInt((String)map.get("id")),(String)map.get("name"));
		System.out.println(person);
		return api.postPerson(person);
	};

}
