package com.scm.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.User;
import com.scm.forms.EmailForm;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.EmailService;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    // user dashbaord page

    @RequestMapping(value = "/dashboard")
    public String userDashboard() {
        System.out.println("User dashboard");
        return "user/dashboard";
    }

    // user profile page

    @RequestMapping(value = "/profile")
    public String userProfile(Model model, Authentication authentication) {

        return "user/profile";
    }

    // user add contacts page

    // user view contacts

    // user edit contact

    // user delete contact

    @GetMapping(value = "/directMessage")
    public String userDirectMessage(Model model) {

        EmailForm emailForm = new EmailForm();
        model.addAttribute("emailForm", emailForm);

        return new String("user/directMessage");
    }

    @RequestMapping(value = "/send-message", method = RequestMethod.POST)
    public String processSendMessage(@Valid @ModelAttribute EmailForm emailForm, BindingResult rBindingResult,
            HttpSession session) {
        System.out.println("Processing emailForm");
        // fetch form data
        // UserForm
        System.out.println(emailForm);

       emailService.sendEmail(emailForm.getToEmail(), emailForm.getSubject(), emailForm.getMessage());

        // validate form data
       
        // redirectto login page
        return "redirect:/user/directMessage";
    }

}