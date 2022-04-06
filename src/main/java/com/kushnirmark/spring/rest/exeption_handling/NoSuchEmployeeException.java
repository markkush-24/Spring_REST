package com.kushnirmark.spring.rest.exeption_handling;


//создаем свой Exception который выбрасываем при не корректном вводе ID
public class NoSuchEmployeeException  extends  RuntimeException{
    public NoSuchEmployeeException(String message) {
        super(message);
    }
}
