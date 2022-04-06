package com.kushnirmark.spring.rest.service;

import com.kushnirmark.spring.rest.entity.Employee;

import java.util.List;

public interface EmployeeService {
    //создаем интерфейс для отображения работников(для этого их нужно поместить в лист)
    public List<Employee> getAllEmployees();

    //создаем метод для сохранения работников
    public void saveEmployee(Employee employee);

    //Возвращает работника получая от нас ID
    public Employee getEmployee(int id);

    //Удаляем работника
    public void deleteEmployee(int id);
}
