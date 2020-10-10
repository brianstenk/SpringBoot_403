package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;
    @GetMapping("/register")
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }
    @PostMapping("/register")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user,
                                          BindingResult result, Model model){
        model.addAttribute("user", user);
        if(result.hasErrors()){
            return "registration";
        }
        else {
            userService.saveUser(user);
            model.addAttribute("message","User account created");
        }
        return "index";
    }

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
