package ru.volkova.springcourse.RouteManagerRest.util.converters;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.volkova.springcourse.RouteManagerRest.dto.CustomerDTO;
import ru.volkova.springcourse.RouteManagerRest.models.Customer;

@Component
public class CustomerConverter {
    private final ModelMapper modelMapper;

    public CustomerConverter(){
        this.modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Customer.class, CustomerDTO.class);
    }

    public CustomerDTO convertToCustomerDTO(Customer customer){
        return modelMapper.map(customer, CustomerDTO.class);
    }
    public Customer convertToCustomer(CustomerDTO customerDTO){
        return modelMapper.map(customerDTO, Customer.class);
    }
}
