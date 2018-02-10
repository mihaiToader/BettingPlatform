package com.mt.bettingPlatform.domain.dto;

/**
 * Created by mtoader on 7/13/2017.
 */
public class UserDto {
    private String name;
    private String password;
    private String repeatPassword;

    public UserDto() {
        name = "";
        password = "";
        repeatPassword = "";
    }

    public UserDto(String name, String password, String repeatPassword) {
        this.name = name;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
