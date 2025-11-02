import java.sql.*;
import java.util.Scanner;

public class JobService {

    // ✅ Add Job
    public static void addJob(Scanner sc)  throws Exception  {
        System.out.print("Enter Job ID: ");
        int jobId = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter Job Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Minimum Salary: ");
        double minSalary = sc.nextDouble();
        System.out.print("Enter Maximum Salary: ");
        double maxSalary = sc.nextDouble();

        String sql = "INSERT INTO jobs (job_id, job_title, min_salary, max_salary) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, jobId);
            ps.setString(2, title);
            ps.setDouble(3, minSalary);
            ps.setDouble(4, maxSalary);
            int rows = ps.executeUpdate();
            System.out.println(rows + " Job added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding job: " + e.getMessage());
        }
    }

    // ✅ Update Job
    public static void updateJob(Scanner sc)  throws Exception {
        System.out.print("Enter Job ID to update: ");
        int jobId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new Job Title: ");
        String title = sc.nextLine();
        System.out.print("Enter new Minimum Salary: ");
        double minSalary = sc.nextDouble();
        System.out.print("Enter new Maximum Salary: ");
        double maxSalary = sc.nextDouble();

        String sql = "UPDATE jobs SET job_title = ?, min_salary = ?, max_salary = ? WHERE job_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setDouble(2, minSalary);
            ps.setDouble(3, maxSalary);
            ps.setInt(4, jobId);
            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("Job updated successfully!");
            else
                System.out.println("Job not found!");
        } catch (SQLException e) {
            System.out.println("Error updating job: " + e.getMessage());
        }
    }

    // ✅ Delete Job
    public static void deleteJob(Scanner sc)  throws Exception {
        System.out.print("Enter Job ID to delete: ");
        int jobId = sc.nextInt();

        String sql = "DELETE FROM jobs WHERE job_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, jobId);
            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("Job deleted successfully!");
            else
                System.out.println("Job not found!");
        } catch (SQLException e) {
            System.out.println("Error deleting job: " + e.getMessage());
        }
    }

    // ✅ View All Jobs
    public static void viewJobs()  throws Exception {
        String sql = "SELECT * FROM jobs ORDER BY job_id";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            System.out.println("\n--- Job List ---");
            System.out.printf("%-10s %-20s %-15s %-15s%n", "ID", "Title", "Min Salary", "Max Salary");
            System.out.println("--------------------------------------------------------------");
            while (rs.next()) {
                System.out.printf("%-10d %-20s %-15.2f %-15.2f%n",
                        rs.getInt("job_id"),
                        rs.getString("job_title"),
                        rs.getDouble("min_salary"),
                        rs.getDouble("max_salary"));
            }
        } catch (SQLException e) {
            System.out.println("Error viewing jobs: " + e.getMessage());
        }
    }
}
