package com.pragma.powerup.infrastructure.feignclient;

import com.pragma.powerup.application.dto.request.AuthRequest;
import com.pragma.powerup.application.dto.response.AuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "auth-service", url = "${microservice.authServiceUrl}$")
public interface IAuthFeignClient {

    @PostMapping("login")
    AuthResponse login(@RequestBody AuthRequest authRequest);

}