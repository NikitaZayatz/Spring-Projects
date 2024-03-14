package ru.vadim.spingapp.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vadim.spingapp.FirstSecurityApp.models.Procedure;
import ru.vadim.spingapp.FirstSecurityApp.repositories.ProcedureRepository;

import java.util.List;


@Service
public class ProcedureDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

    private final ProcedureRepository procedureRepository;


    @Autowired
    public ProcedureDetailService(ProcedureRepository procedureRepository) {
        this.procedureRepository = procedureRepository;
    }
    public Procedure get(int id) {
        return procedureRepository.findById(id).get();
    }

    public List<Procedure> listAll() {
        return procedureRepository.findAll();
    }

    public void deleteProcedureByID(int id) {
        procedureRepository.deleteById(id);
    }

}
