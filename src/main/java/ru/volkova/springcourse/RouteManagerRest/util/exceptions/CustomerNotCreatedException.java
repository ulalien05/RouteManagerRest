package ru.volkova.springcourse.RouteManagerRest.util.exceptions;

public class CustomerNotCreatedException extends RuntimeException{
    public CustomerNotCreatedException(String message){
        super(message);
    }
}
