package Controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import productmaintenance.Product;
import productmaintenance.ProductIO;

/**
 *
 * @author Jody Kirton
 */
@WebServlet(name = "ProductMaintenanceServlet", urlPatterns = {"/ProductMaintenanceServlet"})
public class ProductMaintenanceServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ServletContext sc = getServletContext();
        
        //Get current action (get parameter)
        String action = request.getParameter("option");
        //Get product code
        String productCode = request.getParameter("productCode");

        //url for sending user to a new jsp
        String url = "";
        
        //Used to display error messages when forms not properly filled
        String error = "";
        
        //Check button action, determine process and which jsp page to display
        if(action.equals("View Products")) {       
            url = "/viewProducts.jsp";
        }else if(action.equals("Add Product")) {
            url = "/manager/Product.jsp";
        }else if(action.equals("Update Product")) {
            //Collect parameter values from form and store in Strings
            String code = request.getParameter("code");
            String description = request.getParameter("description");
            String priceString = request.getParameter("price");
            
            //Check stored parameters values before creating Product object
            if((null != code && code.length() > 0)
                    && (null != description && description.length() > 0)
                    && (null != priceString && priceString.length() > 0)) {
                //Check if product code already exist, if so make edits
                //if not add a new product to list
                if(ProductIO.exists(code)) {
                    try{
                        //pass the values to the constructor
                        Double price = Double.parseDouble(priceString);
                        Product newProduct = new Product(code, description, price);
                        ProductIO.updateProduct(newProduct);
                        url = "/viewProducts.jsp";
                    }catch(Exception e){
                        //Display error message on page
                        error = "you did not enter a valid price";
                        url = "/manager/Product.jsp";
                    }     
                }else{
                    //Product code not found in list, add new product
                    try{
                        //pass the values to the constructor
                        Double price = Double.parseDouble(priceString);
                        Product newProduct = new Product(code, description, price);
                        ProductIO.insertProduct(newProduct);
                        url = "/viewProducts.jsp";
                    }catch(Exception e){
                        //Display error message on page
                        error = "you did not enter a valid price";
                        url = "/manager/Product.jsp";
                    }
                }                               
            }else{
                //Display error message on page
                error = "Please fill out all form fields";
                url = "/manager/Product.jsp";
            }
        }else if(action.equals("Edit")) {
            //Declare product and then instantiate product using product code
            Product product = ProductIO.selectProduct(productCode);
            //Set arrtibute so can be displayed in next page
            request.setAttribute("product", product);
            url = "/manager/Product.jsp";
        }else if(action.equals("Delete")) {
            //Declare product and then instantiate product using product code
            Product product = ProductIO.selectProduct(productCode);
            //Create session
            HttpSession session = request.getSession();
            //Set arrribute in session for use in jsp
            session.setAttribute("product", product);           
            url = "/manager/ConfirmDelete.jsp";
        //User would like to add to list or update product in list
        }else if(action.equals("Yes")) {
            //Create session
            HttpSession session = request.getSession();
            //Declare product and then instantiate product from session product
            Product p = (Product)session.getAttribute("product");
            //Delete selected product from storage list
            ProductIO.deleteProduct(p);          
            url = "/viewProducts.jsp";
        //User no loinger wants to add or make any changes
        }else if(action.equals("No")){
            url = "/viewProducts.jsp";
        }else{
            //If all else fails, back to index page
            url = "/index.jsp";
        }
        
        //Declare product list after any updates or adds for display
        List<Product> products = ProductIO.selectProducts();
        //Set attributes for retrieval
        request.setAttribute("products", products);
        request.setAttribute("error", error);
        
        //Send user to the assigned url
        sc.getRequestDispatcher(url)
                .forward(request, response);
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
