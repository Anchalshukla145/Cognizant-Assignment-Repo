# Hands-on 2: Hibernate XML Configuration Walkthrough

Hibernate XML Configuration is the classic way of configuring Hibernate and mapping Java objects (POJOs) to database tables. This walkthrough explains how it is structured and how standard session operations work.

## 1. Hibernate Configuration File (`hibernate.cfg.xml`)

This file is responsible for setting up the database connection and global properties.

```xml
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE hibernate-configuration SYSTEM 
"http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
   <session-factory>
      <!-- Database Connection Settings -->
      <property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
      <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/ormlearn</property>
      <property name="hibernate.connection.username">root</property>
      <property name="hibernate.connection.password">Anay@145</property>

      <!-- SQL Dialect -->
      <property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>

      <!-- Echo all executed SQL to stdout -->
      <property name="show_sql">true</property>

      <!-- Map XML mapping documents -->
      <mapping resource="Employee.hbm.xml"/>
   </session-factory>
</hibernate-configuration>
```

---

## 2. Hibernate Mapping File (`Employee.hbm.xml`)

This file maps the fields of a Java class to columns in the database table.

```xml
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE hibernate-mapping PUBLIC 
"-//Hibernate/Hibernate Mapping DTD 3.0//EN"
"http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">
<hibernate-mapping>
   <class name="com.cognizant.model.Employee" table="employee">
      <meta attribute="class-description">
         This class contains the employee detail. 
      </meta>
      
      <!-- Primary Key mapping -->
      <id name="id" type="int" column="em_id">
         <generator class="native"/>
      </id>
      
      <!-- Field mappings -->
      <property name="name" column="em_name" type="string"/>
      <property name="salary" column="em_salary" type="double"/>
      <property name="permanent" column="em_permanent" type="boolean"/>
      <property name="dateOfBirth" column="em_date_of_birth" type="date"/>
   </class>
</hibernate-mapping>
```

---

## 3. Core Objects and APIs in Hibernate

### SessionFactory
A thread-safe, heavy-weight object that represents a configured instance of Hibernate. It is created once per database and is used to open `Session` instances.

### Session
A short-lived object representing a conversation between the application and the database. It wraps a JDBC connection and acts as a factory for `Transaction` and `Query`.

### Transaction
Allows the application to define units of work. It wraps the underlying database transaction interface.

### Key API Methods:
*   **`session.save(object)`**: Inserts a new record into the database corresponding to the given transient object and returns the generated identifier.
*   **`session.get(Class, id)`**: Retrieves a persistent instance of the given class with the specified primary key, returning `null` if not found.
*   **`session.delete(object)`**: Removes the corresponding database row for the given persistent object.
*   **`session.createQuery(hql).list()`**: Executes a Hibernate Query Language (HQL) query and returns a list of resulting objects.
*   **`tx.commit()`**: Commits the database transaction.
*   **`tx.rollback()`**: Rolls back the database transaction if any exception occurs.
