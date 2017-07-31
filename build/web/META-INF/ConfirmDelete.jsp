<%-- 
    Document   : ConfirmDelete
    Created on : Jul 21, 2016, 7:14:56 PM
    Author     : Jody kirton
--%>

<%@page import="productmaintenance.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp"/>
<!DOCTYPE html>
<html>
    <body>
        <h1>Are you sure you want to delete this product?</h1>       
        <%            
            Product p = (Product)session.getAttribute("product");   
        %>        
        <form name="ConfirmDelete" action="ProductMaintenanceServlet" method="POST">    
            Code: <%= p.getCode() %> <br>
            Description: <%= p.getDescription() %><br>
            Price: <%= p.getPriceCurrencyFormat() %> <br><br>
            <input type="submit" name="option" value="Yes">
            <input type="submit" name="option" value="No">  
        </form>
    </body>
</html>
<jsp:include page="/footer.jsp"/>
