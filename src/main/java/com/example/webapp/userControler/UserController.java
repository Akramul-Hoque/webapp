package com.example.webapp.userControler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired private UserService service;

    @GetMapping("/users")
    public String showUserList(Model model){
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers",listUsers);
        return "users";}

    @GetMapping("/users/new")
    public String ShowNewForm(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("pageTitle","Add New User");
        return "user_form";

        }
    @PostMapping("/users/save")

        public String saveUser(User user){
            service.save(user);
            return "redirect:/users";
    }

    @GetMapping("/users/edit/{Id}")
    public String showEditForm(@PathVariable("Id") Integer Id ,Model model){
        try {
            User user = service.get(Id);
            model.addAttribute("user",user);
            model.addAttribute("pageTitle","Edit User(ID:"+Id+")");
            return "user_form";
        } catch (UserNotFoundException e) {
            return "redirect:/users";

        }
    }
    @GetMapping("/users/delete/{Id}")
    public String deleteUser(@PathVariable("Id") Integer Id)
    {
        try{
            service.delete(Id);
            return "redirect:/users";
        }
        catch (UserNotFoundException e){
            return "redirect:/users";
        }
    }



    }



