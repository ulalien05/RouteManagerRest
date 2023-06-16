package ru.volkova.springcourse.RouteManagerRest.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.volkova.springcourse.RouteManagerRest.dto.CustomerDTO;
import ru.volkova.springcourse.RouteManagerRest.dto.CustomersResponse;
import ru.volkova.springcourse.RouteManagerRest.services.CustomersService;
import ru.volkova.springcourse.RouteManagerRest.util.converters.CustomerConverter;
import ru.volkova.springcourse.RouteManagerRest.util.exceptions.CustomerErrorResponse;
import ru.volkova.springcourse.RouteManagerRest.util.exceptions.NotCreatedException;

import java.util.stream.Collectors;

import static ru.volkova.springcourse.RouteManagerRest.util.ErrorUtil.returnErrorInfo;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomersService customersService;
    private final CustomerConverter customerConverter;

    @GetMapping
    public CustomersResponse getCustomers(){

        return new CustomersResponse(customersService.findAll()
                .stream()
                .map(customerConverter::convertToCustomerDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomer(@PathVariable("id") int id){

        return customerConverter.convertToCustomerDTO(customersService.findOne(id));
    }

    @GetMapping("/getByName/{name}")
    public CustomersResponse getCustomerByNameLike(@PathVariable("name") String name){
        return new CustomersResponse(customersService.findByNameContaining(name)
                .stream()
                .map(customerConverter::convertToCustomerDTO)
                .collect(Collectors.toList()));
    }


    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid CustomerDTO customerDTO,
                                             BindingResult bindingResult){
    if (bindingResult.hasErrors()){
        returnErrorInfo(bindingResult);
    }
    customersService.save(customerConverter.convertToCustomer(customerDTO));
    return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<CustomerErrorResponse> handleException(NotCreatedException ex){
        CustomerErrorResponse response = new CustomerErrorResponse(
                ex.getMessage(), System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
