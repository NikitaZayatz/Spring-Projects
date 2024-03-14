package ru.vadim.spingapp.FirstSecurityApp.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vadim.spingapp.FirstSecurityApp.models.Spa;

import java.util.List;

@Repository
public interface SpaRepository extends JpaRepository<Spa,Integer> {
    @Override
    List<Spa> findAll();
}
