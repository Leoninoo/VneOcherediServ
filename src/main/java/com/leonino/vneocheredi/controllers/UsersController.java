package com.leonino.vneocheredi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.leonino.vneocheredi.forms.LoginForm;
import com.leonino.vneocheredi.forms.UserForm;
import com.leonino.vneocheredi.models.Token;
import com.leonino.vneocheredi.models.User;
import com.leonino.vneocheredi.repositories.ProductsRepository;
import com.leonino.vneocheredi.repositories.TokenRepository;
import com.leonino.vneocheredi.repositories.UsersRepository;
import com.leonino.vneocheredi.service.TokenGenerator;
import com.leonino.vneocheredi.transfer.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * 25.04.2018
 * UsersController
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Controller
public class UsersController {
    private final UsersRepository usersRepository;
    private final TokenRepository tokenRepository;

    @Autowired
    public UsersController(UsersRepository usersRepository, TokenRepository tokenRepository) {
        this.usersRepository = usersRepository;
        this.tokenRepository = tokenRepository;
    }

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return Arrays.asList(
                new UserDto(0L, "leo"),
                new UserDto(1L, "and")
        );
    }

    @PostMapping("/users")
    public ResponseEntity<Object> addUser() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/login")
    @ResponseBody
    public String authentication(@Param("token") String token) {
        return tokenRepository.getByToken(token).getUser().getName();
    }

    @PostMapping("/login")
    public String singIn(LoginForm form) {
        Optional<User> user = usersRepository.findUserByLoginAndPassword(form.getLogin(), form.getPassword());

        if(user.isPresent()) {
            String tokenString = TokenGenerator.generate(tokenRepository.findAll());
            Token token = Token.builder()
                    .token(tokenString)
                    .user(user.get())
                    .build();

            tokenRepository.save(token);

            return "redirect:http://localhost/main.html?token=" + tokenString;
        }

        return "redirect:http://localhost/login.html";
    }

    @PostMapping("/register")
    public String register(UserForm form) {
        User newUser = User.form(form);
        usersRepository.save(newUser);
        return "redirect:http://localhost/login.html";
    }

    @GetMapping("/user")
    @ResponseBody
    public User getUser(@Param("token") String token) {
        User user = tokenRepository.getByToken(token).getUser();
        user.setTokens(new ArrayList<>());
        return user;
    }
}
