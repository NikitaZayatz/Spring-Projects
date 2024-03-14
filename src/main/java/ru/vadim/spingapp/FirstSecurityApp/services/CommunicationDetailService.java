package ru.vadim.spingapp.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vadim.spingapp.FirstSecurityApp.models.Communication;
import ru.vadim.spingapp.FirstSecurityApp.repositories.CommunicationPepository;

@Service
public class CommunicationDetailService implements UserDetailsService {


    private final CommunicationPepository communicationPepository;

    @Autowired
    public CommunicationDetailService(CommunicationPepository communicationPepository) {
        this.communicationPepository = communicationPepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
    public Communication get(int id) {
        return communicationPepository.findById(id).get();
    }

}
