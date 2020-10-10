package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class HomeController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public  String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    //implementing role based permission

    //@RequestMapping("/admin")
 /*   public String admin(){
        return "admin";
    }*/
    @RequestMapping("/secure")
    public String secure(Principal principal, Model model){
        //@AUtowiring not applicable for a local scope
        String username = principal.getName();
        model.addAttribute("user", userRepository.findByUsername(username));
        return "secure";
    }

}
