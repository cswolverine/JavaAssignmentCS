<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="com.chandan.assignment.helper.CustomerOperation" %>
<%@page import="com.chandan.assignment.entities.Customer" %>
<%@page import="com.chandan.assignment.helper.Message"%>
<%
    String token = (String) session.getAttribute("token");
    List<Customer> customerList = null;
    if(token != null){
        customerList = CustomerOperation.getCustomerList(token);
    }else{
        Message message = new Message("Please login!", "error", "alert-danger");
        session.setAttribute("message", message);
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer List Screen</title>
        <%@include file="Components/common_css_js.jsp" %>
        <style type="text/css">
            td, th {
                padding: 0 20px;
            }
        </style>
    </head>
    <body style="background-color: #c5cae9;">
        <div class="container my-5">
            <div class="card">
                <div class="card-body px-5">
                    <div class="container mt-3">
                        <a class="btn btn-primary" href="add_customer.jsp" role="button">Add Customer</a>
                        <a class="btn btn-primary" href="LogoutServlet" role="button">Logout</a>
                    </div>
                    
                    <div class="container mt-3">
                        <%@include file="Components/message.jsp" %>
                        <table class=" table table-hover">
                            <tr class="table-secondary" style="font-size: 18px;">
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Street</th>
                                <th>Address</th>
                                <th>city</th>
                                <th>State</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th colspan="2">Action</th>
                            </tr>
                            <%for(Customer i : customerList){%>
                            <tr>
                                <td><%=i.getFirstName() %></td>
                                <td><%=i.getLastName() %></td>
                                <td><%=i.getStreet() %></td>
                                <td><%=i.getAddress() %></td>
                                <td><%=i.getCity() %></td>
                                <td><%=i.getState() %></td>
                                <td><%=i.getEmail() %></td>
                                <td><%=i.getPhone() %></td>
                                <td><a href="OperationServlet?uid=<%=i.getUuid()%>&op=delete" role="button" class="btn btn-link"> <i class="fa-solid fa-circle-minus fa-xl" style="color: #ff0000;"></i></a><a href="update_customer.jsp?uid=<%=i.getUuid()%>&fname=<%=i.getFirstName()%>&lname=<%=i.getLastName()%>&street=<%=i.getStreet()%>&addr=<%=i.getAddress()%>&city=<%=i.getCity()%>&state=<%=i.getState()%>&email=<%=i.getEmail()%>&phone=<%=i.getPhone()%>" role="button" class="btn btn-link"> <i class="fa-solid fa-pen fa-lg" style="color: #9e9e9e;"></i></a></td>
                            </tr>
                            <%}%>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
