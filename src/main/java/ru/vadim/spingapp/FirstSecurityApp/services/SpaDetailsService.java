package ru.vadim.spingapp.FirstSecurityApp.services;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vadim.spingapp.FirstSecurityApp.models.Spa;
import ru.vadim.spingapp.FirstSecurityApp.repositories.SpaRepository;

import java.util.List;

@Service
public class SpaDetailsService implements UserDetailsService {

    private final SpaRepository spaRepository;

    public SpaDetailsService(SpaRepository spaRepository) {
        this.spaRepository = spaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }


    public Spa get(int id) {
        return spaRepository.findById(id).get();
    }

    public List<Spa> listAll() {
        return spaRepository.findAll();
    }
}
