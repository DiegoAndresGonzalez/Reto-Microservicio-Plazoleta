package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.response.ClientRestaurantResponseDto;
import com.pragma.powerup.domain.model.RestaurantModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantResponseMapper {

    ClientRestaurantResponseDto toClientRestaurantResponse(RestaurantModel restaurantModel);

}