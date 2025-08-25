package ru.a2n.sfm.person.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@SuppressWarnings("java:S112")
@Service
public class HashService {

    private final PasswordEncoder passwordEncoder;

    public HashService(@Autowired PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String hash(String algorithm, String password) {
        try {
            if ("BCrypt".equals(algorithm)) {
                return passwordEncoder.encode(password);
            } else {
                MessageDigest md = MessageDigest.getInstance(algorithm);
                md.update(password.getBytes(StandardCharsets.UTF_8));
                return new String(md.digest(), StandardCharsets.UTF_8);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
