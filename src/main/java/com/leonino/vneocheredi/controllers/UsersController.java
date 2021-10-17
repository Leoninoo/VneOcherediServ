package com.leonino.vneocheredi.controllers;

import com.google.gson.Gson;
import com.leonino.vneocheredi.forms.LoginForm;
import com.leonino.vneocheredi.forms.UserForm;
import com.leonino.vneocheredi.models.Product;
import com.leonino.vneocheredi.models.Token;
import com.leonino.vneocheredi.models.User;
import com.leonino.vneocheredi.repositories.TokenRepository;
import com.leonino.vneocheredi.repositories.UsersRepository;
import com.leonino.vneocheredi.service.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    @ResponseBody
    public List<User> getUsers() {
        return usersRepository.findAll();
    }

    @PostMapping("/users")
    public ResponseEntity<Object> addUser() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/login")
    @ResponseBody
    public String authentication(@Param("token") String token) {
        return tokenRepository.getByToken(token).getUser().getLogin();
    }

    @PostMapping("/login")
    public String singIn(LoginForm form, @Param("request") String request) {
        String url = "";

        if(request != null) {
            url = request;
        }

        for(User user : usersRepository.findAll()) {
            if(user.getLogin().equals(form.getLogin())
            && new BCryptPasswordEncoder().matches(form.getPassword(), user.getPassword())) {
                String tokenString = TokenGenerator.generate(tokenRepository.findAll());
                Token token = Token.builder()
                        .token(tokenString)
                        .user(user)
                        .build();

                tokenRepository.save(token);

                return "redirect:https://vne-ocheredi.ru/" + url + "?token=" + tokenString;
            }
        }

        return "https://vne-ocheredi.ru/" + url + "?error=login";
    }

    @PostMapping("/loginAndroid")
    @ResponseBody
    public String singIn(@RequestBody String JSONObject) {
        LoginForm form = new Gson().fromJson(String.valueOf(JSONObject), LoginForm.class);
        if(singIn(form, null).endsWith("login"))
            return "login";
        else {
            return singIn(form, null).split("token=")[1];
        }
    }

    @PostMapping("/register")
    public String register(UserForm form, @Param("request") String request) {
        String url = "";

        if(request != null) {
            url = request;
        }

        if(!form.getPassword().equals(form.getConfirm()))
            return "redirect:https://vne-ocheredi.ru/" + url + "?error=confirm";

        if(usersRepository.findUserByLogin(form.getLogin()).isPresent())
            return "redirect:https://vne-ocheredi.ru/" + url + "?error=user";

        if(usersRepository.findUserByMail(form.getMail()).isPresent())
            return "redirect:https://vne-ocheredi.ru/" + url + "?error=mail";

        if(usersRepository.findUserByNumber(form.getNumber()).isPresent())
            return "redirect:https://vne-ocheredi.ru/" + url + "?error=number";

        User newUser = User.form(form);
        usersRepository.save(newUser);
        return "redirect:https://vne-ocheredi.ru/" + url;
    }

    @PostMapping("/registerAndroid")
    @ResponseBody
    public String register(@RequestBody String JSONObject) {
        UserForm form = new Gson().fromJson(String.valueOf(JSONObject), UserForm.class);
        String register = register(form, null);
        if (register.endsWith("confirm")) {
            return "confirm";
        } else if (register.endsWith("user")) {
            return "user";
        } else if (register.endsWith("mail")) {
            return "mail";
        } else if (register.endsWith("number")) {
            return "number";
        }
        return "login";
    }

    @GetMapping("/user")
    @ResponseBody
    public User getUser(@Param("token") String token) {
        User user = tokenRepository.getByToken(token).getUser();
        user.setTokens(new ArrayList<>());
        return user;
    }

}
