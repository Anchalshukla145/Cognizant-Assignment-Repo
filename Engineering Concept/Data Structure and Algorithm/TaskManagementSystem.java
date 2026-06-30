class Task {
    int taskId;
    String taskName;
    String status;
    Task next;

    Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
        this.next = null;
    }
}

class TaskManagement {

    Task head = null;

    // Add Task
    void addTask(int taskId, String taskName, String status) {

        Task newTask = new Task(taskId, taskName, status);

        if (head == null) {
            head = newTask;
        } else {
            Task temp = head;

            while (temp.next != null) {
                temp = temp.next;
            }

            temp.next = newTask;
        }

        System.out.println("Task Added Successfully");
    }

    // Search Task
    void searchTask(int taskId) {

        Task temp = head;

        while (temp != null) {

            if (temp.taskId == taskId) {
                System.out.println("Task Found:");
                System.out.println(temp.taskId + " " +
                                   temp.taskName + " " +
                                   temp.status);
                return;
            }

            temp = temp.next;
        }

        System.out.println("Task Not Found");
    }

    // Traverse Tasks
    void traverseTasks() {

        if (head == null) {
            System.out.println("No Tasks Available");
            return;
        }

        System.out.println("Task List:");

        Task temp = head;

        while (temp != null) {
            System.out.println(temp.taskId + " " +
                               temp.taskName + " " +
                               temp.status);

            temp = temp.next;
        }
    }

    // Delete Task
    void deleteTask(int taskId) {

        if (head == null) {
            System.out.println("Task List is Empty");
            return;
        }

        if (head.taskId == taskId) {
            head = head.next;
            System.out.println("Task Deleted Successfully");
            return;
        }

        Task temp = head;

        while (temp.next != null && temp.next.taskId != taskId) {
            temp = temp.next;
        }

        if (temp.next == null) {
            System.out.println("Task Not Found");
        } else {
            temp.next = temp.next.next;
            System.out.println("Task Deleted Successfully");
        }
    }
}

public class TaskManagementSystem{

    public static void main(String[] args) {

        TaskManagement tm = new TaskManagement();

        tm.addTask(1, "Complete Assignment", "Pending");
        tm.addTask(2, "Attend Meeting", "Completed");
        tm.addTask(3, "Submit Report", "Pending");

        System.out.println();

        tm.traverseTasks();

        System.out.println();

        tm.searchTask(2);

        System.out.println();

        tm.deleteTask(1);

        System.out.println();

        tm.traverseTasks();
    }
}