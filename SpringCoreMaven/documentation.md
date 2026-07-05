# Project Documentation: Spring Core and Maven

This document provides a detailed explanation of Spring Core and Maven concepts, describes the two projects created in this workspace to solve the 9 exercises, and highlights the technical challenges faced and how they were resolved.

---

## 1. Core Concepts Explained

### 1.1 Maven
Maven is a build automation tool used primarily for Java projects. It defines how a project is built and managed through a standard Project Object Model (POM).
* **pom.xml**: The Project Object Model XML configuration file containing project details, plugins, build properties, and dependency declarations.
* **Dependencies**: External libraries (such as Spring or AspectJ) required by the project. Maven automatically downloads them from the Maven Central repository.
* **Maven Lifecycle**: Standard phases including `clean` (deletes compile targets), `compile` (translates source code to byte-code), `test` (runs unit tests), and `package` (bundles compiled code into JAR/WAR formats).

### 1.2 Spring Core & IoC Container
Spring Core is the foundation of the Spring Framework, providing dependency injection and container configuration.
* **IoC (Inversion of Control)**: The design principle where the control of object creation and lifecycle is shifted from the programmer to the Spring container.
* **Dependency Injection (DI)**: A pattern where an object's dependencies are "injected" into it by the container rather than the object instantiating them itself.
  * **Constructor Injection**: Injecting dependencies through a class constructor (ideal for mandatory dependencies).
  * **Setter Injection**: Injecting dependencies through public setter methods (ideal for optional or mutable dependencies).
* **Application Context**: The central Spring container that manages bean creation, configuration, wiring, and lifecycle.
* **Component Scanning**: The process where Spring automatically scans packages for annotated classes (like `@Repository`, `@Service`, `@Component`, `@Controller`) and registers them as beans in the context.

### 1.3 Spring AOP (Aspect-Oriented Programming)
AOP allows separating cross-cutting concerns (such as logging, transaction management, or security) from the main business logic.
* **Aspect**: A modular unit representing a cross-cutting concern (e.g., `LoggingAspect`).
* **Advice**: Action taken by an aspect at a particular joint point.
  * **Before Advice (`@Before`)**: Runs before the execution of a target method.
  * **After Advice (`@After`)**: Runs after the execution of a target method (regardless of outcome).
  * **Around Advice (`@Around`)**: Enfolds the target method call, allowing custom behavior before and after execution, and controls whether the target method proceeds.

---

## 2. Directory Structure and Project Segmentation

To keep the codebase simple and easy to understand for a college assignment, the workspace is organized into **exactly two main folders** with flat package structures (`com.library` directly, with no nested subdirectories).

```
SpringCoreMaven/
│
├── LibraryManagement/                      <-- Project 1 (Exercises 1-8)
│   ├── pom.xml                             <-- Dependencies for Spring Context, AOP, WebMVC
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/
│           │       └── library/
│           │           ├── BookRepository.java
│           │           ├── BookService.java
│           │           ├── LoggingAspect.java
│           │           └── LibraryManagementApplication.java
│           └── resources/
│               └── applicationContext.xml  <-- Context with component scan & XML bean configs
│
├── LibraryManagementSpringBoot/            <-- Project 2 (Exercise 9)
│   ├── pom.xml                             <-- Spring Boot Starter Web, JPA, H2 dependencies
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/
│           │       └── library/
│           │           ├── Book.java       <-- Entity
│           │           ├── BookRepository.java <-- JPA Repository
│           │           ├── BookController.java <-- REST Controller
│           │           └── LibraryManagementSpringBootApplication.java
│           └── resources/
│               └── application.properties  <-- H2 DB properties configuration
│
├── Spring Core_Maven.docx                  <-- Assignment description
└── documentation.md                        <-- This document
```

---

## 3. What Has Been Done (Exercise Breakdown)

### Project 1: `LibraryManagement` (Spring Core)
* **Exercise 1 (Basic Setup) & Exercise 4 (Maven Configuration)**: Configured a Maven project using `pom.xml` with dependencies for Spring Context, AOP, and WebMVC, targeting Java 17 compatibility.
* **Exercise 2 & 5 (IoC & DI XML Configuration)**: Created `applicationContext.xml` defining a basic repository (`BookRepository`) and service (`BookService`) with setter injection.
* **Exercise 3 & 8 (Spring AOP Logging)**: Configured AspectJ support. Added `LoggingAspect` with `@Before`, `@After`, and `@Around` advice methods. The around advice logs the exact execution duration of the service methods.
* **Exercise 6 (Annotation Configuration)**: Enabled component scanning (`<context:component-scan>`) and annotated classes with `@Service` and `@Repository` for automatic detection.
* **Exercise 7 (Constructor vs. Setter Injection)**: Configured both constructor-arg and property-based setter injection configurations inside `applicationContext.xml`.
* **Execution Runner**: Created `LibraryManagementApplication.java` to fetch scanned and XML-wired beans from the Spring container and demonstrate that they run successfully with aspect logs printing in the console.

### Project 2: `LibraryManagementSpringBoot` (Spring Boot)
* **Exercise 9 (Spring Boot Application)**:
  * Created a standard Spring Boot parent project configuration.
  * Added `spring-boot-starter-web`, `spring-boot-starter-data-jpa`, and `h2` database dependencies.
  * Configured properties in `application.properties` to connect to an in-memory H2 database.
  * Created a `Book` entity class and a `BookRepository` interface extending `JpaRepository`.
  * Created a `BookController` REST controller defining mapping endpoints for standard HTTP requests (`GET`, `POST`, `PUT`, `DELETE`).

---

## 4. Issues Faced and Resolutions

### Issue 1: Environment Java Version Mismatch
* **Symptom**: The default Maven command (`mvn`) on the host system compiles using a JDK 1.8 runtime environment, while modern Spring Framework 6.x and Spring Boot 3.x require a minimum of Java 17.
* **Resolution**: Configured a Maven compile wrapper targeting Java 17. Evaluated builds and ran tasks utilizing the host system's Java 25 wrapper command: `"C:\maven\bin\mvn-java25.cmd"`.

### Issue 2: Ambiguous Bean Definitions (NoUniqueBeanDefinitionException)
* **Symptom**: During context initialization, Spring crashed with `NoUniqueBeanDefinitionException: No qualifying bean of type 'com.library.BookRepository' available`. This was because we had both component scanning (which automatically registered a `BookRepository` bean) and XML bean definitions (which registered an `xmlBookRepository` bean).
* **Resolution**: Added `@Qualifier("bookRepository")` to the autowired setter parameter in `BookService` so that the annotated service knows which bean to request. For explicit XML bean instances, properties were wired to `xmlBookRepository` directly, resolving the ambiguity.

### Issue 3: BookService Type Ambiguity in Application Runner
* **Symptom**: Running `context.getBean(BookService.class)` threw a lookup exception because multiple service beans (`bookService`, `xmlBookServiceConstructor`, `xmlBookServiceSetter`) matched the class type.
* **Resolution**: Updated `LibraryManagementApplication` to retrieve beans by their explicit string IDs (`"bookService"`, `"xmlBookServiceConstructor"`, `"xmlBookServiceSetter"`) and cast them, ensuring the runner retrieves the correct configured instances.
