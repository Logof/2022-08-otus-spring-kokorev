package ru.otus.collectorio.payload.request;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotNull
    private String username;

    @NotNull
    private String password;
    private String firstName;
    private String lastName;
}
