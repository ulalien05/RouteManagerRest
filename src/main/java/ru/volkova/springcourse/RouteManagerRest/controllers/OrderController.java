package ru.volkova.springcourse.RouteManagerRest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.volkova.springcourse.RouteManagerRest.dto.OrderDTO;
import ru.volkova.springcourse.RouteManagerRest.dto.OrdersResponse;
import ru.volkova.springcourse.RouteManagerRest.models.Order;
import ru.volkova.springcourse.RouteManagerRest.services.CustomersService;
import ru.volkova.springcourse.RouteManagerRest.services.OrdersService;
import ru.volkova.springcourse.RouteManagerRest.util.converters.OrderConverter;
import ru.volkova.springcourse.RouteManagerRest.util.exceptions.CustomerNotFoundException;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static ru.volkova.springcourse.RouteManagerRest.util.ErrorUtil.returnErrorInfo;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrdersService orderService;
    private final CustomersService customersService;
    private final OrderConverter orderConverter;

    @Autowired
    public OrderController(OrdersService orderService, OrderConverter orderConverter, CustomersService customersService) {
        this.orderService = orderService;
        this.customersService = customersService;
        this.orderConverter = orderConverter;
    }

    @GetMapping
    public OrdersResponse getOrders(){
        return new OrdersResponse(orderService.findAll().stream()
                .map(orderConverter::convertToOrderDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public OrderDTO getOrder(@PathVariable("id") int orderId){
        return orderConverter.convertToOrderDTO(orderService.findOne(orderId));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody OrderDTO orderDTO,
                                             BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            returnErrorInfo(bindingResult);
        }
        Order order = new Order();
        try {
        order.setCustomer(customersService.findOne(orderDTO.getCustomerId()));}
        catch (CustomerNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
        order.setToBring(orderDTO.getToBring());
        order.setToTake(orderDTO.getToTake());
        order.setDate(LocalDateTime.now());
    orderService.save(order);
    return ResponseEntity.ok(HttpStatus.OK);
    }

}
