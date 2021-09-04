package com.leonino.vneocheredi.forms;

import lombok.Data;

@Data
public class LoginForm {
    private String login;
    private String password;
    private boolean keep;
}
