package com.anpo.eurekaprovider;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.EurekaClient;

@RestController
public class MainController2 {
	
	@Autowired
	DiscoveryClient client;
	
	@Autowired
	EurekaClient client1;
	
	@Autowired
	LoadBalancerClient loadBalancerClient;
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/client4")
	public String client4() {
		
		ServiceInstance instance = loadBalancerClient.choose("provider");
		
		String url = "http://" + instance.getHost() + ":" +instance.getPort() + "/getHi";
		System.out.println("url==" + url);
		
		String respStr = restTemplate.getForObject(url, String.class);
		System.out.println(respStr);
		
		return respStr;
	}
	
	AtomicInteger atomicInteger = new AtomicInteger(0);
	/**
	 * 自定义负载均衡策略
	 * @return
	 */
	@GetMapping("/client5")
	public String client5() {
		
		List<ServiceInstance> instances = client.getInstances("provider");
		
		//随机
//		int nextInt = new Random().nextInt(instances.size());
//		ServiceInstance instance =instances.get(nextInt);
//		
		//轮询
		int atomicInt = atomicInteger.getAndIncrement();
		int nextIndex = atomicInt % instances.size();
		ServiceInstance instance =instances.get(nextIndex);
		
		//加权  未完成
//		for (ServiceInstance serviceInstance : instances) {
//			//需要配合在metadata中添加属性，在获取后做加权选择
//			serviceInstance.getMetadata().get()
//		}
		
		String url = "http://" + instance.getHost() + ":" +instance.getPort() + "/getHi";
		System.out.println("url==" + url);
		
		String respStr = restTemplate.getForObject(url, String.class);
		System.out.println(respStr);
		
		return respStr;
	}
	
	@GetMapping("/client6")
	public String client6() {
		
		String url = "http://provider/getHi";
		System.out.println("url==" + url);
		
		String respStr = restTemplate.getForObject(url, String.class);
		System.out.println(respStr);
		
		return respStr;
	}
	
	
}
