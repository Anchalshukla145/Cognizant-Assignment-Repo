# Hands-on 3: Hibernate Annotation Configuration Walkthrough

Hibernate Annotation Configuration replaces external mapping XML files (`.hbm.xml`) with Java annotations placed directly on the entity class.

## 1. Mapped Persistent Class Example
```java
package com.cognizant.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employee")
public class Employee {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "em_id")
   private int id;

   @Column(name = "em_name")
   private String name;

   @Column(name = "em_salary")
   private double salary;

   @Column(name = "em_permanent")
   private boolean permanent;

   @Column(name = "em_date_of_birth")
   @Temporal(TemporalType.DATE)
   private Date dateOfBirth;

   // Getters and Setters
}
```

---

## 2. Explanation of Annotations

### `@Entity`
Tells Hibernate/JPA that this class is a persistent entity. It signifies that objects of this class can be stored in the database.

### `@Table(name = "...")`
Defines the mapping database table. If not specified, Hibernate maps the class to a table with the same name as the class.

### `@Id`
Specifies the primary key of the entity.

### `@GeneratedValue(strategy = ...)`
Configures the generation strategy for the primary key. `GenerationType.IDENTITY` maps to database auto-increment columns (like `AUTO_INCREMENT` in MySQL or `IDENTITY` in SQL Server).

### `@Column(name = "...")`
Defines the mapping database column name, length, nullability, etc.

---

## 3. Configuration with `hibernate.cfg.xml`
Even when using annotations, `hibernate.cfg.xml` is still used to declare database connection credentials and list the annotated classes:

```xml
<hibernate-configuration>
   <session-factory>
      <property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
      <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/ormlearn</property>
      <property name="hibernate.connection.username">root</property>
      <property name="hibernate.connection.password">******</property>
      <property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>

      <!-- Reference the annotated class here -->
      <mapping class="com.cognizant.model.Employee"/>
   </session-factory>
</hibernate-configuration>
```
