package com.example.demo;

import lombok.AllArgsConstructor;
import org.apache.coyote.Request;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;

@RestController
public class PersonController
{
    @GetMapping("/persons")
    public Collection<Person> getAll()
    {
        return PersonService.listOfPersons;
    }

    @PostMapping("/new_person")
    public ResponseEntity<?> createNew(String _usn, int _age, String _email, String _cty)
    {
        PersonService.create(_usn, _age, _email, _cty);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<Person> getById(@PathVariable(name = "id") Long _id)
    {
        Person tmp = PersonService.find(_id);
        return tmp != null ? new ResponseEntity<>(tmp, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") Long _id, @RequestBody Person person)
    {
        return PersonService.update(_id, person)?new ResponseEntity<>(HttpStatus.OK):new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/persons/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name="id") Long _id)
    {
        return PersonService.delete(_id)?new ResponseEntity<>(HttpStatus.OK):new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/persons/city/{name}")
    public ResponseEntity<Collection<Person>> getByCity(@PathVariable(name = "name") String _city)
    {
        return new ResponseEntity<>(PersonService.get(_city), HttpStatus.OK);
    }

    @GetMapping("/persons/age/{value}")
    public ResponseEntity<Collection<Person>> getOverAge(@PathVariable(name = "value") int _age)
    {
        return new ResponseEntity<>(PersonService.get(_age), HttpStatus.OK);
    }

    @GetMapping("/persons/date")
    public ResponseEntity<Person> getLastReg()
    {
        return new ResponseEntity<>(PersonService.get(), HttpStatus.OK);
    }

    @GetMapping("/persons/name/{name}")
    public ResponseEntity<Collection<Person>> getByName(@PathVariable(name="name") String _name)
    {
        return new ResponseEntity<>(PersonService.getNames(_name), HttpStatus.OK);
    }
}
