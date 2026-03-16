package com.demo.demo.repository;

import com.demo.demo.model.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long> {
    Optional<Personne>findById(long id);
}
