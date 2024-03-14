package ru.vadim.spingapp.FirstSecurityApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vadim.spingapp.FirstSecurityApp.models.Attraction;


@Repository
public interface AttractionRepository extends JpaRepository<Attraction,Integer> {
}
