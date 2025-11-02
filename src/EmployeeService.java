import java.sql.*;
import java.util.Scanner;

public class EmployeeService {
    // ✅ Add Employee
    public static void addEmployee(Scanner sc)  throws Exception  {
        System.out.print("Enter Employee ID: ");
        int empId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter First Name: ");
        String firstName = sc.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Hire Date (YYYY-MM-DD): ");
        String hireDate = sc.nextLine();
        System.out.print("Enter Job ID: ");
        int jobId = sc.nextInt();
        System.out.print("Enter Department ID: ");
        int deptId = sc.nextInt();
        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();

        String sql = "INSERT INTO employees (emp_id, first_name, last_name, email, hire_date, job_id, dept_id, salary) "
                + "VALUES (?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, empId);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setString(4, email);
            ps.setString(5, hireDate);
            ps.setInt(6, jobId);
            ps.setInt(7, deptId);
            ps.setDouble(8, salary);
            int rows = ps.executeUpdate();
            System.out.println(rows + " Employee added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding employee: " + e.getMessage());
        }
    }

    // ✅ Update Employee
    public static void updateEmployee(Scanner sc) throws Exception {
        System.out.print("Enter Employee ID to update: ");
        int empId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new Email: ");
        String email = sc.nextLine();
        System.out.print("Enter new Salary: ");
        double salary = sc.nextDouble();

        String sql = "UPDATE employees SET email = ?, salary = ? WHERE emp_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setDouble(2, salary);
            ps.setInt(3, empId);
            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("Employee updated successfully!");
            else
                System.out.println("Employee not found!");
        } catch (SQLException e) {
            System.out.println("Error updating employee: " + e.getMessage());
        }
    }

    // ✅ Delete Employee
    public static void deleteEmployee(Scanner sc)  throws Exception {
        System.out.print("Enter Employee ID to delete: ");
        int empId = sc.nextInt();

        String sql = "DELETE FROM employees WHERE emp_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, empId);
            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("Employee deleted successfully!");
            else
                System.out.println("Employee not found!");
        } catch (SQLException e) {
            System.out.println("Error deleting employee: " + e.getMessage());
        }
    }

    // ✅ View All Employees
    public static void viewAllEmployees()  throws Exception {
        String sql = """
            SELECT e.emp_id, e.first_name || ' ' || e.last_name AS full_name,
                   e.email, e.hire_date, e.salary,
                   d.dept_name, j.job_title
            FROM employees e
            LEFT JOIN departments d ON e.dept_id = d.dept_id
            LEFT JOIN jobs j ON e.job_id = j.job_id
            ORDER BY e.emp_id
        """;

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            System.out.println("\n--- Employee List ---");
            System.out.printf("%-8s %-20s %-25s %-12s %-10s %-20s %-20s%n",
                    "ID", "Name", "Email", "Hire Date", "Salary", "Department", "Job");
            System.out.println("-------------------------------------------------------------------------------------------");
            while (rs.next()) {
                System.out.printf("%-8d %-20s %-25s %-12s %-10.2f %-20s %-20s%n",
                        rs.getInt("emp_id"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getDate("hire_date"),
                        rs.getDouble("salary"),
                        rs.getString("dept_name"),
                        rs.getString("job_title"));
            }
        } catch (SQLException e) {
            System.out.println("Error viewing employees: " + e.getMessage());
        }
    }

    // ✅ Search Employee by ID
    public static void searchById(Scanner sc)  throws Exception {
        System.out.print("Enter Employee ID: ");
        int empId = sc.nextInt();

        String sql = """
            SELECT e.emp_id, e.first_name, e.last_name, e.email, e.salary,
                   d.dept_name, j.job_title
            FROM employees e
            LEFT JOIN departments d ON e.dept_id = d.dept_id
            LEFT JOIN jobs j ON e.job_id = j.job_id
            WHERE e.emp_id = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("\nEmployee Found:");
                System.out.println("ID: " + rs.getInt("emp_id"));
                System.out.println("Name: " + rs.getString("first_name") + " " + rs.getString("last_name"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Salary: " + rs.getDouble("salary"));
                System.out.println("Department: " + rs.getString("dept_name"));
                System.out.println("Job: " + rs.getString("job_title"));
            } else {
                System.out.println("No employee found with that ID!");
            }
        } catch (SQLException e) {
            System.out.println("Error searching employee: " + e.getMessage());
        }
    }

    // ✅ Search Employee by Name
    public static void searchByName(Scanner sc)  throws Exception {
        sc.nextLine();
        System.out.print("Enter Employee Name: ");
        String name = sc.nextLine();

        String sql = """
            SELECT e.emp_id, e.first_name, e.last_name, e.email, e.salary,
                   d.dept_name, j.job_title
            FROM employees e
            LEFT JOIN departments d ON e.dept_id = d.dept_id
            LEFT JOIN jobs j ON e.job_id = j.job_id
            WHERE LOWER(e.first_name) LIKE ? OR LOWER(e.last_name) LIKE ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + name.toLowerCase() + "%");
            ps.setString(2, "%" + name.toLowerCase() + "%");
            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- Search Results ---");
            while (rs.next()) {
                System.out.printf("%d - %s %s (%s) - %s - %s%n",
                        rs.getInt("emp_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("dept_name"),
                        rs.getString("job_title"));
            }
        } catch (SQLException e) {
            System.out.println("Error searching employee: " + e.getMessage());
        }
    }

    // ✅ View Employees by Department
    public static void viewByDepartment(Scanner sc)  throws Exception {
        System.out.print("Enter Department ID: ");
        int deptId = sc.nextInt();

        String sql = """
            SELECT e.emp_id, e.first_name, e.last_name, j.job_title, e.salary
            FROM employees e
            LEFT JOIN jobs j ON e.job_id = j.job_id
            WHERE e.dept_id = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, deptId);
            ResultSet rs = ps.executeQuery();

            System.out.println("\nEmployees in Department ID: " + deptId);
            while (rs.next()) {
                System.out.printf("%d - %s %s - %s - Salary: %.2f%n",
                        rs.getInt("emp_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("job_title"),
                        rs.getDouble("salary"));
            }
        } catch (SQLException e) {
            System.out.println("Error viewing employees by department: " + e.getMessage());
        }
    }

    // ✅ View Employees by Job
    public static void viewByJob(Scanner sc)  throws Exception {
        System.out.print("Enter Job ID: ");
        int jobId = sc.nextInt();

        String sql = """
            SELECT e.emp_id, e.first_name, e.last_name, e.salary, d.dept_name
            FROM employees e
            LEFT JOIN departments d ON e.dept_id = d.dept_id
            WHERE e.job_id = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, jobId);
            ResultSet rs = ps.executeQuery();

            System.out.println("\nEmployees with Job ID: " + jobId);
            while (rs.next()) {
                System.out.printf("%d - %s %s - %s - Salary: %.2f%n",
                        rs.getInt("emp_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("dept_name"),
                        rs.getDouble("salary"));
            }
        } catch (SQLException e) {
            System.out.println("Error viewing employees by job: " + e.getMessage());
        }
    }
}
