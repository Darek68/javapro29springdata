package ru.darek.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.darek.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
}