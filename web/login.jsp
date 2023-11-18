<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <%@include file="Components/common_css_js.jsp" %>
    </head>
    <body style="background-color: #c5cae9;">
        <div class="container-fluid">
            <div class="row mt-5">
                <div class="col-md-4 offset-md-4">
                    <div class="card">
                        <div class="card-body px-5">
                            <h3 class="text-center my-3">Login Page</h3>
                            <%@include file="Components/message.jsp" %>
                            <!--login-form-->
                            <form id="login-form" action="LoginServlet" method="post">
                                <div class="mb-3">
                                    <label class="form-label"><b>Email</b></label>
                                    <input type="email" name="email" placeholder="Login Id" class="form-control"  required>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label"><b>Password</b></label>
                                    <input type="password" name="password" placeholder="Enter your password" class="form-control" required >
                                </div>
                                <div class="container text-center my-3"> 
                                    <button type="submit" class="btn btn-outline-primary me-3">Login</button> 
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>  
    </body>
</html>
