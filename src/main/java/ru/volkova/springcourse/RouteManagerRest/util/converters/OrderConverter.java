package ru.volkova.springcourse.RouteManagerRest.util.converters;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.volkova.springcourse.RouteManagerRest.dto.CustomerDTO;
import ru.volkova.springcourse.RouteManagerRest.dto.OrderDTO;
import ru.volkova.springcourse.RouteManagerRest.models.Customer;
import ru.volkova.springcourse.RouteManagerRest.models.Order;

@Component
public class OrderConverter {
    private final ModelMapper modelMapper;

    public OrderConverter(){
        this.modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Order.class, OrderDTO.class);
        modelMapper.createTypeMap(Customer.class, CustomerDTO.class);
    }

    public OrderDTO convertToOrderDTO(Order order){
        return modelMapper.map(order, OrderDTO.class);
    }
    public Order convertToOrder(OrderDTO orderDTO){
        return modelMapper.map(orderDTO, Order.class);
    }
}
