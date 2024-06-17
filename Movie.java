import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Movie {

    private String title;
    private String genre;
    private double rating;
    private int duration;
    private String synopsis;

    private DatabaseOperation db; // Placeholder for database connection logic
    public Movie(){
        
    }

    public Movie(String title, String genre, double rating, int duration, String synopsis) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.duration = duration;
        this.synopsis = synopsis;
    }
    

    public void insertMovie() {
        String sql = "INSERT INTO movies (title,genre,rating,duration,synopsis) VALUES(?,?,?,?,?)";
        Object[] values = {title, genre, rating, duration, synopsis};
        int rowsAffected = db.executeUpdate(sql, values);
        if (rowsAffected > 0) {
            System.out.println("Movie inserted successfully");
        } else {
            System.out.println("Movie was not inserted");
        }
    }

    public void showMovies() {
        String sql = "SELECT * FROM Movies";
        ResultSet rs = db.getRecords(sql);
        try {
            while (rs.next()) {
                System.out.println("Movie ID: " + rs.getInt("Movie ID"));  // Use column name in quotes
                System.out.println("Title: " + rs.getString("Title"));    // Use column name in quotes
                System.out.println("Genre: " + rs.getString("Genre"));     // Use column name in quotes
                System.out.println("Rating: " + rs.getDouble("Rating"));  // Use column name in quotes
                System.out.println("Duration (in mins): " + rs.getInt("Duration")); // Use column name in quotes
                System.out.println("Synopsis: " + rs.getString("Synopsis"));// Use column name in quotes
                System.out.println("------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getRecords(String sql) {
        ResultSet rs = null;
        try (Connection conn = connectToDatabase();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
