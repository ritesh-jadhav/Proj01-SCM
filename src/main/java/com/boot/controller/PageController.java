package com.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.entities.User;
import com.boot.forms.UserForm;
import com.boot.helpers.Message;
import com.boot.helpers.MessageType;
import com.boot.services.IUserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PageController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/home")
    public String home(Model model) {
        model.addAttribute("title", "this is the technical thing");
        model.addAttribute("github", "https://github.com/ritesh-jadhav");
        System.out.println("home request");
        return "home";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/")
    public String index()
    {
        return "redirect:/home";
    }

    @RequestMapping("/services")
    public String services() {
        return "services";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/login")
    public String login() {
        return "login";

    }

    @GetMapping("/register")
    public String signup(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);

        return "register";
    }

    @PostMapping("/do-register")
    public String register(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session) {
        System.out.println("do form process");
        if(rBindingResult.hasErrors()){
            return "register";
        }
        // User user = User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .about(userForm.getAbout())
        // .password(userForm.getPassword())
        // .phoneNumber(userForm.getPhoneNumber())
        // .profilePic("https://pics.craiyon.com/2023-11-26/oMNPpACzTtO5OVERUZwh3Q.webp")
        // .build();

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setAbout(userForm.getAbout());
        user.setPassword(userForm.getPassword());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("https://pics.craiyon.com/2023-11-26/oMNPpACzTtO5OVERUZwh3Q.webp");
        User savedUser = userService.saveUser(user);
        System.out.println(savedUser);
        Message message = Message.builder().content("Registration succesful! ").type(MessageType.blue).build();
        session.setAttribute("message", message);
        return "redirect:/register";
    }

}
