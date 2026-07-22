package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.exception.EmployeeNotFoundException;
import com.cognizant.springlearn.model.Employee;
import com.cognizant.springlearn.service.EmployeeService;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        LOGGER.info("Start EmployeeController getAllEmployees");
        return employeeService.getAllEmployees();
    }

    @PutMapping("/employees")
    public void updateEmployee(@RequestBody @Valid Employee employee) throws EmployeeNotFoundException {
        LOGGER.info("Start EmployeeController updateEmployee: {}", employee);
        employeeService.updateEmployee(employee);
        LOGGER.info("End EmployeeController updateEmployee");
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable("id") Integer id) throws EmployeeNotFoundException {
        LOGGER.info("Start EmployeeController deleteEmployee id: {}", id);
        employeeService.deleteEmployee(id);
        LOGGER.info("End EmployeeController deleteEmployee");
    }
}
