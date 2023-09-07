package com.example.facebook.Controller;

import com.example.facebook.DAO.UserDao;
import com.example.facebook.Model.User;
import com.example.facebook.Util.DatabaseConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

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
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Redirect to login page if the "id" parameter is not provided
        String idParameter = request.getParameter("id");
        if (idParameter != null) {
            request.setAttribute("id", idParameter);
            request.getRequestDispatcher("Sign_up.jsp").forward(request, response);
        } else {
            response.getWriter().println("<html><body><h1>Hello World!</h1></body></html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        UserDao userDao;

        try {
            userDao = new UserDao(DatabaseConnection.getConnection());
            User user = userDao.userLogin(email, password);

            if (user != null) {
                session.setAttribute("name", "user_id: " + user.getUser_Id());
                response.sendRedirect("homepage.jsp");
            } else {
                request.setAttribute("status", "failed");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // You can handle any database/connection errors here
            // For simplicity, let's just show an error page:
            response.getWriter().println("An error occurred while processing your request.");
        }
    }
}
