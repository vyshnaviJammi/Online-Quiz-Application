package com.quiz;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String jdbcURL = "jdbc:mysql://localhost:3306/quizdb";
    private static final String jdbcUsername = "root";
    private static final String jdbcPassword = "vyshnavi";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, password);

            int result = pstmt.executeUpdate();

            if (result > 0) {
                out.println("<html><body><script>alert('Account created successfully!');window.location='login.html';</script></body></html>");
            } else {
                out.println("<html><body><script>alert('Error creating account. Please try again.');window.history.back();</script></body></html>");
            }

            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            out.println("<html><body><script>alert('JDBC Driver not found: " + e.getMessage() + "');window.history.back();</script></body></html>");
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<html><body><script>alert('Database connection problem: " + e.getMessage() + "');window.history.back();</script></body></html>");
        }
    }
}
