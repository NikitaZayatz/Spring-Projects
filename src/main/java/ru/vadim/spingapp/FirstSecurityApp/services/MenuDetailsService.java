package ru.vadim.spingapp.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vadim.spingapp.FirstSecurityApp.models.Menu;
import ru.vadim.spingapp.FirstSecurityApp.repositories.MenuRepository;

import java.util.List;

@Service
public class MenuDetailsService implements UserDetailsService {

    private final MenuRepository menuRepository;

    @Autowired
    public MenuDetailsService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }


    public List<Menu> listAll() {
        return menuRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

    public Menu get(int id) {
        return menuRepository.findById(id).get();
    }
    public void deleteMenuById(int id) {
        menuRepository.deleteById(id);
    }
}