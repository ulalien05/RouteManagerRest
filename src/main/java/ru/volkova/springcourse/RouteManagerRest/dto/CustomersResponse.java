package ru.volkova.springcourse.RouteManagerRest.dto;

import ru.volkova.springcourse.RouteManagerRest.models.Customer;

import java.util.List;

public class CustomersResponse {

    private List<CustomerDTO> customers;

    public CustomersResponse(List<CustomerDTO> customers) {
        this.customers = customers;
    }

    public List<CustomerDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerDTO> customers) {
        this.customers = customers;
    }
}
