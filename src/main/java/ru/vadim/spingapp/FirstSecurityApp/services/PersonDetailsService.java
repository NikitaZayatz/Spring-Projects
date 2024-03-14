package ru.vadim.spingapp.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vadim.spingapp.FirstSecurityApp.models.Person;
import ru.vadim.spingapp.FirstSecurityApp.repositories.PeopleRepository;
import ru.vadim.spingapp.FirstSecurityApp.security.PersonDetails;

import java.util.List;


@Service
public class PersonDetailsService implements UserDetailsService {



    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;

    }


    public Person get(int id) {
        return peopleRepository.findById(id).get();
    }

    public List<Person> listAll() {
        return peopleRepository.findAll();
    }
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Person person = peopleRepository.findByUsername(s);

        if (person == null)
            throw new UsernameNotFoundException("User not found");

        return new PersonDetails(person);
    }

    public void deletePersonById(int id) {
        peopleRepository.deleteById(id);
    }
}
