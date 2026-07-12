package com.cognizant.orm_learn;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import com.cognizant.orm_learn.model.Country;
import com.cognizant.orm_learn.model.Department;
import com.cognizant.orm_learn.model.Employee;
import com.cognizant.orm_learn.model.Skill;
import com.cognizant.orm_learn.model.Stock;
import com.cognizant.orm_learn.service.CountryService;
import com.cognizant.orm_learn.service.DepartmentService;
import com.cognizant.orm_learn.service.EmployeeService;
import com.cognizant.orm_learn.service.SkillService;
import com.cognizant.orm_learn.service.exception.CountryNotFoundException;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    private static CountryService countryService;
    private static EmployeeService employeeService;
    private static DepartmentService departmentService;
    private static SkillService skillService;
    private static com.cognizant.orm_learn.repository.StockRepository stockRepository;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        LOGGER.info("Inside main");

        countryService = context.getBean(CountryService.class);
        employeeService = context.getBean(EmployeeService.class);
        departmentService = context.getBean(DepartmentService.class);
        skillService = context.getBean(SkillService.class);
        stockRepository = context.getBean(com.cognizant.orm_learn.repository.StockRepository.class);

        try {
            LOGGER.info("-------------------- COUNTRY TESTS --------------------");
            testGetAllCountries();
            testFindCountryByCode();
            testAddCountry();
            testUpdateCountry();
            testDeleteCountry();
            testCountryQueryMethods();

            LOGGER.info("-------------------- STOCK TESTS --------------------");
            testStockQueries();

            LOGGER.info("-------------------- RELATIONSHIP TESTS --------------------");
            testGetEmployee();
            testAddEmployee();
            testUpdateEmployee();
            testGetDepartment();

            LOGGER.info("-------------------- ADVANCED QUERY TESTS --------------------");
            testAdvancedQueries();
            
        } catch (Exception e) {
            LOGGER.error("Error occurred during hands-on execution: ", e);
        }
    }

    // ----------------- COUNTRY TESTS -----------------
    private static void testGetAllCountries() {
        LOGGER.info("Start: testGetAllCountries");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.info("Total Countries: {}", countries.size());
        LOGGER.debug("countries={}", countries);
        LOGGER.info("End: testGetAllCountries");
    }

    private static void testFindCountryByCode() {
        LOGGER.info("Start: testFindCountryByCode");
        try {
            Country country = countryService.findCountryByCode("IN");
            LOGGER.info("Country Found: {}", country);
        } catch (CountryNotFoundException e) {
            LOGGER.error("Country not found: IN", e);
        }

        try {
            countryService.findCountryByCode("XX");
        } catch (CountryNotFoundException e) {
            LOGGER.info("Expected Exception for XX: {}", e.getMessage());
        }
        LOGGER.info("End: testFindCountryByCode");
    }

    private static void testAddCountry() {
        LOGGER.info("Start: testAddCountry");
        Country newCountry = new Country("ZZ", "Test Country ZZ");
        countryService.addCountry(newCountry);
        try {
            Country checked = countryService.findCountryByCode("ZZ");
            LOGGER.info("Successfully added and retrieved country: {}", checked);
        } catch (CountryNotFoundException e) {
            LOGGER.error("Failed to retrieve added country ZZ", e);
        }
        LOGGER.info("End: testAddCountry");
    }

    private static void testUpdateCountry() {
        LOGGER.info("Start: testUpdateCountry");
        try {
            countryService.updateCountry("ZZ", "Updated Country ZZ");
            Country checked = countryService.findCountryByCode("ZZ");
            LOGGER.info("Successfully updated country: {}", checked);
        } catch (CountryNotFoundException e) {
            LOGGER.error("Failed to update country ZZ", e);
        }
        LOGGER.info("End: testUpdateCountry");
    }

    private static void testDeleteCountry() {
        LOGGER.info("Start: testDeleteCountry");
        countryService.deleteCountry("ZZ");
        try {
            countryService.findCountryByCode("ZZ");
            LOGGER.error("Country ZZ was not deleted!");
        } catch (CountryNotFoundException e) {
            LOGGER.info("Successfully verified deletion of ZZ: {}", e.getMessage());
        }
        LOGGER.info("End: testDeleteCountry");
    }

    private static void testCountryQueryMethods() {
        LOGGER.info("Start: testCountryQueryMethods");
        
        LOGGER.info("Countries containing 'ou':");
        List<Country> ouCountries = countryService.findCountriesContaining("ou");
        ouCountries.forEach(c -> LOGGER.info("  {} - {}", c.getCode(), c.getName()));

        LOGGER.info("Countries containing 'ou' sorted by Name ascending:");
        List<Country> ouCountriesSorted = countryService.findCountriesContainingSorted("ou");
        ouCountriesSorted.forEach(c -> LOGGER.info("  {} - {}", c.getCode(), c.getName()));

        LOGGER.info("Countries starting with 'Z':");
        List<Country> zCountries = countryService.findCountriesStartingWith("Z");
        zCountries.forEach(c -> LOGGER.info("  {} - {}", c.getCode(), c.getName()));

        LOGGER.info("End: testCountryQueryMethods");
    }

    // ----------------- STOCK TESTS -----------------
    private static void testStockQueries() throws Exception {
        LOGGER.info("Start: testStockQueries");
        
        // Setup Date parser
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        // 1. FB in September 2019
        Date startDate = sdf.parse("2019-09-01");
        Date endDate = sdf.parse("2019-09-30");
        LOGGER.info("Facebook stocks in September 2019:");
        List<Stock> fbSepStocks = stockRepository.findByCodeAndDateBetween("FB", startDate, endDate);
        fbSepStocks.forEach(s -> LOGGER.info("  {}", s));
        
        // 2. Google stock details price > 1250
        LOGGER.info("Google stocks where close price > 1250:");
        List<Stock> googleHighStocks = stockRepository.findByCodeAndCloseGreaterThan("GOOGL", new BigDecimal("1250"));
        googleHighStocks.forEach(s -> LOGGER.info("  {}", s));

        // 3. Top 3 dates with highest volume
        LOGGER.info("Top 3 volume transactions:");
        List<Stock> topVolume = stockRepository.findTop3ByOrderByVolumeDesc();
        topVolume.forEach(s -> LOGGER.info("  {}", s));

        // 4. Netflix stocks lowest 3
        LOGGER.info("Netflix lowest 3 close price dates:");
        List<Stock> lowestNetflix = stockRepository.findTop3ByCodeOrderByCloseAsc("NFLX");
        lowestNetflix.forEach(s -> LOGGER.info("  {}", s));

        LOGGER.info("End: testStockQueries");
    }

    // ----------------- RELATIONSHIP TESTS -----------------
    private static void testGetEmployee() {
        LOGGER.info("Start: testGetEmployee");
        Employee employee = employeeService.get(1);
        LOGGER.debug("Employee: {}", employee);
        if (employee != null) {
            LOGGER.debug("Department: {}", employee.getDepartment());
            LOGGER.debug("Skills: {}", employee.getSkillList());
        }
        LOGGER.info("End: testGetEmployee");
    }

    private static void testAddEmployee() throws Exception {
        LOGGER.info("Start: testAddEmployee");
        Employee newEmp = new Employee();
        newEmp.setName("Alice Cooper");
        newEmp.setSalary(75000.00);
        newEmp.setPermanent(true);
        newEmp.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1996-08-18"));
        
        Department dept = departmentService.get(1); // IT
        newEmp.setDepartment(dept);
        
        newEmp.setSkillList(new HashSet<>());
        Skill skill = skillService.get(1); // Java
        if (skill != null) {
            newEmp.getSkillList().add(skill);
        }

        employeeService.save(newEmp);
        LOGGER.info("Successfully added employee ID: {}", newEmp.getId());
        LOGGER.info("End: testAddEmployee");
    }

    private static void testUpdateEmployee() {
        LOGGER.info("Start: testUpdateEmployee");
        Employee emp = employeeService.get(1);
        if (emp != null) {
            Department hr = departmentService.get(2); // HR
            emp.setDepartment(hr);
            employeeService.save(emp);
            LOGGER.info("Updated Employee ID 1 department to: {}", emp.getDepartment());
        }
        LOGGER.info("End: testUpdateEmployee");
    }

    private static void testGetDepartment() {
        LOGGER.info("Start: testGetDepartment");
        Department dept = departmentService.get(1); // IT
        LOGGER.debug("Department: {}", dept);
        if (dept != null && dept.getEmployeeList() != null) {
            LOGGER.debug("Department Employees count: {}", dept.getEmployeeList().size());
        }
        LOGGER.info("End: testGetDepartment");
    }

    // ----------------- ADVANCED QUERY TESTS -----------------
    private static void testAdvancedQueries() {
        LOGGER.info("Start: testAdvancedQueries");
        
        // 1. JPQL / HQL Fetch
        LOGGER.info("HQL - Permanent employees:");
        List<Employee> permanent = employeeService.getEmployeesByPermanent(true);
        permanent.forEach(e -> LOGGER.info("  {} - permanent={}", e.getName(), e.isPermanent()));

        // 2. FETCH JOIN (N+1 Select mitigation)
        LOGGER.info("FETCH JOIN - Load employees and dependencies:");
        List<Employee> allDeps = employeeService.getAllEmployeesWithDetails();
        allDeps.forEach(e -> LOGGER.info("  {} (Dept: {}) (Skills: {})", 
            e.getName(), 
            e.getDepartment() != null ? e.getDepartment().getName() : "None",
            e.getSkillList()));

        // 3. Native Query
        LOGGER.info("Native SQL - Salary > 50000:");
        List<Employee> highEarners = employeeService.getEmployeesWithSalaryGreaterThanNative(50000.0);
        highEarners.forEach(e -> LOGGER.info("  {} - salary={}", e.getName(), e.getSalary()));

        // 4. Modifying JPQL DML
        LOGGER.info("Modifying DML JPQL - Update salary of Employee ID 1:");
        int rowsUpdated = employeeService.updateEmployeeSalary(1, 95000.0);
        LOGGER.info("  Rows updated: {}", rowsUpdated);
        Employee updatedEmp = employeeService.get(1);
        LOGGER.info("  Updated Employee 1 details: {}", updatedEmp);

        // 5. Criteria API Dynamic Query
        LOGGER.info("Criteria API - Salary > 40000 in IT Dept (ID=1):");
        List<Employee> criteriaResult = employeeService.findEmployeesByCriteria(40000.0, 1);
        criteriaResult.forEach(e -> LOGGER.info("  Criteria match: {} - salary={} - dept={}", 
            e.getName(), e.getSalary(), e.getDepartment().getName()));

        // 6. Pagination & Sorting
        LOGGER.info("Pagination - Permanent employees page 0 size 2:");
        Page<Employee> paged = employeeService.getEmployeesPaged(true, 0, 2);
        LOGGER.info("  Total elements paged: {}", paged.getTotalElements());
        LOGGER.info("  Total pages: {}", paged.getTotalPages());
        paged.getContent().forEach(e -> LOGGER.info("  Paged: {} - salary={}", e.getName(), e.getSalary()));

        LOGGER.info("End: testAdvancedQueries");
    }
}
