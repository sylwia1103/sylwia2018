import java.sql.*;
import java.util.Scanner;

class MyClass {
    public static void main(String args[]) {

        Statement statement;
        Connection conn = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mydb", "root", null
            );

            String ct = "CREATE TABLE IF NOT EXISTS users (username VARCHAR(30), password VARCHAR(30), name VARCHAR(30));";

            statement = conn.createStatement();

            statement.executeUpdate(ct);

            Scanner in = new Scanner(System.in);
            while(true) {
                System.out.println("'a' to add, 'p' to print all rows");
                String choice = in.nextLine();
                if ("p".equals(choice)) {
                    statement = conn.createStatement();
                    String query = "select * from users;";
                    ResultSet rs = statement.executeQuery(query);
                    System.out.println("Users:");
                    while (rs.next()) {
                        String username = rs.getString("username");
                        String password = rs.getString("password");
                        String name = rs.getString("name");

                        System.out.print(username);
                        System.out.print(", ");
                        System.out.print(password);
                        System.out.print(", ");
                        System.out.println(name);
                    }
                }
                if ("a".equals(choice)) {
                    System.out.print("Username: ");
                    String username = in.nextLine();
                    System.out.print("Password: ");
                    String password = in.nextLine();
                    System.out.print("Name: ");
                    String name = in.nextLine();

                    statement = conn.createStatement();

                    String query = "INSERT INTO users VALUES('"+username+"', '"+password+"', '"+name+"');";

                    statement.executeUpdate(query);
                }
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
