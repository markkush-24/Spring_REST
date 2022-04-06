package com.kushnirmark.spring.rest.configuration;

import com.kushnirmark.spring.rest.entity.Employee;
import com.kushnirmark.spring.rest.exeption_handling.EmployeeIncorrectData;
import com.kushnirmark.spring.rest.exeption_handling.NoSuchEmployeeException;
import com.kushnirmark.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Управляет RestResponse and RestQuestions
@RequestMapping("api")
public class MyRESTController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("employees")
    public List<Employee> showAllEmployees() {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees;
        //В формате JSON будут отображены в браузере данные о всех работниках
    }

    @GetMapping("employees/{id}")
    public Employee getEmployee(@PathVariable int id) { //@PathVariable используется для получения значения переменной из адреса запроса
        // необходим для установки полученного ID в параметр метода
        Employee employee = employeeService.getEmployee(id);

        if (employee == null) {
            throw new NoSuchEmployeeException("There is no employee with ID = " + id + " in Database");
        }
        return employee;
    }

    @PostMapping("employees")//PostMapping связывает HTTP запрос , использующий HTTP метод POST c методом контроллера
    public Employee addNewEmployee(@RequestBody Employee employee) { // @RequestBody связывает тело HTTP метода с параметром
        //метода Controller'a
        employeeService.saveEmployee(employee);
        return employee;
    }

    @PutMapping ("employees")//Создание метода для изменения данных работника , утанавливаем другую аннотацию @PutMapping
    public Employee updateEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
        return employee;
    }
    @DeleteMapping("employees/{id}")//Удаление работника
    public String deleteEmployee(@PathVariable int id){
        //прежде всего надо проверить есть ли работник с таким ID , если да то удаляем , если нет то будет Exception
        Employee employee = employeeService.getEmployee(id);
        if(employee == null){
            throw new NoSuchEmployeeException("There is no employee with ID = " + id + " in Database");
        }
        employeeService.deleteEmployee(id);
        return "Employee with ID = " + id + " was deleted";
    }
}
