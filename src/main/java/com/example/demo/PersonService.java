package com.example.demo;

import java.io.Console;
import java.lang.ref.WeakReference;
import java.time.LocalDateTime;
import java.util.*;

public class PersonService
{
    public static Collection<Person> listOfPersons;
    PersonService()
    {
        listOfPersons = new LinkedList<>();
        listOfPersons.add(new Person(1L, "Oleg", 21, "olol@gmail.ru", "Moscow", LocalDateTime.now()));
        listOfPersons.add(new Person(2L, "Gleb", 21, "gleb@gmail.ru", "Rom", LocalDateTime.now()));
        listOfPersons.add(new Person(3L, "Olga", 21, "olga@gmail.ru", "Saransk", LocalDateTime.now()));
        listOfPersons.add(new Person(4L, "Roma", 21, "roma@gmail.ru", "Moscow", LocalDateTime.now()));
        listOfPersons.add(new Person(5L, "Chrome", 21, "chrome@gmail.ru", "Krasnodar", LocalDateTime.now()));
    }
    public static void create(String _usn, int _age, String _email, String _cty)
    {
        PersonService.listOfPersons.add(new Person((long) PersonService.listOfPersons.size(),_usn, _age, _email, _cty, LocalDateTime.now()));
    }
    public static Person find(Long _id)
    {
        return listOfPersons.stream().filter(p-> Objects.equals(p.getId(), _id)).findFirst().get();
    }
    public static boolean update(Long _id, Person person)
    {
        try
        {
            listOfPersons.stream().filter(p-> Objects.equals(p.getId(), _id)).findFirst().get().setAge(person.getAge());
            listOfPersons.stream().filter(p-> Objects.equals(p.getId(), _id)).findFirst().get().setCity(person.getCity());
            listOfPersons.stream().filter(p-> Objects.equals(p.getId(), _id)).findFirst().get().setEmail(person.getEmail());
            listOfPersons.stream().filter(p-> Objects.equals(p.getId(), _id)).findFirst().get().setUserName(person.getUserName());
            listOfPersons.stream().filter(p-> Objects.equals(p.getId(), _id)).findFirst().get().setRegistrationDateTime(person.getRegistrationDateTime());
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }
    public static boolean delete(Long _id)
    {
        try
        {
            listOfPersons.remove(listOfPersons.stream().filter(p-> Objects.equals(p.getId(), _id)).findFirst().get());
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }
    public static Collection<Person> get(String _city)
    {
        return listOfPersons.stream().filter(p->p.getCity().equalsIgnoreCase(_city)).toList();
    }
    public static Collection<Person> get(int _age)
    {
        return listOfPersons.stream().filter(p->p.getAge()>=_age).toList();
    }
    public static Person get()
    {
        listOfPersons.stream().sorted(Comparator.comparing(Person::getRegistrationDateTime).reversed());
        return listOfPersons.stream().findFirst().get();
    }
    public static Collection<Person> getNames(String _name)
    {
        return listOfPersons.stream().filter(p->p.getUserName().toLowerCase().contains(_name)).toList();
    }
}
