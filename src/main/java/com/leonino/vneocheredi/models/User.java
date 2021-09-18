package com.leonino.vneocheredi.models;

import com.leonino.vneocheredi.forms.UserForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "public")
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
                .password(new BCryptPasswordEncoder().encode(form.getPassword()))
                .number("+79990637091")
                .build();
    }
}
