package ru.vadim.spingapp.FirstSecurityApp.controllers;


import org.apache.tomcat.jni.Proc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vadim.spingapp.FirstSecurityApp.models.*;
import ru.vadim.spingapp.FirstSecurityApp.repositories.*;
import ru.vadim.spingapp.FirstSecurityApp.services.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    List<Person> listPersons;
    List<Menu> listMenu;


    @Autowired
    PeopleRepository peopleRepository;
    @Autowired
    PersonDetailsService personDetailsService;

    @Autowired
    CommunicationDetailService communicationDetailService;
    @Autowired
    CommunicationPepository communicationPepository;

    @Autowired
    AttractionDetailService attractionDetailService;
    @Autowired
    AttractionRepository attractionRepository;


    @Autowired
    MenuDetailsService menuDetailsService;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    ProcedureDetailService procedureDetailService;

    @Autowired
    ProcedureRepository procedureRepository;


    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @GetMapping("/admin/admin")
    public String userList(Model model)
    {
        listPersons = personDetailsService.listAll();
        model.addAttribute("users",listPersons);
        return "/admin/admin";
    }

    @PostMapping("/admin/admin/{id}")
    public String userEdit(@PathVariable("id") int id,@ModelAttribute("person") Person newPerson)
    {
        Person person = personDetailsService.get(id);
        person.setName(newPerson.getName());
        person.setLast_name(newPerson.getLast_name());
        person.setRole(newPerson.getRole());
        person.setEmail(newPerson.getEmail());
        person.setUsername(newPerson.getUsername());
        peopleRepository.save(person);
        return "redirect:/admin/admin";
    }

  /*  @GetMapping("/admin/menuOut/{id}")
    public String menuUserList(@PathVariable("id") int id, Model model){
        Person person =  personDetailsService.get(id);
        listMenu = person.getMenu();
        model.addAttribute("listMenu",listMenu);
        return "/admin/adminRest";
    }*/
    @GetMapping("/admin/deleteUser/{id}")
    public String deleteUser(@PathVariable int id) {
        personDetailsService.deletePersonById(id);
        return "redirect:/admin/admin";
    }

    @GetMapping("/admin/adminAnswer")
    public String adminAnswer(Model model)
    {
        List<Communication> communicationList = communicationPepository.findAll();
        model.addAttribute("communicationList",communicationList);
        return "/admin/adminAnswer";
    }

    @PostMapping("/admin/adminAnswer/{id}")
    public String adminPostAnswer(@PathVariable int id,@ModelAttribute("communication") Communication communication)
    {
        Communication communication1 = communicationDetailService.get(id);
        communication1.setAnswer(communication.getAnswer());
        communicationPepository.save(communication1);
        return "redirect:/admin/adminAnswer";
    }

    @GetMapping("/admin/adminAddData")
    public String adminAddData(Model model)
    {
       List<Attraction> attractionList = attractionDetailService.listAll();
       Attraction newAttraction = new Attraction();
       List<Procedure> procedureList = procedureDetailService.listAll();
       Procedure newProcedure = new Procedure();
       List<Menu> menuList = menuDetailsService.listAll();
       Menu newMenu = new Menu();
       model.addAttribute("menuList",menuList);
       model.addAttribute("men",newMenu);
       model.addAttribute("procedureList",procedureList);
       model.addAttribute("proc",newProcedure);
       model.addAttribute("attract",newAttraction);
       model.addAttribute("attractionList",attractionList);
        return "/admin/adminAddData";
    }


    @PostMapping("/admin/adminUpdateData/{id}")
    public String userUpdateData(@PathVariable("id") int id,@ModelAttribute("attraction") Attraction newAttraction)
    {
        Attraction  attraction = attractionDetailService.get(id);
        attraction.setAttraction_name(newAttraction.getAttraction_name());
        attraction.setAttraction_description(newAttraction.getAttraction_description());
        attraction.setImage_url(newAttraction.getImage_url());
        attractionRepository.save(attraction);
        return "redirect:/admin/adminAddData";
    }

    @PostMapping("/admin/adminUpdateDataProcedure/{id}")
    public String adminUpdateDataProcedure(@PathVariable("id") int id,@ModelAttribute("procedure") Procedure newProcedure)
    {
        Procedure procedure = procedureDetailService.get(id);
        procedure.setLocation_from(newProcedure.getLocation_from());
        procedure.setProcedure_time(newProcedure.getProcedure_time());
        procedure.setProcedure_cost(newProcedure.getProcedure_cost());
        procedure.setDoctor(newProcedure.getDoctor());
        procedureRepository.save(procedure);
        return "redirect:/admin/adminAddData";
    }

    @GetMapping("/admin/deleteEntity/{id}")
    public String deleteEntity(@PathVariable int id) {
        attractionDetailService.deleteAttractionByID(id);
        return "redirect:/admin/adminAddData";
    }


    @GetMapping("/admin/deleteProcedure/{id}")
    public String deleteProcedure(@PathVariable int id) {
        procedureDetailService.deleteProcedureByID(id);
        return "redirect:/admin/adminAddData";
    }

    @PostMapping("/admin/adminAddData")
    public String userAddData(@ModelAttribute("attract") Attraction newAttraction)
    {
        attractionRepository.save(newAttraction);
        return "redirect:/admin/admin";
    }

    @PostMapping("/admin/adminUpdateDataProcedure")
    public String adminUpdateDataProcedure(@ModelAttribute("proc") Procedure newProcedure)
    {
        procedureRepository.save(newProcedure);
        return "redirect:/admin/admin";
    }

    @PostMapping("/admin/adminUpdateDataMenu/{id}")
    public String adminUpdateDataMenu(@PathVariable("id") int id,@ModelAttribute("menu") Menu newMenu)
    {
        Menu menu = menuDetailsService.get(id);
        menu.setDish_name(newMenu.getDish_name());
        menu.setDish_description(newMenu.getDish_description());
        menu.setDish_price(newMenu.getDish_price());
        menu.setImage_url(newMenu.getImage_url());
        menuRepository.save(menu);
        return "redirect:/admin/adminAddData";
    }

    @PostMapping("/admin/adminUpdateDataMenu")
    public String adminUpdateMenu(@ModelAttribute("men") Menu newMenu)
    {
        menuRepository.save(newMenu);
        return "redirect:/admin/adminAddData";
    }
    @GetMapping("/admin/deleteMenu/{id}")
    public String deleteMenu(@PathVariable int id) {
        menuDetailsService.deleteMenuById(id);
        return "redirect:/admin/adminAddData";
    }
}
