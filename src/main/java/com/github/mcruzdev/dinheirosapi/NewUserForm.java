package com.github.mcruzdev.dinheirosapi;

import javax.validation.constraints.NotBlank;

public class NewUserForm {

    @NotBlank
    private String name;

    public NewUserForm() {
    }

    public NewUserForm(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public User toUser() {
        return new User(name);
    }
}
