package com.pragma.powerup.infrastructure.feignclient;

import com.pragma.powerup.domain.model.UserModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "userServiceClient", url = "${microservice.userServiceUrl}")
public interface IUserFeignClient {

    @GetMapping("{id}")
    UserModel fetchUserModel(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id);

    @GetMapping("data")
    UserModel fetchUser(@RequestHeader("Authorization") String authorization);
}