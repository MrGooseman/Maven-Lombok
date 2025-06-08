package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private static PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
        // Инициализация тестовых данных (можно удалить в продакшене)
        initializeTestData();
    }

    private static void initializeTestData() {
        if (personRepository.count() == 0) {
            personRepository.saveAll(List.of(
                    new PersonEntity( "Oleg", 21, "olol@gmail.ru", "Moscow", LocalDateTime.now()),
                    new PersonEntity( "Gleb", 21, "gleb@gmail.ru", "Rom", LocalDateTime.now()),
                    new PersonEntity( "Olga", 21, "olga@gmail.ru", "Saransk", LocalDateTime.now()),
                    new PersonEntity( "Roma", 21, "roma@gmail.ru", "Moscow", LocalDateTime.now()),
                    new PersonEntity( "Chrome", 21, "chrome@gmail.ru", "Krasnodar", LocalDateTime.now())
            ));
        }
    }

    public static List<PersonEntity> getAllPersons()
    {
        return personRepository.findAll();
    }

    public static PersonEntity create(String userName, int age, String email, String city) {
        PersonEntity person = new PersonEntity();
        person.setUserName(userName);
        person.setAge(age);
        person.setEmail(email);
        person.setCity(city);
        person.setRegistrationDateTime(LocalDateTime.now());
        return personRepository.save(person);
    }

    public static Optional<PersonEntity> find(Long id) {
        return personRepository.findById(id);
    }

    public static boolean update(Long id, PersonEntity personDetails) {
        Optional<PersonEntity> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent()) {
            PersonEntity person = optionalPerson.get();
            person.setAge(personDetails.getAge());
            person.setCity(personDetails.getCity());
            person.setEmail(personDetails.getEmail());
            person.setUserName(personDetails.getUserName());
            person.setRegistrationDateTime(personDetails.getRegistrationDateTime());
            personRepository.save(person);
            return true;
        }
        return false;
    }

    public static boolean delete(Long id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public static List<PersonEntity> getByCity(String city) {
        return personRepository.findByCityIgnoreCase(city);
    }

    public static List<PersonEntity> getByAgeGreaterThanEqual(int age) {
        return personRepository.findByAgeGreaterThanEqual(age);
    }

    public static Optional<PersonEntity> getLatestRegistered() {
        return personRepository.findTopByOrderByRegistrationDateTimeDesc();
    }

    public static List<PersonEntity> getByUserNameContaining(String name) {
        return personRepository.findByUserNameContainingIgnoreCase(name);
    }
}