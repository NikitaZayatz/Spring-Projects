package ru.vadim.spingapp.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vadim.spingapp.FirstSecurityApp.models.Attraction;
import ru.vadim.spingapp.FirstSecurityApp.repositories.AttractionRepository;

import java.util.List;

@Service
public class AttractionDetailService implements UserDetailsService {

    private final AttractionRepository attractionRepository;


    @Autowired
    public AttractionDetailService(AttractionRepository attractionRepository) {
        this.attractionRepository = attractionRepository;
    }

    public List<Attraction> listAll() {
        return attractionRepository.findAll();
    }
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

    public Attraction get(int id) {
        return attractionRepository.findById(id).get();
    }
    public void deleteAttractionByID(int id) {
        attractionRepository.deleteById(id);
    }
}
