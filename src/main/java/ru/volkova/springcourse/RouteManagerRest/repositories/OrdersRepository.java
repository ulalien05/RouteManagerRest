package ru.volkova.springcourse.RouteManagerRest.repositories;

import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.volkova.springcourse.RouteManagerRest.models.Order;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Integer> {

    List<Order> findByCustomerId(int customerId);
}
