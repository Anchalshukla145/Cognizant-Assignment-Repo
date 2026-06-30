class Employee {
    int employeeId;
    String name;
    String position;
    double salary;

    Employee(int employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    void display() {
        System.out.println(
                employeeId + " " +
                name + " " +
                position + " " +
                salary
        );
    }
}

class EmployeeManagement {

    Employee[] employees = new Employee[10];
    int count = 0;

    // Add Employee
    void addEmployee(Employee employee) {
        if (count < employees.length) {
            employees[count] = employee;
            count++;
            System.out.println("Employee Added Successfully");
        } else {
            System.out.println("Employee Array is Full");
        }
    }

    // Search Employee
    void searchEmployee(int id) {

        for (int i = 0; i < count; i++) {
            if (employees[i].employeeId == id) {
                System.out.println("Employee Found:");
                employees[i].display();
                return;
            }
        }

        System.out.println("Employee Not Found");
    }

    // Traverse Employees
    void traverseEmployees() {

        if (count == 0) {
            System.out.println("No Employees");
            return;
        }

        System.out.println("Employee Records:");

        for (int i = 0; i < count; i++) {
            employees[i].display();
        }
    }

    // Delete Employee
    void deleteEmployee(int id) {

        for (int i = 0; i < count; i++) {

            if (employees[i].employeeId == id) {

                for (int j = i; j < count - 1; j++) {
                    employees[j] = employees[j + 1];
                }

                employees[count - 1] = null;
                count--;

                System.out.println("Employee Deleted Successfully");
                return;
            }
        }

        System.out.println("Employee Not Found");
    }
}

public class EmployeeManagementSystem {

    public static void main(String[] args) {

        EmployeeManagement em = new EmployeeManagement();

        em.addEmployee(new Employee(101, "Anchal", "Developer", 65000));
        em.addEmployee(new Employee(102, "Rahul", "Tester", 50000));
        em.addEmployee(new Employee(103, "Aman", "Manager", 90000));

        System.out.println();

        em.traverseEmployees();

        System.out.println();

        em.searchEmployee(102);

        System.out.println();

        em.deleteEmployee(101);

        System.out.println();

        em.traverseEmployees();
    }
}