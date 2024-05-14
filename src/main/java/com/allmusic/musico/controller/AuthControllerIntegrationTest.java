package com.allmusic.musico.controller;

import com.allmusic.musico.model.User;
import com.allmusic.musico.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSignup() {
        String url = "http://localhost:" + port + "/auth/signup";

        User user = new User();
        user.setUsername("integrationtestuser");
        user.setPassword("password");

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<User> request = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User registered successfully", response.getBody());

        User savedUser = userRepository.findByUsername("integrationtestuser");
        assertEquals("integrationtestuser", savedUser.getUsername());
    }

    @Test
    public void testSignupUserExists() {
        String url = "http://localhost:" + port + "/auth/signup";

        User user = new User();
        user.setUsername("existingintegrationuser");
        user.setPassword("password");

        userRepository.save(user); // Pre-save a user

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<User> request = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User already exists", response.getBody());
    }
}
