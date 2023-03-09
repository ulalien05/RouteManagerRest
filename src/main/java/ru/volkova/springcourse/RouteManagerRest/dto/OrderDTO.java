package ru.volkova.springcourse.RouteManagerRest.dto;

public class OrderDTO {

    int customerId;

    String toBring;

    String toTake;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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
}
