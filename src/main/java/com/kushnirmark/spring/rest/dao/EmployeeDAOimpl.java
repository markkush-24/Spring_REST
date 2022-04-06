package com.kushnirmark.spring.rest.dao;

import com.kushnirmark.spring.rest.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOimpl implements EmployeeDAO {
    //Так мы прописываем зависимоть чтобы наш DAO мог подключения к БД  , для этого он должен иметь доступ к SessionFactory
    @Autowired
    private SessionFactory sessionFactory;


    @Override
//    @Transactional // Открывает и закрывает транзакцию , нам не надо писать session.beginTransaction();
    // session.getTransaction().commit(); как мы это делали ранее


    public List<Employee> getAllEmployees() {
//получаем из этой фабрики сессию

        Session session = sessionFactory.getCurrentSession();
        List<Employee> allEmployees = (List<Employee>) session.createQuery("from Employee ", Employee.class).getResultList();
        return allEmployees;
    }

    @Override
    public void saveEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        //используем метод который сам распознает что нужно сделать , если сотрудник новый до добавит , если имеющийся то изменит
        session.saveOrUpdate(employee);
    }

    @Override
    public Employee getEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();
      Employee employee = session.get(Employee.class , id);
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();
//        создаем коллекцию Query в которую помещаем сотрудника которого хотим удалить , после чего присвоим в setParameter тому работнику
//        которого мы получили ID ,  employeeId который указали и будет самим id. После чего  query.executeUpdate(); вносит изменения
        Query<Employee>query = session.createQuery("delete from Employee where id = :employeeId");
        query.setParameter("employeeId",id);
        query.executeUpdate();
    }
}
