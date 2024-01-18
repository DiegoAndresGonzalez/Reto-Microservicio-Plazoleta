package com.pragma.powerup.infrastructure.security;

import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.infrastructure.feignclient.IUserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CustomDetailsService implements UserDetailsService {

    private final IUserFeignClient userFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userFeignClient.fetchUserModelByEmail(username);
        return new User(userModel.getEmail(),userModel.getPassword(),new ArrayList<>());
    }
}