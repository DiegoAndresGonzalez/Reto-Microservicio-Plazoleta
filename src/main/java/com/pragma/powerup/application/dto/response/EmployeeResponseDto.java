package com.pragma.powerup.application.dto.response;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmployeeResponseDto {

    private List<OrderResponseDto> orderModels;

}