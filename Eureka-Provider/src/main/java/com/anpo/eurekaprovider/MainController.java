package com.anpo.eurekaprovider;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	
	@Autowired
	HealthStatusService healthStatusService;
	
	@Value("${server.port}")
	String port;
	
	@GetMapping("/getHi")
	public String getHi() {
		return "Hi，我的port是：" + port;
	}
	
	@GetMapping("/getMap")
	public Map<String,String> getMap() {
		return Collections.singletonMap("name", "anpochen");
	}
	
	@GetMapping("/getObj")
	public Object getObj() {
		return new Person(1,"anpo",20);
	}
	
	@GetMapping("/getObj2")
	public Object getObj2(String name,Integer age) {
		if(name == null ) {
			name = "anpo";
		}
		if(age == null ) {
			age = 20;
		}
		return new Person(1,name,age);
	}
	
	@PostMapping("/postObj")
	public Object postObj(@RequestBody String name, Integer age) {
		if(name == null ) {
			name = "anpo";
		}
		if(age == null ) {
			age = 20;
		}
		return new Person(1,name,age);
	}
	
	@PostMapping("/postLocation")
	public URI postLocation(@RequestBody Map params,HttpServletResponse response) throws Exception {
		
		String param = (String) params.get("param");
		System.out.println("param == " + param);
		
		URI uri = new URI("https://www.baidu.com/s?wd=" + param);
		
		//如果不加这一句的话，返回的回事null
		response.addHeader("location", uri.toString());
 		return uri;
	}
	
	@GetMapping("/health")
	public String setHealth(@RequestParam("status") boolean status) {
		healthStatusService.setStatus(status);
		return healthStatusService.isStatus();
	}

}
