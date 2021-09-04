package com.leonino.vneocheredi.models;

import com.leonino.vneocheredi.forms.UserForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String password;
    private String name;
    private String number;
    private String mail;

    @OneToMany
    private List<Token> tokens;

    public static User form(UserForm form){
        return User.builder()
                .login(form.getLogin())
                .mail(form.getMail())
                .name(form.getName())
                .password(form.getPassword())
                .number("+79990637091")
                .build();
    }
}
