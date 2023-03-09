package ru.volkova.springcourse.RouteManagerRest.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;


@Entity
@Table(name = "customer")
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

    public Customer(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}