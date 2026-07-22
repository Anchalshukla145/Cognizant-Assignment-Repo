package com.cognizant.springlearn.dao;

import com.cognizant.springlearn.exception.EmployeeNotFoundException;
import com.cognizant.springlearn.model.Department;
import com.cognizant.springlearn.model.Employee;
import com.cognizant.springlearn.model.Skill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class EmployeeDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDao.class);
    private static final List<Employee> EMPLOYEE_LIST = new ArrayList<>();

    static {
        Department dept = new Department(1, "ECE");
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill(101, "Java"));
        skills.add(new Skill(102, "Spring Boot"));

        EMPLOYEE_LIST.add(new Employee(1, "John Doe", 55000.0, true, new Date(), dept, skills));
        EMPLOYEE_LIST.add(new Employee(2, "Jane Smith", 62000.0, false, new Date(), dept, skills));
    }

    public List<Employee> getAllEmployees() {
        LOGGER.info("Start EmployeeDao getAllEmployees");
        return EMPLOYEE_LIST;
    }

    public void updateEmployee(Employee employee) throws EmployeeNotFoundException {
        LOGGER.info("Start EmployeeDao updateEmployee: {}", employee);
        Employee existing = EMPLOYEE_LIST.stream()
                .filter(e -> e.getId().equals(employee.getId()))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + employee.getId() + " not found"));

        existing.setName(employee.getName());
        existing.setSalary(employee.getSalary());
        existing.setPermanent(employee.getPermanent());
        existing.setDateOfBirth(employee.getDateOfBirth());
        existing.setDepartment(employee.getDepartment());
        existing.setSkills(employee.getSkills());
        LOGGER.info("Employee updated successfully");
    }

    public void deleteEmployee(Integer id) throws EmployeeNotFoundException {
        LOGGER.info("Start EmployeeDao deleteEmployee id: {}", id);
        boolean removed = EMPLOYEE_LIST.removeIf(e -> e.getId().equals(id));
        if (!removed) {
            throw new EmployeeNotFoundException("Employee with id " + id + " not found for deletion");
        }
        LOGGER.info("Employee with id {} deleted successfully", id);
    }
}
