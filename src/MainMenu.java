import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args)  throws Exception {
        Scanner sc = new Scanner(System.in);
        int choice ;
        while (true) {
            System.out.println("\n====== EMPLOYEE MANAGEMENT SYSTEM ======");
            System.out.println("1. Manage Departments");
            System.out.println("2. Manage Jobs");
            System.out.println("3. Manage Employees");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    manageDepartments(sc);
                    break;
                case 2:
                    manageJobs(sc);
                    break;
                case 3:
                    manageEmployees(sc);
                    break;
                case 4:
                    System.out.println("Exiting system... Goodbye!");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // ---------------- Department Menu ----------------
    private static void manageDepartments(Scanner sc)  throws Exception  {
        int choice;
        while (true) {
            System.out.println("\n---- DEPARTMENT MENU ----");
            System.out.println("1. Add Department");
            System.out.println("2. Update Department");
            System.out.println("3. Delete Department");
            System.out.println("4. View All Departments");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    DepartmentService.addDepartment(sc);
                    break;
                case 2:
                    DepartmentService.updateDepartment(sc);
                    break;
                case 3:
                    DepartmentService.deleteDepartment(sc);
                    break;
                case 4:
                    DepartmentService.viewDepartments();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // ---------------- Job Menu ----------------
    private static void manageJobs(Scanner sc)  throws Exception  {
        int choice;
        while (true) {
            System.out.println("\n---- JOB MENU ----");
            System.out.println("1. Add Job");
            System.out.println("2. Update Job");
            System.out.println("3. Delete Job");
            System.out.println("4. View All Jobs");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    JobService.addJob(sc);
                    break;
                case 2:
                    JobService.updateJob(sc);
                    break;
                case 3:
                    JobService.deleteJob(sc);
                    break;
                case 4:
                    JobService.viewJobs();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // ---------------- Employee Menu ----------------
    private static void manageEmployees(Scanner sc)  throws Exception {
        int choice;
        while (true) {
            System.out.println("\n---- EMPLOYEE MENU ----");
            System.out.println("1. Add Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Delete Employee");
            System.out.println("4. View All Employees");
            System.out.println("5. Search Employee by ID");
            System.out.println("6. Search Employee by Name");
            System.out.println("7. View Employees by Department");
            System.out.println("8. View Employees by Job");
            System.out.println("9. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    EmployeeService.addEmployee(sc);
                    break;
                case 2:
                    EmployeeService.updateEmployee(sc);
                    break;
                case 3:
                    EmployeeService.deleteEmployee(sc);
                    break;
                case 4:
                    EmployeeService.viewAllEmployees();
                    break;
                case 5:
                    EmployeeService.searchById(sc);
                    break;
                case 6:
                    EmployeeService.searchByName(sc);
                    break;
                case 7:
                    EmployeeService.viewByDepartment(sc);
                    break;
                case 8:
                    EmployeeService.viewByJob(sc);
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
