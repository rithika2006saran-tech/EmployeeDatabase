import java.sql.*;
import java.util.Scanner;

public class DepartmentService {

    // Add Department
    public static void addDepartment(Scanner sc)  throws Exception {
        System.out.print("Enter Department ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter Department Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Location: ");
        String location = sc.nextLine();

        String sql = "INSERT INTO departments (dept_id, dept_name, location) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, location);
            int rows = ps.executeUpdate();
            System.out.println(rows + " Department added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding department: " + e.getMessage());
        }
    }

    // Update Department
    public static void updateDepartment(Scanner sc)  throws Exception  {
        System.out.print("Enter Department ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new Department Name: ");
        String name = sc.nextLine();
        System.out.print("Enter new Location: ");
        String location = sc.nextLine();

        String sql = "UPDATE departments SET dept_name = ?, location = ? WHERE dept_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, location);
            ps.setInt(3, id);
            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("Department updated successfully!");
            else
                System.out.println("Department not found!");
        } catch (SQLException e) {
            System.out.println("Error updating department: " + e.getMessage());
        }
    }

    // Delete Department
    public static void deleteDepartment(Scanner sc)  throws Exception {
        System.out.print("Enter Department ID to delete: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM departments WHERE dept_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("Department deleted successfully!");
            else
                System.out.println("Department not found!");
        } catch (SQLException e) {
            System.out.println("Error deleting department: " + e.getMessage());
        }
    }

    // View All Departments
    public static void viewDepartments()  throws Exception  {
        String sql = "SELECT * FROM departments ORDER BY dept_id";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            System.out.println("\n--- Department List ---");
            System.out.printf("%-10s %-20s %-20s%n", "ID", "Name", "Location");
            System.out.println("-----------------------------------------------");
            while (rs.next()) {
                System.out.printf("%-10d %-20s %-20s%n",
                        rs.getInt("dept_id"),
                        rs.getString("dept_name"),
                        rs.getString("location"));
            }
        } catch (SQLException e) {
            System.out.println("Error viewing departments: " + e.getMessage());
        }
    }
}

