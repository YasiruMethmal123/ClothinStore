package edu.icet.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString


public class User {
    private String email;
    private String name;
    private String password;
    private String OTP;
    private String userId;
}
