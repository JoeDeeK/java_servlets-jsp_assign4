<%-- 
    Document   : Product
    Created on : Jul 21, 2016, 6:41:42 PM
    Author     : Jody Kirton
--%>

<%@page import="productmaintenance.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp"/>
<!DOCTYPE html>
<html>
    <body>
        <h1>Product</h1>
        <% 
            String code="", description="", price ="";
            Product p = (Product)request.getAttribute("product");
            if(p != null){
                code = p.getCode();
                description = p.getDescription();
                price = p.getPrice()+"";
            }
            
            String error="";
            if(error != null){
                error = (String)request.getAttribute("error");
            }   
        %> 
        <form name="AddProduct" action="ProductMaintenanceServlet" method="POST">
            Code: <input type="text" name="code" value="<%=code%>"><br><br>
            Description: <input type ="text" name="description" value="<%=description%>"><br><br>
            Price: <input type ="text" name="price" value="<%=price%>"><br><br>
            <input type="submit" name="option" value="Update Product">
            <input type="submit" name="option" value="View Products">
        </form>
            <h2>${error}</h2>
    </body>
</html>
<jsp:include page="/footer.jsp"/>
