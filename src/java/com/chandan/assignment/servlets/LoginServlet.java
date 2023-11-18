package com.chandan.assignment.servlets;

import com.chandan.assignment.helper.Message;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import org.json.JSONObject;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        var userId = request.getParameter("email");
        var password = request.getParameter("password");
        var user = "{ 'login_id' : '" + userId + "', 'password' : '" + password + "' }";
        HttpSession session = request.getSession();
        
        try {

            var url = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";
            var req = HttpRequest.newBuilder().uri(URI.create(url)).POST(BodyPublishers.ofString(user)).header("Content-type", "application/json").build();
            var client = HttpClient.newBuilder().build();
            var resp = client.send(req, HttpResponse.BodyHandlers.ofString());

            if (resp.statusCode() == 200) {
                
                JSONObject resObj = new JSONObject(resp.body());
                var token = resObj.getString("access_token");
                session.setAttribute("token", token);
                response.sendRedirect("customer_list.jsp");
                
            } else {
                
                Message message = new Message("Invalid email or Password! Try again!!", "error", "alert-danger");
                session.setAttribute("message", message);
                response.sendRedirect("login.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
