package com.anpo.userconsumer;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.anpo.feignuserapi.Person;

@Component
public class UserProviderFallBack implements ConsumerApi{

	@Override
	public String alive() {
		return "被降级了！";
	}

	@Override
	public String getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String register() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getMap(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getMap1(Integer id, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getMap2(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person postPerson(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

}
