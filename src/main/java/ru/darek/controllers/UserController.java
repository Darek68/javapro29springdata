package ru.darek.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.darek.entities.User;
import ru.darek.services.UserCrudService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserCrudService userCrudService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class.getName());

    // GET http://localhost:8189/api/v1/users/hello
    @GetMapping("/hello")
    public String hello() {
        logger.info("Request hello!!!");
        return "Hello word";
    }

    // GET http://localhost:8189/api/v1/users
    @GetMapping
    public List<User> getAllUsers() {
        logger.info("Request for all users");
        return userCrudService.findAll();
    }
    // GET http://localhost:8189/api/v1/users/2
    @GetMapping("/{id}")
    public User getUserDetails(@PathVariable long id) {
        logger.info("Request details for user with id = {}", id);
        return userCrudService.getUser(id).orElse(null);
    }
    // POST http://localhost:8189/api/v1/users
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createNewUser(@RequestHeader long userId, @RequestBody User createUser) {
        createUser.setId(userId);
        logger.info("Request for create user: {} -- {} -- {} - {}", createUser.getId(), createUser.getName(), createUser.getAge(), createUser.getSalary());
        return userCrudService.saveClient(createUser);
    }
    // DELETE http://localhost:8189/api/v1/users
    @DeleteMapping
    public List<User> deleteUserById(@RequestHeader long userId) {
        logger.info("Request delete for user with id = {}", userId);
        userCrudService.deleteUserById(userId);
        return userCrudService.findAll();
    }
}
