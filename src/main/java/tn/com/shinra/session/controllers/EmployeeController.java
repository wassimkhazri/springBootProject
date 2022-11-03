package tn.com.shinra.session.controllers;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.com.shinra.session.models.AuthorModel;
import tn.com.shinra.session.models.Book;
import tn.com.shinra.session.models.Employee;
import tn.com.shinra.session.models.Library;
import tn.com.shinra.session.repositories.EmployeeRepository;
import tn.com.shinra.session.repositories.LibraryRepository;
import tn.com.shinra.session.services.BookService;
import tn.com.shinra.session.services.EmployeeService;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private LibraryRepository libraryRepository;

    @PostMapping("/addEmployee")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @GetMapping("/employees")
    public List<Employee> findAllEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/libraries/{libraryId}/employees")
    public ResponseEntity<List<Employee>> getAllEmployeesByLibraryId(@PathVariable(value = "libraryId") Long libraryId) {
        if (!libraryRepository.existsById(libraryId)) {
            throw new ResourceNotFoundException("Not found Library with id = " + libraryId);
        }

        List<Employee> comments = employeeRepository.findByLibraryId(libraryId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
    @PostMapping("/libraries/{libraryId}/employees")
    public ResponseEntity<Employee> createEmplyee(@PathVariable(value = "libraryId") Long libraryId,
                                                 @RequestBody Employee emplyeeRequest) {
        Employee emplyee = libraryRepository.findById(libraryId).map(library -> {
            emplyeeRequest.setLibrary(library);
            return employeeRepository.save(emplyeeRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Library with id = " + libraryId));

        return new ResponseEntity<>(emplyee, HttpStatus.CREATED);
    }
    @DeleteMapping("/libraries/{libraryId}/employees")
    public ResponseEntity<List<Employee>> deleteAllEmplyeesOfLibrary(@PathVariable(value = "libraryId") Long libraryId) {
        if (!libraryRepository.existsById(libraryId)) {
            throw new ResourceNotFoundException("Not found Tutorial with id = " + libraryId);
        }

        employeeRepository.deleteByLibraryId(libraryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @GetMapping("/employees")
//    public ResponseEntity<List<Employee>> getAllEmployees(@RequestParam(required = false) String library) {
//        try {
//            List<Employee> employees = new ArrayList<Employee>();
//
//            if (library == null)
//                employeeRepository.findAll().forEach(employees::add);
//            else
//                employeeRepository.findByLibraryContaining(library).forEach(employees::add);
//
//            if (employees.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(employees, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/employeeByName/{name}")
    public Employee findEmployeeByName(@PathVariable String name) {
        return employeeService.getEmployeeByName(name);
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }






}
