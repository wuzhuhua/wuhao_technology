package com.wuzhuhua.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wuzhuhua.entity.Person;
import com.wuzhuhua.mapper.PersonMapper;
import com.wuzhuhua.service.PersonService;

@Service("personService")
public class PersonServiceImpl implements PersonService{

	@Autowired
	private PersonMapper personMapper;
	
	@Override
	public List<Person> showPersons() {
		// TODO Auto-generated method stub
		return personMapper.queryAll();
	}

}
