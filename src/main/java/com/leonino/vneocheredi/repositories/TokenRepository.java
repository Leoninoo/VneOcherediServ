package com.leonino.vneocheredi.repositories;

import com.leonino.vneocheredi.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, String> {
    Token getByToken(String token);
}
