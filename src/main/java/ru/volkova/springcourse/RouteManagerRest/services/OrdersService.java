package ru.volkova.springcourse.RouteManagerRest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.volkova.springcourse.RouteManagerRest.models.Customer;
import ru.volkova.springcourse.RouteManagerRest.models.Order;
import ru.volkova.springcourse.RouteManagerRest.repositories.OrdersRepository;
import ru.volkova.springcourse.RouteManagerRest.util.exceptions.OrderNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final CustomersService customersService;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository, CustomersService customersService, CustomersService customersService1) {
        this.ordersRepository = ordersRepository;
        this.customersService = customersService1;
    }

    public List<Order> getOrdersByCustomer(Customer customer){
        return ordersRepository.findByCustomerId(customer.getId());
    }

    public List<Order> findAll(){
        return ordersRepository.findAll();
    }

    public Order findOne(int id){
        Optional<Order> foundOrder = ordersRepository.findById(id);
        return foundOrder.orElseThrow(OrderNotFoundException::new);
    }

    @Transactional
    public void save(Order order){
        enrichOrder(order);
        ordersRepository.save(order);
    }

    public void enrichOrder(Order order){
        order.setCustomer(customersService.findOne(order.getCustomer().getId()));
        order.setDate(LocalDateTime.now());
    }

}
