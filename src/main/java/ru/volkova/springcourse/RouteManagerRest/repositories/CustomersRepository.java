package ru.volkova.springcourse.RouteManagerRest.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.volkova.springcourse.RouteManagerRest.entities.Customer;

import java.util.List;


@Repository
public interface CustomersRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findByNameContainingIgnoreCase(String name);


    List<Customer> findByName(String name);

    List<Customer> findByAddress(String address);

    List<Customer> findByPhone(String phone);
}