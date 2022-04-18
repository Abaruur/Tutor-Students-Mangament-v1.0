package com.teksystems.repository;

import com.teksystems.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Integer> {
    Optional<Tutor> findTutorByUsernameAndPassword(String username, String password);
    Optional<Tutor> findTutorByUsername(String username);
}
