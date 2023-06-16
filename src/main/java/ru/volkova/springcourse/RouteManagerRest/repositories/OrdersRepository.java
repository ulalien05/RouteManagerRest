package ru.volkova.springcourse.RouteManagerRest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.volkova.springcourse.RouteManagerRest.entities.Order;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Integer> {

    List<Order> findByCustomerName(String customerName);

    List<Order> findByDate(LocalDateTime date);

    void deleteById(int id);
}
