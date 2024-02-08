package com.pragma.powerup.infrastructure.configuration;

import com.pragma.powerup.domain.api.IClientServicePort;
import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.api.IEmployeeServicePort;
import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.*;
import com.pragma.powerup.domain.usecase.ClientUseCase;
import com.pragma.powerup.domain.usecase.DishUseCase;
import com.pragma.powerup.domain.usecase.EmployeeUseCase;
import com.pragma.powerup.domain.usecase.RestaurantUseCase;
import com.pragma.powerup.infrastructure.feignclient.IUserFeignClient;
import com.pragma.powerup.infrastructure.out.jpa.adapter.*;
import com.pragma.powerup.infrastructure.out.jpa.client.UserClient;
import com.pragma.powerup.infrastructure.out.jpa.mapper.*;
import com.pragma.powerup.infrastructure.out.jpa.repository.*;
import com.pragma.powerup.infrastructure.security.auth.CustomUserDetails;
import com.pragma.powerup.infrastructure.security.jwt.TokenHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserFeignClient feignClient;

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort(IRestaurantRepository restaurantRepository, IRestaurantEntityMapper restaurantEntityMapper, UserClient userClient, IEmployeeRestaurantRepository employeeRestaurantRepository, IEmployeeRestaurantEntityMapper employeeRestaurantEntityMapper){
        return new RestaurantJpaAdapter(restaurantRepository,restaurantEntityMapper,userClient,employeeRestaurantRepository,employeeRestaurantEntityMapper);
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort(IRestaurantPersistencePort restaurantPersistencePort){
        return new RestaurantUseCase(restaurantPersistencePort);
    }

    @Bean
    public IDishPersistencePort dishPersistencePort(IDishRepository dishRepository, IDishEntityMapper dishEntityMapper, IRestaurantPersistencePort restaurantPersistencePort, UserClient userClient){
        return new DishJpaAdapter(dishRepository,dishEntityMapper,restaurantPersistencePort,userClient);
    }

    @Bean
    public IDishServicePort dishServicePort(IDishPersistencePort dishPersistencePort, IRestaurantPersistencePort restaurantPersistencePort, ICategoryPersistencePort categoryPersistencePort){
        return new DishUseCase(dishPersistencePort, restaurantPersistencePort, categoryPersistencePort);
    }

    @Bean
    public IEmployeePersistencePort employeePersistencePort(IOrderRepository orderRepository,IOrderEntityMapper orderEntityMapper,IDishOrderRepository dishOrderRepository){
        return new EmployeeJpaAdapter(orderRepository,orderEntityMapper,dishOrderRepository);
    }

    @Bean
    public IEmployeeServicePort employeeServicePort(IEmployeePersistencePort employeePersistencePort){
        return new EmployeeUseCase(employeePersistencePort);
    }

    @Bean
    public ICategoryPersistencePort categoryPersistencePort(ICategoryRepository categoryRepository, ICategoryEntityMapper categoryMapper){
        return new CategoryJpaAdapter(categoryRepository,categoryMapper);
    }

    @Bean
    public IClientPersistencePort clientPersistencePort(IOrderRepository orderRepository, IOrderEntityMapper orderEntityMapper, IDishOrderRepository dishOrderRepository, IDishRepository dishRepository,
                                                        IUserFeignClient userFeignClient){
        return new ClientJpaAdapter(orderEntityMapper,orderRepository,dishOrderRepository,dishRepository,userFeignClient);
    }

    @Bean
    public IClientServicePort clientServicePort(IClientPersistencePort clientPersistencePort,IDishPersistencePort dishPersistencePort,IRestaurantPersistencePort restaurantPersistencePort){
        return new ClientUseCase(clientPersistencePort,dishPersistencePort,restaurantPersistencePort);
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> {
            UserModel userModel = feignClient.fetchUser(TokenHolder.getBearer());
            return new CustomUserDetails(userModel);
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception{
        return authConfig.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}