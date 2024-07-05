package ru.darek.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.darek.entities.User;
import ru.darek.errors.ErrorDto;
import ru.darek.services.UserCrudService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserCrudService userCrudService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    // GET http://localhost:8189/api/v1/users/test
    @GetMapping("/test")
    public String hello() {
        logger.info("Test for services");
        return "Test is OK!";
    }

    // GET http://localhost:8189/api/v1/users
    @GetMapping
    public List<User> getAllUsers() {
        logger.info("Request for all users");
        return userCrudService.findAll();
    }
    // GET http://localhost:8189/api/v1/users/2
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserDetails(@PathVariable long id) {
        logger.info("Request details for user with id = {}", id);
        Optional<User> user = userCrudService.getUser(id);
        if (!user.isPresent()) {
            ErrorDto errorDto = new ErrorDto("RESOURCE_NOT_FOUND","Пользователь c id = " + id + " не найден!");
            return new ResponseEntity<>(errorDto,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    // POST http://localhost:8189/api/v1/users
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createNewUser(@RequestHeader long userId, @RequestBody User createUser) {
      //  createUser.setId(userId);
        logger.info("Request for create user: {} -- {} -- {} - {}", createUser.getId(), createUser.getName(), createUser.getAge(), createUser.getSalary());
        User user = userCrudService.saveClient(createUser);
        if (user == null){
            ErrorDto errorDto = new ErrorDto("RESOURCE_NOT_CREATED","Пользователь не был создан!");
            return new ResponseEntity<>(errorDto,HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }
    // DELETE http://localhost:8189/api/v1/users
    @DeleteMapping
    public ResponseEntity<Void> deleteUserById(@RequestHeader long userId) {
        logger.info("Request delete for user with id = {}", userId);
        userCrudService.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
