package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataaccess.CustomerDAO;
import entities.Customer;
import helper.CustomerBuilder;
import helper.Validator;

/**
 * Servlet implementation class CreateCustomer
 */
public class CreateCustomer extends HttpServlet {
	private CustomerDAO customerDAO; 	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateCustomer() {
        super();
        //this.customerDAO = new CustomerDAO();
        
    }
    
    public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
		System.out.println(getClass()+ " Called");
	}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/mre/create.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(Validator.validateEmail(request.getParameter("email"))){
            Customer customer = new CustomerBuilder(request.getParameter("first_name"), request.getParameter("last_name"))
                    .withPhone(Long.parseLong(request.getParameter("phone")))
                    .withEmail(request.getParameter("email"))
                    .build();
            customerDAO.save(customer);

            response.sendRedirect("/mre/customers.jsp");
	    } else {
	        request.setAttribute("first_name", request.getParameter("first_name"));
	        request.setAttribute("last_name", request.getParameter("last_name"));
	        request.setAttribute("email", request.getParameter("email"));
	        request.setAttribute("phone", request.getParameter("phone"));
	        request.setAttribute("error", "Please enter a valid email!!");
	        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/create.jsp");
	        requestDispatcher.forward(request, response);
	    }
	}

}
