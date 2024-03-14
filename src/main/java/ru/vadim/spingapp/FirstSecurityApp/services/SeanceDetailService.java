package ru.vadim.spingapp.FirstSecurityApp.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vadim.spingapp.FirstSecurityApp.repositories.SeanceRepository;

@Service
public class SeanceDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }


    @Autowired
    public SeanceDetailService(SeanceRepository seanceRepository) {
    }



    /*public Seance get(int id) {
        return seanceRepository.findById(id).get();
    }*/
}
