package com.chandan.assignment.servlets;

import com.chandan.assignment.dao.CustomerDao;
import com.chandan.assignment.entities.Customer;
import com.chandan.assignment.helper.ConnectionProvider;
import com.chandan.assignment.helper.Message;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OperationServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operation = request.getParameter("op");
        HttpSession session = request.getSession();
        var token = session.getAttribute("token");
        Message message = null;

        if (operation.equals("delete")) {
            String uuid = request.getParameter("uid");

            try {

                var url = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=delete&uuid=" + uuid;
                var req = HttpRequest.newBuilder().uri(URI.create(url)).POST(HttpRequest.BodyPublishers.noBody()).header("Authorization", "Bearer " + token).build();
                var client = HttpClient.newBuilder().build();
                var resp = client.send(req, HttpResponse.BodyHandlers.discarding());

                switch (resp.statusCode()) {
                    case 200 ->
                        message = new Message("Successfully deleted!", "success", "alert-success");
                    case 500 ->
                        message = new Message("Error Not deleted!", "error", "alert-danger");
                    case 400 ->
                        message = new Message("UUID not found!", "error", "alert-danger");
                    default -> {
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            session.setAttribute("message", message);
            response.sendRedirect("customer_list.jsp");

        } else if (operation.equals("add")) {
            String fname = request.getParameter("first_name");
            String lname = request.getParameter("last_name");
            String street = request.getParameter("street");
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");

            CustomerDao cusDao = new CustomerDao(ConnectionProvider.getConnection());
            Customer customer = new Customer(fname, lname, street, address, city, state, email, phone);
            boolean flag = cusDao.saveCustomer(customer);
            if (flag) {
                message = new Message("Successfully Created!", "success", "alert-success");
            } else {
                message = new Message("Something went wrong!", "error", "alert-danger");
            }

//            var customer = "{ 'first_name' : '" + fname + "', 'last_name' : '" + lname + "', 'street' : '" + street + "', 'address' : '" + address + "', 'city' : '" + city + "', 'state' : '" + state + "', 'email' : '" + email + "', 'phone' : '" + phone + "' }";
//
//            try {
//
//                var url = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=create";
//                var req = HttpRequest.newBuilder().uri(URI.create(url)).POST(HttpRequest.BodyPublishers.ofString(customer)).header("Authorization", "Bearer " + token).header("Content-type", "application/json").build();
//                var client = HttpClient.newBuilder().build();
//                var resp = client.send(req, HttpResponse.BodyHandlers.ofPublisher());
//
//                if (resp.statusCode() == 201) {
//                    message = new Message("Successfully Created!", "success", "alert-success");
//                } else if (resp.statusCode() == 400) {
//                    message = new Message("First Name or Last Name is missing!", "error", "alert-danger");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            session.setAttribute("message", message);
            response.sendRedirect("customer_list.jsp");

        } else if (operation.equals(
                "update")) {

            String uuid = request.getParameter("uuid");
            String fname = request.getParameter("first_name");
            String lname = request.getParameter("last_name");
            String street = request.getParameter("street");
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");

            var customer = "{ 'first_name' : '" + fname + "', 'last_name' : '" + lname + "', 'street' : '" + street + "', 'address' : '" + address + "', 'city' : '" + city + "', 'state' : '" + state + "', 'email' : '" + email + "', 'phone' : '" + phone + "' }";

            try {

                var url = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=update&uuid=" + uuid;
                var req = HttpRequest.newBuilder().uri(URI.create(url)).POST(HttpRequest.BodyPublishers.ofString(customer)).header("Authorization", "Bearer " + token).header("Content-type", "application/json").build();
                var client = HttpClient.newBuilder().build();
                var resp = client.send(req, HttpResponse.BodyHandlers.discarding());

                switch (resp.statusCode()) {
                    case 200 ->
                        message = new Message("Successfully Updated!", "success", "alert-success");
                    case 500 ->
                        message = new Message("UUID not found!", "error", "alert-danger");
                    case 400 ->
                        message = new Message("Body is Empty!", "error", "alert-danger");
                    default -> {
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            session.setAttribute("message", message);
            response.sendRedirect("customer_list.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
