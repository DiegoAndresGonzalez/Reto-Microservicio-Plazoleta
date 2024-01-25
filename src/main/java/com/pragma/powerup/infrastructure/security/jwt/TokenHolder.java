package com.pragma.powerup.infrastructure.security.jwt;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenHolder {
    private static final ThreadLocal<String> jwtHolderBearer = new ThreadLocal<>();

    public static void setBearer(String token){
        jwtHolderBearer.set(token);
    }
    public static String getBearer(){
        return jwtHolderBearer.get();
    }
    public static String getToken() {
        return jwtHolderBearer.get();
    }

    public static void clearBearer() {
        jwtHolderBearer.remove();
    }


}