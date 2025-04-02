package com.thezz9.schedulerjpa.common.config;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

    private static final int COST = 12;

    public String encode(String rawPassword) {
        return BCrypt.withDefaults().hashToString(COST, rawPassword.toCharArray());
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword);
        return !result.verified;
    }

}
