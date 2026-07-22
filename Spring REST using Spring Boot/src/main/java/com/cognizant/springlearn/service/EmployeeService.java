package com.cognizant.springlearn.service;

import com.cognizant.springlearn.dao.EmployeeDao;
import com.cognizant.springlearn.exception.EmployeeNotFoundException;
import com.cognizant.springlearn.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeDao employeeDao;

    public List<Employee> getAllEmployees() {
        LOGGER.info("Start EmployeeService getAllEmployees");
        return employeeDao.getAllEmployees();
    }

    public void updateEmployee(Employee employee) throws EmployeeNotFoundException {
        LOGGER.info("Start EmployeeService updateEmployee: {}", employee);
        employeeDao.updateEmployee(employee);
    }

    public void deleteEmployee(Integer id) throws EmployeeNotFoundException {
        LOGGER.info("Start EmployeeService deleteEmployee id: {}", id);
        employeeDao.deleteEmployee(id);
    }
}
