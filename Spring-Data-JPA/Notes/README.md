# Technical Notes: JPA vs Hibernate vs Spring Data JPA

## 1. Comparing JPA, Hibernate, and Spring Data JPA

| Aspect | Java Persistence API (JPA) | Hibernate | Spring Data JPA |
|---|---|---|---|
| **What is it?** | Specification / Standard (JSR 338). | ORM Framework / Implementation of JPA. | Data Access Abstraction Layer. |
| **Code Type** | Interfaces and annotations definition (e.g. `jakarta.persistence.*`). | Concrete classes implementing JPA spec (e.g. `SessionFactory`, `Session`). | Repository layer wrappers to reduce boilerplate. |
| **Execution** | Cannot run on its own; needs a provider. | Directly interacts with JDBC and SQL to execute operations. | Generates implementations of repository interfaces using Hibernate at runtime. |
| **Transaction Management** | Defines `@Transactional`. | Relies on Hibernate's `Transaction` API. | Manages transaction boundaries automatically using Spring AOP. |

---

## 2. Core Objects of Hibernate Framework

1.  **SessionFactory**: Thread-safe, heavy-weight cached object representing a configured database connection pool and mappings. Created once per application lifecycle.
2.  **Session**: Non-thread-safe context wrapping a JDBC connection. Used to CRUD entities and create queries.
3.  **Transaction**: Interface to programmatically manage transaction boundaries (`begin`, `commit`, `rollback`).
4.  **Query**: Used to build and execute SQL or HQL/JPQL statement plans against the database.

---

## 3. Querying Approaches in Spring Boot

### HQL (Hibernate Query Language) & JPQL (Java Persistence Query Language)
*   **Definition**: Object-oriented query languages targeting entities and fields instead of database tables and columns.
*   **Example (JPQL)**:
    ```java
    @Query("SELECT e FROM Employee e WHERE e.department.name = :deptName")
    List<Employee> findByDeptName(@Param("deptName") String deptName);
    ```

### Native Queries
*   **Definition**: Standard SQL queries executed directly on the underlying database. Useful for database-specific functions or performance optimizations.
*   **Example**:
    ```java
    @Query(value = "SELECT * FROM employee WHERE em_salary > :salary", nativeQuery = true)
    List<Employee> findHighEarnersNative(@Param("salary") double salary);
    ```

### Criteria API
*   **Definition**: Programmatic, type-safe query building approach. Extremely helpful for dynamic queries where search conditions are optional or determined at runtime.
*   **Example**:
    ```java
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
    Root<Employee> employee = cq.from(Employee.class);
    cq.where(cb.greaterThan(employee.get("salary"), 50000.00));
    List<Employee> result = entityManager.createQuery(cq).getResultList();
    ```

---

## 4. Advanced Persistence Features

### Pagination & Sorting
Spring Data JPA integrates pagination and sorting via `Pageable` and `Sort` parameters.
```java
Page<Employee> pagedResult = employeeRepository.findAll(PageRequest.of(0, 10, Sort.by("name").ascending()));
```

### Auditing
JPA Auditing automatically populates audit metadata like creation/modification dates and users.
```java
@CreatedDate
@Column(name = "created_date", updatable = false)
private LocalDateTime createdDate;
```

### Projections
Allows fetching specific subsets of attributes instead of full entity loads to improve network and memory performance.
```java
public interface EmployeeSummary {
    String getName();
    double getSalary();
}
```
