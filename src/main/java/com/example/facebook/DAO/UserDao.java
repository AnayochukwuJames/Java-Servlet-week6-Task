package com.exampl.facebook.DAO;
import com.example.facebook.Model.User;
import java.sql.*;


public class UserDao {
    private Connection connection;
    public UserDao(Connection connection) {
        this.connection = connection;

    }
    public boolean userRegistration(User user) throws SQLException {
        String query = "INSERT INTO user(first_name, last_name, birthDate, email, password, gender, phone_number) VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, user.getFirstName());

            preparedStatement.setString(2, user.getLastName());

// preparedStatement.setDate(3, Date.valueOf(user.get()));

            preparedStatement.setString(4, user.getEmail());

            preparedStatement.setString(5, user.getPassword());

            preparedStatement.setString(6, user.getGender());

            preparedStatement.setString(7, user.getPhone_number());


            var statementResult = preparedStatement.executeUpdate();

            System.out.println(statementResult);

            return statementResult > 0;

        } catch (SQLException e) {

            e.printStackTrace();

            throw new SQLException("Failed to register user: " + e.getMessage());

        }

    }


    public boolean checkEmailExist(String email) throws SQLException {
        String query = "SELECT EMAIL FROM user WHERE email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {

            e.printStackTrace();

            throw new SQLException("Failed to check email existence: " + e.getMessage());

        }

    }
    public User userLogin(String email, String password) throws SQLException {

        User user = null;

        String query = "SELECT * FROM user WHERE email = ? AND password = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);

            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {

                    user = new User();

                    user.setFirstName(resultSet.getString("first_name"));

                    user.setLastName(resultSet.getString("last_name"));

                    user.setEmail(resultSet.getString("email"));

                    user.setPassword(resultSet.getString("password"));

// user.set(resultSet.getInt("userId"));

                }

            }

        } catch (SQLException e) {

            e.printStackTrace();

            throw new SQLException("Failed to log in: " + e.getMessage());

        }

        return user;

    }


// Close resources after executing queries

    public void closeResources() throws SQLException {

        if (connection != null) {

            connection.close();

        }

    }

}
