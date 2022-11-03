package tn.com.shinra.session.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.com.shinra.session.models.AuthorModel;
import tn.com.shinra.session.models.Book;
import tn.com.shinra.session.models.Employee;
import tn.com.shinra.session.repositories.BookRepository;
import tn.com.shinra.session.repositories.EmployeeRepository;
import tn.com.shinra.session.services.EmployeeService;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);

        // return "employee added successfully";
    }

    @Override
    public Optional<Employee> findEmployee(long b) {
        return Optional.empty();
    }



    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }


    public Employee getEmployeeById(long id) {
        return employeeRepository.findById(id);
    }
    @Override
    public String deleteEmployee(long id) {
        employeeRepository.deleteById(id);
        return "employee removed !! " + id;
    }
    @Override
    public Employee getEmployeeByName(String name) {
        return employeeRepository.findByName(name);
    }
    @Override
    public Employee updateEmployee(Employee employee) {
        Employee existingEmployee = employeeRepository.findById(employee.getId()).orElse(null);
        existingEmployee.setName(employee.getName());
        existingEmployee.setAdress(employee.getAdress());
        existingEmployee.setCin(employee.getCin());
        return employeeRepository.save(existingEmployee);
    }

}
