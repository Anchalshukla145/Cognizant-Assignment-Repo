package com.cognizant.orm_learn.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cognizant.orm_learn.model.Employee;
import com.cognizant.orm_learn.repository.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional(readOnly = true)
    public Employee get(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Transactional
    public int updateEmployeeSalary(int id, double salary) {
        return employeeRepository.updateEmployeeSalary(id, salary);
    }

    @Transactional(readOnly = true)
    public List<Employee> getAllEmployeesWithDetails() {
        return employeeRepository.getAllEmployeesWithDetails();
    }

    @Transactional(readOnly = true)
    public List<Employee> getEmployeesByPermanent(boolean permanent) {
        return employeeRepository.findEmployeesByPermanent(permanent);
    }

    @Transactional(readOnly = true)
    public List<Employee> getEmployeesWithSalaryGreaterThanNative(double salary) {
        return employeeRepository.findEmployeesWithSalaryGreaterThanNative(salary);
    }

    @Transactional(readOnly = true)
    public Page<Employee> getEmployeesPaged(boolean permanent, int pageNum, int pageSize) {
        return employeeRepository.findByPermanent(
            permanent, 
            PageRequest.of(pageNum, pageSize, Sort.by("name").ascending())
        );
    }

    // Criteria API Demonstration
    @Transactional(readOnly = true)
    public List<Employee> findEmployeesByCriteria(Double minSalary, Integer departmentId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> employee = cq.from(Employee.class);
        List<Predicate> predicates = new ArrayList<>();

        if (minSalary != null) {
            predicates.add(cb.greaterThan(employee.get("salary"), minSalary));
        }
        if (departmentId != null) {
            predicates.add(cb.equal(employee.get("department").get("id"), departmentId));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getResultList();
    }
}
