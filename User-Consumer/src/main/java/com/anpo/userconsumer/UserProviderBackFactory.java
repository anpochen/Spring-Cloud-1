package com.anpo.userconsumer;

import java.util.Map;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.stereotype.Component;

import com.anpo.feignuserapi.Person;

import feign.FeignException.InternalServerError;
import feign.hystrix.FallbackFactory;

@Component
public class UserProviderBackFactory implements FallbackFactory<ConsumerApi>{

	@Override
	public ConsumerApi create(Throwable cause) {
		// TODO Auto-generated method stub
		return new ConsumerApi() {
			
			@Override
			public String register() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Person postPerson(Person person) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Map<String, Object> getMap2(Map<String, Object> map) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Map<String, Object> getMap1(Integer id, String name) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Map<String, Object> getMap(Integer id) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getById(Integer id) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String alive() {
				
				System.out.println("cause == " +ReflectionToStringBuilder.toString(cause));
				System.out.println("cause Message == " + ReflectionToStringBuilder.toString(cause.getStackTrace()));
				if(cause instanceof InternalServerError) {
					System.out.println("InternalServerError");
					return "远程服务报错:" + cause.getMessage();
				}else if(cause instanceof RuntimeException) {
					
					return "请求时异常：" + cause.getMessage();
				}else {
					return "都算不上:" + cause.getMessage();
				}
			}
		};
	}

}
