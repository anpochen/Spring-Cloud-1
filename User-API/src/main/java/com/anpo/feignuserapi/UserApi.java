package com.anpo.feignuserapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * 在使用Hystrix时不能有这个，不然启动会加载两次，会报错
 */
//@RequestMapping("/User")
public interface UserApi {

	/**
	 * 查看当前服务状态
	 * @return 服务状态描述
	 */
	@GetMapping("/alive")
	public String alive();
	
	/**
	 * 根据id获取信息
	 * @return 对应的字符串
	 */
	@GetMapping("/getById")
	public String getById(Integer id);
	
	@PostMapping("/postPerson")
	public Person postPerson(Person person);
	
}
