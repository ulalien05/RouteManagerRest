package ru.volkova.springcourse.RouteManagerRest.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "customer")
@Data
public class Customer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name cannot be empty.")
    @Size(min=2, max=100, message = "Name should be between 2 and 100 characters.")
    private String name;

    @Column(name = "address")
    @NotEmpty(message = "Укажите адрес клиента.")
    @Size(min=2, max=200, message = "Количество символов не должно быть менее 2 и более 200")
    private String address;

    @Column(name = "phone")
    @NotEmpty(message = "Укажите телефон клиента.")
    @Size(min=6, max=15, message = "Количество символов не должно быть менее 6 и более 15")
    private String phone;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;


    public Customer() {
    }
}