package com.pragma.powerup.infrastructure.out.jpa.client;

import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.infrastructure.exception.InvalidRoleException;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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
        if (responseModel == null){
            throw new NoDataFoundException("No se ha encontrado un usuario que corresponda con la id ingresada.");
        } else if (!PROPRIETARY_ROLE.equals(responseModel.getRole().getRole())){
            throw new InvalidRoleException("El usuario no tiene el rol propietario.");
        }
    }

}