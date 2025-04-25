package com.sashmitha.library.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupRequest {
    private String name;
    private String email;
    private String password;

}



