package ru.volkova.springcourse.RouteManagerRest.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.volkova.springcourse.RouteManagerRest.dto.OrderDTO;
import ru.volkova.springcourse.RouteManagerRest.dto.OrdersResponse;
import ru.volkova.springcourse.RouteManagerRest.entities.Order;
import ru.volkova.springcourse.RouteManagerRest.services.CustomersService;
import ru.volkova.springcourse.RouteManagerRest.services.OrdersService;
import ru.volkova.springcourse.RouteManagerRest.util.converters.OrderConverter;
import ru.volkova.springcourse.RouteManagerRest.util.exceptions.CustomerNotFoundException;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static ru.volkova.springcourse.RouteManagerRest.util.ErrorUtil.returnErrorInfo;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrdersService orderService;
    private final CustomersService customersService;
    private final OrderConverter orderConverter;

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

    @GetMapping("/{date}")
    public OrdersResponse getOrdersByDate(@PathVariable ("date") LocalDateTime date){
        return new OrdersResponse(orderService.findByDate(date).stream()
                .map(orderConverter::convertToOrderDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{customer_name}")
    public OrdersResponse getOrdersByCustomerId(@PathVariable("customer_name") String customerName){
        return new OrdersResponse(orderService.findOrdersByCustomerName(customerName)
                .stream()
                .map(orderConverter::convertToOrderDTO)
                .collect(Collectors.toList()));
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

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid OrderDTO orderDTO,
                                             @PathVariable("id") int id,
                                             BindingResult bindingResult){
        if (bindingResult.hasErrors())
            returnErrorInfo(bindingResult);
        orderService.update(id, orderConverter.convertToOrder(orderDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @DeleteMapping("/{order_id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("order_id") int orderId){
        orderService.delete(orderId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
