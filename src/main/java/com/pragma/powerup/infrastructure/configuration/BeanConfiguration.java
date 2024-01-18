package com.pragma.powerup.infrastructure.configuration;

import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.ICategoryPersistencePort;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;
import com.pragma.powerup.domain.usecase.DishUseCase;
import com.pragma.powerup.domain.usecase.RestaurantUseCase;
import com.pragma.powerup.infrastructure.feignclient.IUserFeignClient;
import com.pragma.powerup.infrastructure.out.jpa.adapter.CategoryJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.client.UserClient;
import com.pragma.powerup.infrastructure.out.jpa.adapter.DishJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.adapter.RestaurantJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.mapper.ICategoryMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.ICategoryRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IDishRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort(IRestaurantRepository restaurantRepository, IRestaurantEntityMapper restaurantEntityMapper, UserClient userClient){
        return new RestaurantJpaAdapter(restaurantRepository,restaurantEntityMapper,userClient);
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort(IRestaurantPersistencePort restaurantPersistencePort){
        return new RestaurantUseCase(restaurantPersistencePort);
    }

    @Bean
    public IDishPersistencePort dishPersistencePort(IDishRepository dishRepository, IDishEntityMapper dishEntityMapper){
        return new DishJpaAdapter(dishRepository,dishEntityMapper);
    }

    @Bean
    public IDishServicePort dishServicePort(IDishPersistencePort dishPersistencePort, IRestaurantPersistencePort restaurantPersistencePort, ICategoryPersistencePort categoryPersistencePort){
        return new DishUseCase(dishPersistencePort, restaurantPersistencePort, categoryPersistencePort);
    }

    @Bean
    public ICategoryPersistencePort categoryPersistencePort(ICategoryRepository categoryRepository, ICategoryMapper categoryMapper){
        return new CategoryJpaAdapter(categoryRepository,categoryMapper);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder,UserDetailsService userDetailsService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(IUserFeignClient userFeignClient){
        return username -> {
            UserModel userModel = userFeignClient.fetchUserModelByEmail(username);
            return new User(userModel.getEmail(), userModel.getPassword(), new ArrayList<>());
        };
    }

}