import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DepartmentManager {

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/departments";
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";

    // Department class representing the Department entity
    public static class Department {
        private int id;
        private String name;

        public Department(int id, String name) {
            this.id = id;
            this.name = name;
        }

        // Getters and setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    // Method to insert a Department into the database
    public static void insertDepartment(Department department) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO department (id, name) VALUES (?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, department.getId());
                statement.setString(2, department.getName());
                statement.executeUpdate();
                System.out.println("Department inserted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Create a Department object
        Department department = new Department(1, "Engineering");

        // Insert the Department into the database
        insertDepartment(department);
    }
}
