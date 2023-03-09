package ru.volkova.springcourse.RouteManagerRest.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.volkova.springcourse.RouteManagerRest.dto.CustomerDTO;
import ru.volkova.springcourse.RouteManagerRest.dto.CustomersResponse;
import ru.volkova.springcourse.RouteManagerRest.services.CustomersService;
import ru.volkova.springcourse.RouteManagerRest.util.converters.CustomerConverter;
import ru.volkova.springcourse.RouteManagerRest.util.exceptions.CustomerErrorResponse;
import ru.volkova.springcourse.RouteManagerRest.util.exceptions.CustomerNotCreatedException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomersService customersService;
    private final CustomerConverter customerConverter;

    @Autowired
    public CustomerController(CustomersService customersService, CustomerConverter customerConverter) {
        this.customersService = customersService;
        this.customerConverter = customerConverter;
    }

    @GetMapping
    public CustomersResponse getCustomers(){

        return new CustomersResponse(customersService.findAll().stream().map(customerConverter::convertToCustomerDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomer(@PathVariable("id") int id){

        return customerConverter.convertToCustomerDTO(customersService.findOne(id));
    }

    @GetMapping("/getByName/{name}")
    public CustomersResponse getCustomerByNameLike(@PathVariable("name") String name){
        return new CustomersResponse(customersService.findByNameContaining(name).stream()
                .map(customerConverter::convertToCustomerDTO).collect(Collectors.toList()));
    }


    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid CustomerDTO customerDTO,
                                             BindingResult bindingResult){
    if (bindingResult.hasErrors()){
        StringBuilder errorMessage = new StringBuilder();
        List<FieldError> errors  = bindingResult.getFieldErrors();
        for (FieldError error : errors){
            errorMessage.append(error.getField())
                    .append(" - ").append(error.getDefaultMessage())
                    .append("; ");
        }
        throw new CustomerNotCreatedException(errorMessage.toString());
    }
    customersService.save(customerConverter.convertToCustomer(customerDTO));
    return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<CustomerErrorResponse> handleException(CustomerNotCreatedException ex){
        CustomerErrorResponse response = new CustomerErrorResponse(
                ex.getMessage(), System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
