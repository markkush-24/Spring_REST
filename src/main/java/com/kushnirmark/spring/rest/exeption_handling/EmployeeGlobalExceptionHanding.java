package com.kushnirmark.spring.rest.exeption_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //Отмечается класс  ,предоставляющий функциональность Global Exception Handler'a
//При правильном построении кода мы должны сделать один класс который будет отвечать за обработку исключений
public class EmployeeGlobalExceptionHanding {

    @ExceptionHandler //Отвечает метод ответственный за обработку исключений
    public ResponseEntity<EmployeeIncorrectData> handlerException (NoSuchEmployeeException exception){
        //ResponseEntity это обертка HTTP response
        //Мы показываем что в случае выброса NoSuchEmployeeException мы должны добавить в тело ResponseEntity добавить EmployeeIncorrectData
        EmployeeIncorrectData data = new EmployeeIncorrectData();//создаем объект ответственный за работу с JSON
        data.setInfo(exception.getMessage());//передаем ему в поле Info которое у него создано, message Exception
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler //Отвечает метод ответственный за обработку исключений
    public ResponseEntity<EmployeeIncorrectData> handlerException (Exception exception){ //метод теперь реагирует на все виды
        //исключений
        EmployeeIncorrectData data = new EmployeeIncorrectData();//создаем объект ответственный за работу с JSON
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
