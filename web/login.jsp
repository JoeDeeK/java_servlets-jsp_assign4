<%-- 
    Document   : login.jsp
    Created on : Aug 10, 2016, 9:32:07 AM
    Author     : Jody Kirton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
<!DOCTYPE html>
<html>
    <body>
        <h1>Login Page</h1>
        <form method="POST" action="j_security_check">
            Name: <input type="text" name="j_username"><br><br>
            Password: <input type ="text" name="j_password"><br><br>
            <input type="submit" value="Enter">
        </form>
    </body>
</html>
<jsp:include page="footer.jsp"/>