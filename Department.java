import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DepartmentDAO {
    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/departments";
    private static final String USERNAME = "ramyasri";
    private static final String PASSWORD = " ";

    // Insert query
    private static final String INSERT_QUERY = "INSERT INTO department (id, name) VALUES (?, ?)";

    public static void main(String[] args) {
        // Sample Department objects
        Department department1 = new Department(1, "Front-End Department");
        Department department2 = new Department(2, "Back-End Department");

        // Save Department objects to the database
        saveDepartment(department1);
        saveDepartment(department2);
    }

    // Method to save a Department object to the database
    private static void saveDepartment(Department department) {
        try (
                // Establishing connection and creating prepared statement in try-with-resources
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)
        ) {
            // Set parameters for the prepared statement
            preparedStatement.setInt(1, department.getId());
            preparedStatement.setString(2, department.getName());

            // Execute the query
            preparedStatement.executeUpdate();
            System.out.println("Department saved successfully: " + department);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class Department {
    private int id;
    private String name;

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Department{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
