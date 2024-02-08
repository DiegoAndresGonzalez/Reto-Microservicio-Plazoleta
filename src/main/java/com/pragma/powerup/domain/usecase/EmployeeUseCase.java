package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IEmployeeServicePort;
import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.domain.spi.IEmployeePersistencePort;
import com.pragma.powerup.domain.utils.OrderStatus;
import org.springframework.data.domain.Page;

public class EmployeeUseCase implements IEmployeeServicePort {

    private final IEmployeePersistencePort employeePersistencePort;

    public EmployeeUseCase(IEmployeePersistencePort employeePersistencePort) {
        this.employeePersistencePort = employeePersistencePort;
    }

    @Override
    public Page<OrderModel> getOrderListPaginated(Integer page, Integer size, OrderStatus status){
        return employeePersistencePort.getOrderListPaginated(page,size,status);
    }
}