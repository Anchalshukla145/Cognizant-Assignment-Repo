package com.cognizant.orm_learn.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cognizant.orm_learn.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // 1. JPQL / HQL to fetch employees with permanent status
    @Query("SELECT e FROM Employee e WHERE e.permanent = :permanent")
    List<Employee> findEmployeesByPermanent(@Param("permanent") boolean permanent);

    // 2. Fetch Join to avoid N+1 select problem
    @Query("SELECT e FROM Employee e LEFT JOIN FETCH e.department LEFT JOIN FETCH e.skillList")
    List<Employee> getAllEmployeesWithDetails();

    // 3. Native SQL Query
    @Query(value = "SELECT * FROM employee WHERE em_salary > :salary", nativeQuery = true)
    List<Employee> findEmployeesWithSalaryGreaterThanNative(@Param("salary") double salary);

    // 4. Custom DML operation using JPQL (@Modifying)
    @Modifying
    @Query("UPDATE Employee e SET e.salary = :salary WHERE e.id = :id")
    int updateEmployeeSalary(@Param("id") int id, @Param("salary") double salary);

    // 5. Pagination support
    Page<Employee> findByPermanent(boolean permanent, Pageable pageable);
}
