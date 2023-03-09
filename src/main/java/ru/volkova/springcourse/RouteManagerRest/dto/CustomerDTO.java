package ru.volkova.springcourse.RouteManagerRest.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CustomerDTO {

    @NotEmpty(message = "Name cannot be empty.")
    @Size(min=2, max=100, message = "Name should be between 2 and 100 characters.")
    private String name;

    @NotEmpty(message = "Укажите адрес клиента.")
    @Size(min=2, max=200, message = "Количество символов не должно быть менее 2 и более 200")
    private String address;

    @NotEmpty(message = "Укажите телефон клиента.")
    @Size(min=6, max=15, message = "Количество символов не должно быть менее 6 и более 15")
    private String phone;

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
