package com.quiz;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.google.gson.JsonObject;

public class GetUserInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        JsonObject json = new JsonObject();

        if (session != null && session.getAttribute("username") != null) {
            json.addProperty("loggedIn", true);
            json.addProperty("username", (String) session.getAttribute("username"));
        } else {
            json.addProperty("loggedIn", false);
        }

        response.setContentType("application/json");
        response.getWriter().write(json.toString());
    }
}
