package ru.volkova.springcourse.RouteManagerRest.util.exceptions;

public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException(String message){
        super(message);
    }
}
