package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonController
{
    @GetMapping("/persons")
    public List<PersonEntity> getAllUsers()
    {
        return PersonService.getAllPersons();
    }

    @PostMapping("/persons/new")
    public ResponseEntity<?> createNew(String _usn, int _age, String _email, String _cty)
    {
        PersonService.create(_usn, _age, _email, _cty);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<Optional<PersonEntity>> getById(@PathVariable(name = "id") Long _id)
    {
        Optional<PersonEntity> tmp = PersonService.find(_id);
        return tmp.isPresent() ? new ResponseEntity<>(tmp, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/persons/update/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") Long _id, @RequestBody PersonEntity person)
    {
        return PersonService.update(_id, person)?new ResponseEntity<>(HttpStatus.OK):new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/persons/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name="id") Long _id)
    {
        return PersonService.delete(_id)?new ResponseEntity<>(HttpStatus.OK):new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/persons/city/{name}")
    public ResponseEntity<List<PersonEntity>> getByCity(@PathVariable(name = "name") String _city)
    {
        return new ResponseEntity<>(PersonService.getByCity(_city), HttpStatus.OK);
    }

    @GetMapping("/persons/age/{value}")
    public ResponseEntity<List<PersonEntity>> getOverAge(@PathVariable(name = "value") int _age)
    {
        return new ResponseEntity<>(PersonService.getByAgeGreaterThanEqual(_age), HttpStatus.OK);
    }

    @GetMapping("/persons/date")
    public ResponseEntity<Optional<PersonEntity>> getLastReg()
    {
        return new ResponseEntity<>(PersonService.getLatestRegistered(), HttpStatus.OK);
    }

    @GetMapping("/persons/name/{name}")
    public ResponseEntity<List<PersonEntity>> getByName(@PathVariable(name="name") String _name)
    {
        return new ResponseEntity<>(PersonService.getByUserNameContaining(_name), HttpStatus.OK);
    }
}
