

        package com.example.facebook.Controller;

import com.example.facebook.DAO.UserDao;

import com.example.facebook.Util.DatabaseConnection;

import com.example.facebook.Model.User;

import com.example.facebook.dao.UserDao;
import com.example.facebook.model.User;
import com.example.facebook.util.DatabaseConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

        @WebServlet(name = "SignUpServlet", value = "/SignUpServlet")
        public class SignUpServlet extends HttpServlet {

            @Override
            protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                response.setContentType("text/html;charset=UTF-8");

                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String birthDate = request.getParameter("birthDate");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String gender = request.getParameter("gender");
                String phone_number = request.getParameter("phone_number");

                User user = new User(firstName, lastName, birthDate, email, password, gender, phone_number);

                try {
                    Connection connection = DatabaseConnection.getConnection();
                    UserDao userDao = new UserDao(connection);

                    boolean status = userDao.userRegistration(user);

                    HttpSession session = request.getSession();

                    if (status) {
                        session.setAttribute("sign_in", "success");
                    } else {
                        request.setAttribute("sign_in", "failed");
                    }

                    connection.close(); // Close the database connection after usage.

                    request.getRequestDispatcher("homepage.jsp").forward(request, response);

                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                    response.getWriter().println("An error occurred while processing your request.");
                }
            }
        }
