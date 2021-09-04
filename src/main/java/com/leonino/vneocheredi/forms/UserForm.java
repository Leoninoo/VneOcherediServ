package com.leonino.vneocheredi.forms;

import lombok.Data;

@Data
public class UserForm {
    private String login;
    private String password;
    private String confirmPassword;
    private String name;
    private String mail;
}
