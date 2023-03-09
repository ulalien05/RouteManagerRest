package ru.volkova.springcourse.RouteManagerRest.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="call")
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @Column(name="to_bring")
    private String toBring;

    @Column(name="to_take")
    private String toTake;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;

    public Order(){}

    public Order(Customer customer, String toBring, String toTake) {
        this.customer = customer;
        this.toBring = toBring;
        this.toTake = toTake;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getToBring() {
        return toBring;
    }

    public void setToBring(String toBring) {
        this.toBring = toBring;
    }

    public String getToTake() {
        return toTake;
    }

    public void setToTake(String toTake) {
        this.toTake = toTake;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
