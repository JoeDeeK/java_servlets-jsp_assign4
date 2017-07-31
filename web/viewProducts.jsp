<%-- 
    Document   : viewProducts
    Created on : Jul 21, 2016, 6:33:04 PM
    Author     : Jody kirton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>
<link rel="stylesheet" href="mainStyles.css">
<!DOCTYPE html>
<html>
    <body>
        <h1>Products</h1>
        <table>
            <tr>
                <th>Code</th>
                <th>Description</th>
                <th>Price</th>
                <th> </th>
                <th> </th>
            </tr>                        
            <c:forEach var="product" items="${products}">               
            <tr>
                <th><c:out value='${product.getCode()}'/></th>
                <th><c:out value='${product.getDescription()}'/></th>
                <th><c:out value='${product.getPriceCurrencyFormat()}'/></th>
                <th><form action="ProductMaintenanceServlet" method="POST">                        
                        <input type="hidden" name="productCode" value="${product.getCode()}">
                        <input type="submit" name="option" value="Edit">
                    </form></th>
                <th><form action="ProductMaintenanceServlet" method="POST">
                        <input type="hidden" name="productCode" value="${product.getCode()}">
                        <input type="submit" name="option" value="Delete">
                    </form></th>    
            </tr>
            </c:forEach>         
        </table><br><br>
        
       <form name="addProduct" action="ProductMaintenanceServlet" method="POST">
            <input type="Submit" name="option" value="Add Product">
        </form>
    </body>
</html>
<jsp:include page="/footer.jsp"/>
