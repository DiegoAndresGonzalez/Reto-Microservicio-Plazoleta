package com.pragma.powerup.infrastructure.out.jpa.client;

import com.pragma.powerup.domain.exception.InvalidInputException;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.infrastructure.exception.InvalidRoleException;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.feignclient.IUserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.pragma.powerup.domain.utils.Constant.OWNER_ROLE;

@Component
@RequiredArgsConstructor
public class UserClient {

    private final IUserFeignClient userServiceClient;

    public UserModel fetchUserModel(String token, Long id){
        return userServiceClient.fetchUserModel(token,id);
    }

    public void validateOwner(UserModel responseModel){
        if (responseModel == null){
            throw new NoDataFoundException("No se ha encontrado un usuario que corresponda con la id ingresada.");
        } else if (!OWNER_ROLE.equals(responseModel.getRole().getRole())){
            throw new InvalidRoleException("El usuario no tiene el rol propietario.");
        }
    }

    public void validateOwnership(String token, RestaurantModel restaurantModel){
        UserModel ownerModel = userServiceClient.fetchUser(token);
        if (!restaurantModel.getIdOwner().equals(ownerModel.getId())){
            throw new InvalidInputException("No puedes modificar platos de un restaurante que" +
                    " no te pertenezca.");
        }
    }

}