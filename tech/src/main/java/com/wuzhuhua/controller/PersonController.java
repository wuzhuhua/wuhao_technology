package com.wuzhuhua.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wuzhuhua.entity.Person;
import com.wuzhuhua.service.PersonService;

@Controller
@RequestMapping("/person")
public class PersonController {
    
	@Autowired
    private PersonService personService;
    

    @RequestMapping("/showPerson")
    public String showPersons(Model model){
    	List<Person> persons = personService.showPersons();
        return "showperson";
    }
}
