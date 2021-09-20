package com.leonino.vneocheredi.repositories;

import com.leonino.vneocheredi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByLogin(String login);
    Optional<User> findUserByMail(String mail);
}
