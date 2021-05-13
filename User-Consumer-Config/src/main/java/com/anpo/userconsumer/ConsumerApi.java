package com.anpo.userconsumer;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.anpo.feignuserapi.Person;
import com.anpo.feignuserapi.UserApi;

/**
 * 配合自定义接口API使用
 * @author anpochen
 *
 */
//@FeignClient(name = "user-provider")
/*
 * 第一种
 */
//@FeignClient(name = "user-provider",fallback = UserProviderFallBack.class)
/*
 * 第二种
 */
@FeignClient(name = "user-provider",fallbackFactory = UserProviderBackFactory.class)
public interface ConsumerApi extends UserApi{

	/**
	 * 这里的这些注解都是给Feign看的
	 * Feign使用这些信息来组装新的HttpClient请求
	 */
	@GetMapping("/alive")
	public String alive();
	
	@GetMapping("/getById")
	public String getById(@RequestParam Integer id);
	
	@GetMapping("/register")
	public String register();
	
	
	@GetMapping("/getMap")
	public Map<String,Object> getMap(@RequestParam Integer id);
	
	@GetMapping("/getMap1")
	public Map<String,Object> getMap1(@RequestParam Integer id,@RequestParam String name);
	
	@GetMapping("/getMap2")
	public Map<String,Object> getMap2(@RequestParam Map<String,Object> map);

	@PostMapping("/postPerson")
	public Person postPerson(@RequestBody Person person);
	
}
