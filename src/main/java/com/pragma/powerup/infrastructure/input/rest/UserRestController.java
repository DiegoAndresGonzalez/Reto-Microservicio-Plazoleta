package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.*;
import com.pragma.powerup.application.dto.response.ClientMenuResponseDto;
import com.pragma.powerup.application.dto.response.ClientRestaurantResponseDto;
import com.pragma.powerup.application.handler.IDishHandler;
import com.pragma.powerup.application.handler.IOrderHandler;
import com.pragma.powerup.application.handler.IRestaurantHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.pragma.powerup.domain.utils.Constant.*;

@RestController
@RequestMapping("api/user/")
@RequiredArgsConstructor
public class UserRestController {

    private final IDishHandler dishHandler;
    private final IRestaurantHandler restaurantHandler;
    private final IOrderHandler orderHandler;


    @Operation(summary = "Create a new dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Dish created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Dish already exists", content = @Content)
    })
    @PostMapping("dish")
    @PreAuthorize("hasRole('"+OWNER_ROLE+"')")
    public ResponseEntity<Void> createDish(@RequestBody CreateDishRequestDto createDishRequestDto){
        dishHandler.createDish(createDishRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PatchMapping("dish")
    @PreAuthorize("hasRole('"+OWNER_ROLE+"')")
    public ResponseEntity<Void> updateDish(@RequestBody UpdateDishRequestDto updateDishRequestDto){
        dishHandler.updateDish(updateDishRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("dish/status")
    @PreAuthorize("hasRole('"+OWNER_ROLE+"')")
    public ResponseEntity<Void> updateDishStatus(@RequestBody UpdateDishStatusDto updateDishStatusDto){
        dishHandler.updateDishStatus(updateDishStatusDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Create a new restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Owner created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Owner already exists", content = @Content)
    })
    @PostMapping("create/restaurant")
    @PreAuthorize("hasRole('"+ADMIN_ROLE+"')")
    public ResponseEntity<Void> createRestaurant(@RequestBody RestaurantRequestDto restaurantRequestDto){
        restaurantHandler.createRestaurant(restaurantRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("client/restaurant")
    @PreAuthorize("hasRole('"+CLIENT_ROLE+"')")
    public ResponseEntity<Page<ClientRestaurantResponseDto>> getAllRestaurantsPaginated(@RequestParam Integer page, @RequestParam Integer size){
        Page<ClientRestaurantResponseDto> restaurantResponseDto = restaurantHandler.getAllRestaurantsPaginated(page,size);
        return new ResponseEntity<>(restaurantResponseDto,HttpStatus.OK);
    }

    @GetMapping("client/dish")
    @PreAuthorize("hasRole('"+CLIENT_ROLE+"')")
    public ResponseEntity<Page<ClientMenuResponseDto>> getAllDishesPaginated(@RequestParam String category, @RequestParam Integer page, @RequestParam Integer size){
        Page<ClientMenuResponseDto> menuResponseDto = dishHandler.getAllDishesPaginated(category,page,size);
        return new ResponseEntity<>(menuResponseDto, HttpStatus.OK);
    }

    @PostMapping("client/order")
    @PreAuthorize("hasRole('"+CLIENT_ROLE+"')")
    public ResponseEntity<Void> dishOrderRequest(@RequestBody OrderRequestDto orderRequestDto){
        orderHandler.requestOrder(orderRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
