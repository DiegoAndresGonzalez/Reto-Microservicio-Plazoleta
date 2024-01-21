package com.pragma.powerup.infrastructure.security.auth;

import com.pragma.powerup.application.dto.request.AuthRequest;
import com.pragma.powerup.application.dto.response.AuthResponse;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.infrastructure.feignclient.IUserFeignClient;
import com.pragma.powerup.infrastructure.security.jwt.JwtService;
import com.pragma.powerup.infrastructure.security.jwt.TokenHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final IUserFeignClient userFeignClient;
    private final JwtService jwtService;

    public AuthResponse authentication(AuthRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),
                        request.getPassword())
        );
        String token = TokenHolder.getToken();
        UserModel authUser = userFeignClient.fetchUserModelByEmail(token, request.getEmail());
        String role = authUser.getRole().getRole();
        String jwtToken = jwtService.generateTokenWithRole(new CustomUserDetails(authUser) {
        },role);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}