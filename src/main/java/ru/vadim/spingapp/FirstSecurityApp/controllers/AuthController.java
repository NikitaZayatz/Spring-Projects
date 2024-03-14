package ru.vadim.spingapp.FirstSecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vadim.spingapp.FirstSecurityApp.models.Person;
import ru.vadim.spingapp.FirstSecurityApp.repositories.PeopleRepository;
import ru.vadim.spingapp.FirstSecurityApp.services.RegistrationService;
import ru.vadim.spingapp.FirstSecurityApp.util.PersonValidator;

import javax.validation.Valid;


@Controller
@RequestMapping("/auth")
public class AuthController {

    PeopleRepository peopleRepository;

    private final RegistrationService registrationService;
    private final PersonValidator personValidator;

    @Autowired
    public AuthController(RegistrationService registrationService, PersonValidator personValidator) {
        this.registrationService = registrationService;
        this.personValidator = personValidator;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }


    @GetMapping("/index")
    public String indexPage() {
        return "auth/index";
    }


    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") @Valid Person person,
                                      BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors())
            return "/auth/registration";

        registrationService.register(person);

        return "redirect:/auth/login";
    }

}
