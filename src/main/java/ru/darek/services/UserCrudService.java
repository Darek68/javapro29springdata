package ru.darek.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.darek.entities.User;
import ru.darek.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * C - create
 * R - read
 * U - update
 * D - delete
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserCrudService {

    private final UserRepository userRepository;
    @Transactional
    public User saveClient(User user) {
        log.info("before saved user: {}", user);
        User savedCUser = userRepository.save(user);
        log.info("after saved user: {}", savedCUser);
        return savedCUser;
    }
    @Transactional(readOnly = true)
    public Optional<User> getUser(long id) {
        Optional<User> user = userRepository.findById(id);
        log.info("user: {}", user);
        return user;
    }
    @Transactional(readOnly = true)
    public List<User> findAll() {
        List<User> users = StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        log.info("users:{}", users);
        return users;
    }
    public void deleteUserById(long id){userRepository.deleteById(id);
    }
}
