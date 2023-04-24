package ru.volkova.springcourse.RouteManagerRest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.volkova.springcourse.RouteManagerRest.models.Customer;
import ru.volkova.springcourse.RouteManagerRest.repositories.CustomersRepository;
import ru.volkova.springcourse.RouteManagerRest.util.exceptions.CustomerNotFoundException;


import java.util.List;


@Service
@Transactional(readOnly = true)
public class CustomersService {

    private final CustomersRepository customersRepository;


    @Autowired
    public CustomersService(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    public List<Customer> findAll() {
        return customersRepository.findAll();
    }

    public Customer findOne(int id) {
        return customersRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Клиент не найден. Проверьте данные клиента или создайте нового."));
    }

    public List<Customer> findByNameContaining(String name){
        return customersRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Customer> findByName(String name){
        return customersRepository.findByName(name);
    }

    public List<Customer> findByAddress(String address){
        return customersRepository.findByAddress(address);
    }

    public List<Customer> findByPhone(String phone){
        return customersRepository.findByPhone(phone);
    }

    @Transactional
    public void save(Customer customer){
        customersRepository.save(customer);
    }
}
