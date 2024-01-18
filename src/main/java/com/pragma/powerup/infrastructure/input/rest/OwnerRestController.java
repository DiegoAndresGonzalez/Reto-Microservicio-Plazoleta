package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.CreateDishRequestDto;
import com.pragma.powerup.application.dto.request.UpdateDishRequestDto;
import com.pragma.powerup.application.handler.IDishHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.pragma.powerup.domain.utils.Constant.OWNER_ROLE;

@RestController
@RequestMapping("api/owner/")
@RequiredArgsConstructor
public class OwnerRestController {

    private final IDishHandler dishHandler;

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


}