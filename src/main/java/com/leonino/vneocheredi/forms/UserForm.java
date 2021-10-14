package com.leonino.vneocheredi.forms;

import lombok.Data;

@Data
public class UserForm {
    private String login;
    private String password;
    private String confirm;
    private String number;
    private String mail;
}
