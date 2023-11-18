<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.chandan.assignment.helper.Message"%>
<%
    var token = session.getAttribute("token");
    if(token == null){
        Message message = new Message("Please login!", "error", "alert-danger");
        session.setAttribute("message", message);
        response.sendRedirect("login.jsp");
        return;
    }

    String uuid = request.getParameter("uid");
    String fname = request.getParameter("fname");
    String lname = request.getParameter("lname");
    String street = request.getParameter("street");
    String address = request.getParameter("addr");
    String city = request.getParameter("city");
    String state = request.getParameter("state");
    String email = request.getParameter("email");
    String phone = request.getParameter("phone");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Customer</title>
         <%@include file="Components/common_css_js.jsp" %>
        <style>
            label{
                font-weight: bold;
            }    
        </style>
    </head>
    <body style="background-color: #c5cae9;">
        <div class="container-fluid mt-4">
            <div class="row g-0">
                <div class="col-md-6 offset-md-3">
                    <div class="card">
                        <div class="card-body px-5">
                            <h3 class="text-center">Update Customer</h3>
                            <%@include file="Components/message.jsp" %>
                            <form action="OperationServlet?op=update" method="post">
                                <input type="hidden" name="uuid" value="<%=uuid%>">
                                <div class="row">
                                    <div class="col-md-6 mt-2">
                                        <label class="form-label">First name</label> 
                                        <input type="text" name="first_name"class="form-control" value="<%=fname%>" required>
                                    </div>
                                    <div class="col-md-6 mt-2">
                                        <label class="form-label">Last Name</label>
                                        <input type="text" name="last_name" value="<%=lname%>" class="form-control" required>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6 mt-2">
                                        <label class="form-label">Street</label> <input
                                            type="text" name="street"
                                            value="<%=street%>" class="form-control">
                                    </div>
                                    <div class="col-md-6 mt-2">
                                        <label class="form-label">Address</label> <input
                                            type="text" name="address"
                                            value="<%=address%>" class="form-control">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6 mt-2">
                                        <label class="form-label">City</label> <input
                                            class="form-control" type="text" name="city"
                                            value="<%=city%>">
                                    </div>
                                    <div class="col-md-6 mt-2">
                                        <label class="form-label">state</label> <input
                                            class="form-control" type="text" name="state"
                                            value="<%=state%>">
                                    </div>  
                                </div>
                                <div class="row">
                                    <div class="col-md-6 mt-2">
                                        <label class="form-label">Email</label> <input
                                            class="form-control" type="email" name="email"
                                            value="<%=email%>">
                                    </div>
                                    <div class="col-md-6 mt-2">
                                        <label class="form-label">Phone</label> <input
                                            class="form-control" type="number" name="phone"
                                            value="<%=phone%>">
                                    </div>  
                                </div>
                        </div>
                        <div class="container text-center my-4">
                            <button type="submit" class="btn btn-outline-primary me-3">Submit</button>
                            <button type="reset" class="btn btn-outline-primary">Reset</button>
                        </div> 
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
