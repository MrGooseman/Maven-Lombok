package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    List<PersonEntity> findByCityIgnoreCase(String city);
    List<PersonEntity> findByAgeGreaterThanEqual(int age);
    Optional<PersonEntity> findTopByOrderByRegistrationDateTimeDesc();
    List<PersonEntity> findByUserNameContainingIgnoreCase(String name);
}