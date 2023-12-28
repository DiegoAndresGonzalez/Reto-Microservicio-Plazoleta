package com.pragma.powerup.infrastructure.client;

import com.pragma.powerup.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import static com.pragma.powerup.domain.utils.Constant.PROPRIETARY_ROLE;

@Component
@RequiredArgsConstructor
public class UserClient {

    private final RestTemplate restTemplate;

    @Value("${microservice.userServiceUrl}")
    private String serviceUrl;

    public UserModel fetchUserModel(Long userId){
        String url = serviceUrl + userId;
        ResponseEntity<UserModel> response = restTemplate.getForEntity(url, UserModel.class);
        return response.getBody();
    }

    public void validateProprietary(UserModel responseModel){
        if (!PROPRIETARY_ROLE.equals(responseModel.getRole().getRole())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El usuario no tiene el rol propietario.");
        }
    }

}