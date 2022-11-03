package tn.com.shinra.session.services;

import tn.com.shinra.session.models.Book;
import tn.com.shinra.session.models.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee addEmployee(Employee employee);

    Optional<Employee> findEmployee(long e);

    Employee  updateEmployee(Employee employee);

    List<Employee> getEmployees();

    public Employee getEmployeeById(long id);

    public Employee getEmployeeByName(String name);

    String deleteEmployee(long id);
}
