package ru.volkova.springcourse.RouteManagerRest.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="call")
@Data
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
}
