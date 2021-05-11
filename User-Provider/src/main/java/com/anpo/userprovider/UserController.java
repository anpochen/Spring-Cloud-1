package com.anpo.userprovider;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anpo.feignuserapi.Person;
import com.anpo.feignuserapi.UserApi;

@RestController("/User")
public class UserController implements UserApi{
	
	@Value("${server.port}")
	String port;
	
	private AtomicInteger count = new AtomicInteger();
	
	@GetMapping("/alive")
	public String alive() {
		
//		try { 
			System.out.println("准备睡");
		 
//		 	Thread.sleep(500); 
//		} catch (InterruptedException e) { 
//			 e.printStackTrace(); 
//		}
		 
//		int j = 1/0;
		int i = count.getAndIncrement(); 
		System.out.println(port + " === 第 " + i + " 次调用");
		return "provider -->> port:" + port;
	}

	@GetMapping("/getById")
	public String getById(Integer id) {
		if(id == 1) {
			return "anpo1";
		}
		return "anpo";
	}
	
	@GetMapping("/getMap")
	public Map<String,Object> getMap(@RequestParam Integer id) {
		
		return Collections.singletonMap("id", id);
	}
	
	@GetMapping("/getMap1")
	public Map<String,Object> getMap1(Integer id,String name) {
		Map<String,Object> map = new HashMap<>();
		map.put("id", id);
		map.put("name", name);
		return map;
	}
	
	@GetMapping("/getMap2")
	public Map<String,Object> getMap2(@RequestParam Map<String,Object> map) {
		System.out.println(map);
		map.put("id", Integer.parseInt((String)map.get("id")) + 1);
		map.put("name", map.get("name") + "1");
		return map;
	}
	
	@Override
	public Person postPerson(@RequestBody Person person) {
		System.out.println(person);
		return new Person(person.getId()+1,person.getName()+ "1");
	}


}
