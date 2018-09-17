package com.wuzhuhua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wuzhuhua.service.PersonService;

@Controller
@RequestMapping("/personController")
public class PersonController {
    
	@Autowired
    private PersonService personService;
    

    @RequestMapping("/showPerson")
    public String showPersons(Model model){
        
        return "showperson";
    }
}
