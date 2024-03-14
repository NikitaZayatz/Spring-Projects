package ru.vadim.spingapp.FirstSecurityApp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



import ru.vadim.spingapp.FirstSecurityApp.services.AttractionDetailService;
import ru.vadim.spingapp.FirstSecurityApp.services.MenuDetailsService;
import ru.vadim.spingapp.FirstSecurityApp.services.PersonDetailsService;
import ru.vadim.spingapp.FirstSecurityApp.services.ProcedureDetailService;
import ru.vadim.spingapp.FirstSecurityApp.models.*;
import ru.vadim.spingapp.FirstSecurityApp.repositories.*;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    PersonDetailsService personDetailsService;

    List<Seance> newSeance = new ArrayList<>();
    List<Attraction> newAttractions = new ArrayList<>();
    List<Menu> newMenu = new ArrayList<>();
    List<Person> newPerson = new ArrayList<>();
    @Autowired
    MenuDetailsService menuDetailsService;

    @Autowired
    ProcedureDetailService procedureDetailService;
    @Autowired
    AttractionDetailService attractionDetailService;

    @Autowired
    AttractionRepository attractionRepository;
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    PeopleRepository peopleRepository;
    @Autowired
    ProcedureRepository procedureRepository;

    @Autowired
    SeanceRepository seanceRepository;

    @Autowired
    CommunicationPepository communicationPepository;


    @GetMapping("auth/homePage")
    public String redirection(){
        Person person = peopleRepository.findByUsername(getCurrentUserName());

        if(person.getRole().equals("ROLE_ADMIN")){
            return "redirect:/admin/admin";
        }
        else
            return "redirect:/user/userPage";

    }


    @GetMapping( "/auth/transfer" )
    public String TransferPage(@ModelAttribute("transfer") Transfer transfer, Model model) {
        Person person = peopleRepository.findByUsername(getCurrentUserName());
        Transfer tr = person.getTransfer();
        model.addAttribute("Transfer",tr);
        return "/auth/transfer";
    }

    @PostMapping("/auth/{id}")
    public String addNewDish(@PathVariable("id") int id) {
        Menu menu = menuDetailsService.get(id);
        Person person = peopleRepository.findByUsername(getCurrentUserName());
        newMenu.add(menu);
        newPerson.add(person);
        person.setMenu(newMenu);
        menu.setPersons(newPerson);
        menuRepository.save(menu);
        peopleRepository.save(person);
        return "redirect:/auth/food";
    }

    @PostMapping("/auth/transfer")
    public String postTransfer(@ModelAttribute("transfer") @Valid Transfer transfer) throws EntityExistsException{
        Person person = peopleRepository.findByUsername(getCurrentUserName());
        if(person.getTransfer() == null) {
            transfer.setPerson(person);
            person.setTransfer(transfer);
            peopleRepository.save(person);
        }
        return "/auth/transfer";
    }


    public String getCurrentUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         return authentication.getName();
    }


    @GetMapping("/auth/food")
    public String menuList(Model model)
    {
        List<Menu> menuList = menuDetailsService.listAll();
        model.addAttribute("menuList",menuList);
        return "/auth/food";
    }


    @GetMapping("/auth/sights")
    public String sightsList(Model model)
    {
        List<Attraction> attractionList = attractionDetailService.listAll();
        model.addAttribute("attractionList",attractionList);
        return "/auth/sights";
    }


    @PostMapping("/auth/sights/{id}")
    public String addNewAttractionToPerson(@PathVariable("id") int id) {
        Attraction attraction= attractionDetailService.get(id);
        Person person = peopleRepository.findByUsername(getCurrentUserName());
        newAttractions.add(attraction);
        newPerson.add(person);
        person.setAttractions(newAttractions);
        attraction.setPersons(newPerson);
        attractionRepository.save(attraction);

        peopleRepository.save(person);
        return "redirect:/auth/sights";
    }


    @GetMapping( "/auth/rooms" )
    public String SettlementPage(@ModelAttribute("settlement") Settlement settlement, Model model) {
        Person person = peopleRepository.findByUsername(getCurrentUserName());
        Settlement st = person.getSettlement();
        model.addAttribute("Settlement",st);
        return "/auth/rooms";
    }

    @PostMapping("/auth/settlement")
    public String postSettlementPage(@ModelAttribute("settlement") @Valid Settlement settlement) throws EntityExistsException{
        Person person = peopleRepository.findByUsername(getCurrentUserName());
        if(person.getSettlement() == null) {
            settlement.setPerson(person);
            person.setSettlement(settlement);
            peopleRepository.save(person);
        }
        return "/auth/rooms";
    }


    @PostMapping("/auth/medicine")
    public String postMedicinePage(@ModelAttribute("seance")  @Valid Seance seance) {
        Person person = peopleRepository.findByUsername(getCurrentUserName());
        seance.setPerson(person);
        Set<Seance> sc = new HashSet<>();
        sc.add(seance);
        newSeance.add(seance);
        if(seance.getProcedure().isIsavaliable()) {
           // seance.getProcedure().setIsavaliable(false);
            person.setSeances(sc);
            seance.getProcedure().setSeances(newSeance);
            procedureRepository.save(seance.getProcedure());
            peopleRepository.save(person);
            seanceRepository.save(seance);
        }
        return "redirect:/auth/medicine";
    }

    @GetMapping("/auth/medicine")
    public String doctorsList(@ModelAttribute("seance") Seance seance,Model model)
    {
        List<Procedure> procedureList = procedureDetailService.listAll();
        model.addAttribute("procedureList",procedureList);
        return "/auth/medicine";
    }


    public boolean findUsingEnhancedForLoop(String name, List<Seance> seance) {

        for (Seance seance1: seance) {
            if (seance1.getSeance_time().equals(name)) {
                return false;
            }
        }
        return true;
    }


    @GetMapping("/auth/questions")
    public String communication(Model model)
    {
        Person person = peopleRepository.findByUsername(getCurrentUserName());
        Communication communication = person.getCommunication();
        model.addAttribute("communication",communication);
        return "/auth/questions";
    }

    @PostMapping("/auth/questions")
    public String postCommunication(@ModelAttribute("communication") @Valid Communication communication) throws EntityExistsException{
        Person person = peopleRepository.findByUsername(getCurrentUserName());
        if(person.getCommunication() == null){
            communication.setPerson(person);
            person.setCommunication(communication);
            communicationPepository.save(communication);
        }
        return "/auth/questions";
    }

    @GetMapping("/auth/spa")
    public String spaGet(Model model)
    {
        Person person = peopleRepository.findByUsername(getCurrentUserName());
         Spa spa = person.getSpa();
        model.addAttribute("spa",spa);
        return "/auth/spa";
    }


    @PostMapping("/auth/spa")
    public String spaPostPage(@ModelAttribute("spa") @Valid Spa spa) throws EntityExistsException{
        Person person = peopleRepository.findByUsername(getCurrentUserName());
        if(person.getSpa() == null){
            spa.setPerson(person);
            person.setSpa(spa);
            peopleRepository.save(person);
        }
        return "redirect:/auth/spa";
    }


    @GetMapping("/user/userPage")
    public String userPageProfile(Model model)
    {
        Person person = peopleRepository.findByUsername(getCurrentUserName());
        model.addAttribute("person",person);
        return "/user/userPage";
    }
    @GetMapping("/user/userPageTransfer")
    public String userPageTransfer(Model model)
    {
        Person person = peopleRepository.findByUsername(getCurrentUserName());
        Transfer transfer = person.getTransfer();
        model.addAttribute("transfer",transfer);
        return "/user/userPageTransfer";
    }

    @GetMapping("/user/userPageMedicine")
    public String userPageMedicine(Model model)
    {
        Person person = peopleRepository.findByUsername(getCurrentUserName());
        Set<Seance> seances = person.getSeances();
        model.addAttribute("seances",seances);
        return "/user/userPageMedicine";
    }

    @GetMapping("/user/userPageSpa")
    public String userPageSpa(Model model)
    {
        Person person = peopleRepository.findByUsername(getCurrentUserName());
        Spa spa = person.getSpa();
        model.addAttribute("spa",spa);
        return "/user/userPageSpa";
    }

    @GetMapping("/user/userPageRooms")
    public String userPageRooms(Model model)
    {
        Person person = peopleRepository.findByUsername(getCurrentUserName());
        Settlement settlement = person.getSettlement();
        model.addAttribute("settlement",settlement);
        return "/user/userPageRooms";
    }


    @GetMapping("/user/userPageAttraction")
    public String userPageAttraction(Model model)
    {
        Person person = peopleRepository.findByUsername(getCurrentUserName());
        List<Attraction> attraction = person.getAttractions();
        model.addAttribute("attractionList",attraction);
        return "/user/userPageAttraction";
    }

    @GetMapping("/user/userPageRestourant")
    public String userPageRestaurant(Model model)
    {
        Person person = peopleRepository.findByUsername(getCurrentUserName());
        List<Menu> menu = person.getMenu();
        model.addAttribute("menuList",menu);
        return "/user/userPageRestourant";
    }


    @GetMapping("/user/userPageQuestion")
    public String userPageQuestion(Model model)
    {
        Person person = peopleRepository.findByUsername(getCurrentUserName());
        Communication communication = person.getCommunication();
        model.addAttribute("communication",communication);
        return "/user/userPageQuestion";
    }
}
