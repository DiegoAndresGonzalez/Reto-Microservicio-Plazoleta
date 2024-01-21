package com.pragma.powerup.infrastructure.security.jwt;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenHolder {
    private static final ThreadLocal<String> jwtHolder = new ThreadLocal<>();
    private static final ThreadLocal<String> usernameHolder = new ThreadLocal<>();

    public static void setToken(String token) {
        jwtHolder.set(token);
    }

    public static void setUsername(String username){
        usernameHolder.set(username);
    }

    public static String getToken() {
        return jwtHolder.get();
    }

    public static String getUsername(){
        return usernameHolder.get();
    }
    public static void clear() {
        jwtHolder.remove();
    }

    public static void clearUsername(){
        usernameHolder.remove();
    }

}