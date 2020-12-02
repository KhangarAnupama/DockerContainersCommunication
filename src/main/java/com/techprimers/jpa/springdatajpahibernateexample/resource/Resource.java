package com.techprimers.jpa.springdatajpahibernateexample.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/all")
public class Resource {

    private UserRepository usersRepository;

    public Resource(UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    @GetMapping("/")
    public List<Users> all() {
        return usersRepository.findAll();
    }


    @GetMapping("/create")
    public List<Users> users() {
        Users users = new Users();
        users.setId(1);
        users.setName("Sam");
        users.setSalary(3400);
        users.setTeamName("Development");

        usersRepository.save(users);

        return usersRepository.findAll();
    }
    
    @GetMapping("/foo")
    public List<Users> usersFoo() {
        Users users = new Users();
        users.setId(2);
        users.setName("Anupama");
        users.setSalary(1000);
        users.setTeamName("Software developer");

        usersRepository.save(users);

        return usersRepository.findAll();
    }
}
