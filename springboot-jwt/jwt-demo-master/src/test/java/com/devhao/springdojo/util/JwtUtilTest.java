package com.devhao.springdojo.util;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class JwtUtilTest {
    private JwtUtil jwtUtil = new JwtUtil();

    @Test
    public void shouldParseToken() {
        String payload = "test";
        String token = jwtUtil.generateToken(payload);

        assertThat(jwtUtil.parseToken(token), is(payload));
    }

    @Test
    public void shouldValidateValidToken() {
        String payload = "test";
        String token = jwtUtil.generateToken(payload);

        assertThat(jwtUtil.isTokenValid(token), is(true));
    }

    @Test
    public void shouldValidateInvalidToken() {
        String payload = "test";
        String token = jwtUtil.generateToken(payload) + "hack";

        assertThat(jwtUtil.isTokenValid(token), is(false));
    }
}