package com.anpo.eurekaprovider;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;
import com.netflix.discovery.EurekaClient;

@RestController
public class MainController {
	
	@Autowired
	DiscoveryClient client;
	
	@Autowired
	EurekaClient client1;
	
	@Autowired
	LoadBalancerClient loadBalancerClient;
	
	@GetMapping("/getHi")
	public String getHi() {
		return "Hi";
	}
	
	@GetMapping("/client")
	public List<String> client() {
		List<String> list = client.getServices();
		for (String string : list) {
			System.out.println(string);
		}
		return list;
	}
	
	@GetMapping("/client1")
	public List<ServiceInstance> client1() {
		List<ServiceInstance> list = client.getInstances("provider");
		for (ServiceInstance ins : list) {
			System.out.println(ToStringBuilder.reflectionToString(ins));
		}
		return list;
	}
	
	@GetMapping("/client2")
	public String client2() {
		List<InstanceInfo> list = client1.getInstancesByVipAddress("provider", false);
		for (InstanceInfo instanceInfo : list) {
			System.out.println(ToStringBuilder.reflectionToString(instanceInfo));
		}
		if(list.size() > 0) {
			InstanceInfo info = list.get(0);
			if(info.getStatus() == InstanceStatus.UP) {
				String url = "http://" + info.getHostName() + ":" +info.getPort() + "/getHi";
				System.out.println("url==" + url);
				
				RestTemplate rest = new RestTemplate();
				String respStr = rest.getForObject(url, String.class);
				System.out.println(respStr);
				
				return respStr;
			}
		} 
		return null;
	}
	
	@GetMapping("/client3")
	public String client3() {
		
		ServiceInstance instance = loadBalancerClient.choose("provider");
		
		String url = "http://" + instance.getHost() + ":" +instance.getPort() + "/getHi";
		System.out.println("url==" + url);
		
		RestTemplate rest = new RestTemplate();
		String respStr = rest.getForObject(url, String.class);
		System.out.println(respStr);
		
		return respStr;
	}
	
	
}
